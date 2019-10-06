package com.qinzx.demo.future;

/**
 * @ClassName: Util
 * @Author qinzx
 * @Date 2019/07/11 17:52
 * @Copyright (C) 杭州同基汽车科技有限公司
 */
import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
public interface Util {
    Logger logger = Logger.getInstance();
    //初始库存数量
    int q = 5;
    /**
     * 仓库列表
     */
    Set<String> repos = new HashSet<>();
    String oid = "qinzx";
    String pid = "Samsung S10";
    Random r = new SecureRandom(ByteBuffer.allocate(4).putInt(LocalTime.now().getNano()).array());

    static void delay(int base, int random) {
        try {
            Thread.sleep(base + r.nextInt(random));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}