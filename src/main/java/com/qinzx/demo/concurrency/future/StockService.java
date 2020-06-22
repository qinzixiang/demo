package com.qinzx.demo.concurrency.future;

import com.google.common.collect.ImmutableMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static com.qinzx.demo.concurrency.future.Util.*;
/**
 * @author zhhe.me@gmail.com
 * @since 9/9/2018
 */
public class StockService {
    private Map<String, Integer> stocks = new HashMap<>();

    /**
     * 查总库存
     * 随机延迟100～200ms
     * @author  qinzx
     * @date  2019/7/12 15:08
     * @param prd   商品
     * @return  int
     */
    public int query(String prd) {
        delay(100, 100);
        //生成总库存数量
        int q2 = (q-q/4-1) + r.nextInt(q);
        generateStock(q2);
        return q2;
    }

    /**
     * 查/锁定分库库存（含货架）
     * 随机延迟500~2500ms
     * @author  qinzx
     * @date  2019/7/12 15:09
     * @param repo
     * @param prd
     * @return  com.qinzx.demo.concurrency.future.Stock
     */
    public Stock pick(String repo, String prd) {
        final Stock stock = new Stock(repo, stocks.get(repo));
        delay(500, 2000);
        return stock;
    }

    /**
     * 生成商品在各个分库的库存数量
     * @author  qinzx
     * @date  2019/7/12 15:10
     * @param q 总库存数量
     * @return  void
     */
    private void generateStock(int q) {
        final Iterator<String> iter = repos.iterator();
        if (repos.size() == 1) {
            stocks = ImmutableMap.of(iter.next(), q);
        } else {
            stocks = ImmutableMap.of(
                    iter.next(), q / 5,
                    iter.next(), q / 4,
                    iter.next(), q / 3,
                    iter.next(), (q - q / 5 - q / 4 - q / 3)
            );
        }
        logger.log("stocks: [total=%d, repos=%s]", q, stocks);
    }
}