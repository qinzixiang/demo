package com.qinzx.demo.future;

/**
 * @ClassName: RepoService
 * @Author qinzx
 * @Date 2019/07/11 17:51
 * @Copyright (C) 杭州同基汽车科技有限公司
 */

import static com.qinzx.demo.future.Util.*;

public class RepoService {

    public Availability isAvailable(String repo) {
        delay(0, 100);
        final Availability availability = new Availability(repo, r.nextInt(5) != 0);
        logger.log("repo %s %s available", repo, availability.available ? "is" : "NOT");
        return availability;
    }

    public static class Availability {
        String repo;
        boolean available;

        public Availability(String repo, boolean available) {
            this.repo = repo;
            this.available = available;
        }
    }
}