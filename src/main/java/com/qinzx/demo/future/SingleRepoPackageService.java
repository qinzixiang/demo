package com.qinzx.demo.future;

/**
 * Not ThreadSafe and just for demo.
 * @ClassName: SingleRepoPackageService
 * @Author qinzx
 * @Date 2019/07/11 17:53
 * @Copyright (C) 杭州同基汽车科技有限公司
 */
import com.google.common.collect.ImmutableSet;
import java.util.Collection;

import static com.qinzx.demo.future.Util.*;

public class SingleRepoPackageService extends AbstractPackageService {

    private final String repo = "杭州";

    public static void main(String... args) throws Exception {
        final boolean result = new SingleRepoPackageService().pack(oid, pid);
        logger.log("allocated: %s", result);
    }

    @Override
    protected Collection<String> getRepos() {
        //生成不可变的仓库列表
        return ImmutableSet.of(repo);
    }

    @Override
    protected void startPick(String pid) {
        startPickFromOneRepo(repo, pid).whenComplete(this::completeAllocate);
    }
}