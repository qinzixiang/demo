package com.qinzx.demo.concurrency.future;

/**
 * @author qinzx
 * @date 2019/07/11 17:51
 */

import static com.qinzx.demo.concurrency.future.Util.*;

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