package com.qinzx.demo.concurrency.future;

/**
 * Not ThreadSafe and just for demo.
 * @author qinzx
 * @date 2019/07/11 17:53
 */
import com.google.common.collect.ImmutableSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.CompletableFuture;

import static com.qinzx.demo.concurrency.future.Util.*;

import static java.util.concurrent.CompletableFuture.allOf;
public class MultiRepoPackageService extends AbstractPackageService {

    public static void main(final String... args) throws Exception {
        final boolean result = new MultiRepoPackageService().pack(oid, pid);
        logger.log("分配: %s", result);
    }

    @Override
    protected Collection<String> getRepos() {
        return ImmutableSet.of("杭州", "武汉", "上海", "苏州");
    }

    @Override
    protected void startPick(String pid) {
        final CompletableFuture[] queries = new CompletableFuture[repos.size()];
        final Iterator<String> iter = repos.iterator();
        for (int i = 0; i < queries.length; i++) {
            queries[i] = startPickFromOneRepo(iter.next(), pid);
        }

        allOf(queries).whenCompleteAsync(this::completeAllocate);
    }
}