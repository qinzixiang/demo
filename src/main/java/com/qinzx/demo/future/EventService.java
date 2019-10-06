package com.qinzx.demo.future;

/**
 * @ClassName: EventService
 * @Author qinzx
 * @Date 2019/07/11 17:51
 * @Copyright (C) 杭州同基汽车科技有限公司
 */
import static com.qinzx.demo.future.Util.*;

public class EventService {

    /**
     * 监听订单取消事件
     * 延迟2000ms
     * @author  qinzx
     * @date  2019/7/12 15:14
     * @param order
     * @return  void
     */
    public void listenOrderCancel(String order) {
        delay(2000, 300);
        logger.log("cancelled with no reason：" + order);
    }
}