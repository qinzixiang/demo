package com.qinzx.demo.future;

/**
 * @ClassName: AbstractPackageService
 * @Author qinzx
 * @Date 2019/07/11 17:54
 */
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import static com.qinzx.demo.future.Util.*;
import static java.util.concurrent.CompletableFuture.*;

/**
 * Not ThreadSafe and just for demo.
 *
 * @author zhhe.me@gmail.com
 * @since 10/9/2018
 */
public abstract class AbstractPackageService implements PackageService {

    protected StockService stockService = new StockService();
    protected RepoService repoService = new RepoService();
    protected EventService eventService = new EventService();

    /* stateful variables. */
    protected AtomicInteger queriedQ = new AtomicInteger(0);
    protected CompletableFuture<Boolean> allocated = new CompletableFuture<>();

    /**
     * 分拣订单
     * @author  qinzx
     * @date  2019/7/12 15:14
     * @param oid
     * @param pid
     * @return  boolean
     */
    @Override
    public boolean pack(String oid, String pid) {
        /* set global repos since it's used by single repo + multi repo demos. */
        repos.clear();
        repos.addAll(getRepos());
        //初始化仓库
        logger.log("初始化仓库: %s", repos);
        /* set started time. */
        logger.start();
        /* 监听订单取消事件 */
        /* a listener to monitor if this order's cancellation event was emitted. */
        final CompletableFuture<Boolean> cancelListener =
                runAsync(() -> eventService.listenOrderCancel(oid))
                        .thenApply(reason -> false);

        /* a control to indicate if package was completed. */
        /* 总开关（权衡订单取消和分拣结束） */
        final CompletableFuture<Boolean> terminator =
                allocated.applyToEitherAsync(cancelListener, Function.identity());

        logger.log("正在快速预检查库存...");

        supplyAsync(() -> stockService.query(pid))
        .whenComplete((stock, e) -> {
            if (e != null) {
                e.printStackTrace();
                allocated.complete(false);
            } else if (stock < q) {
                logger.log("库存不足 (%d < %d)", stock, q);
                allocated.complete(false);
            } else {
                logger.log("总库存足够 (%d >= %d). 从仓库分配中...", stock, q);
                startPick(pid);
            }
        });

        try {
            // wait until package was completed.
            return terminator.get(5, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /** repos used to initialize global repos and generate stocks. */
    protected abstract Collection<String> getRepos();

    /** the entry to kick off pick process. */
    protected abstract void startPick(String pid);

    /** a process to pick up stock from one repo. */
    protected CompletableFuture<Void> startPickFromOneRepo(String repo, String pid) {
        return supplyAsync(() -> repoService.isAvailable(repo))
                .thenCompose(this::pick)
                .thenAccept(this::allocateStock)
                .thenRun(this::isAllocatedFully)
                ;
    }

    /** pick up stock based on repo's availability. */
    protected CompletableFuture<Stock> pick(RepoService.Availability a) {
        if (!a.available) {
            CompletableFuture<Stock> dummy = new CompletableFuture<Stock>();
            dummy.complete(new Stock(a.repo, 0));
            return dummy;
        } else {
            return supplyAsync(() -> stockService.pick(a.repo, pid));
        }
    }

    /** allocate stock. */
    protected void allocateStock(Stock stock) {
        queriedQ.addAndGet(stock.count);
        logger.log("仓库 %s 已分配 %d", stock.repo, stock.count);
    }

    /** check if all stocks are allocated enough. If yes, stop process. */
    protected boolean isAllocatedFully() {
        final int i = queriedQ.get();
        if (i >= q) {
            logger.log("%d >= %d", i, q);
            allocated.complete(true);
        }
        return i >= q;
    }

    /** complete allocation process. */
    protected void completeAllocate(Void v, Throwable e) {
        if (e != null) {
            e.printStackTrace();
        }else if (!allocated.isDone()) {
            allocated.complete(false);
            logger.log("没有充足的库存.");
        }
    }
}