package com.qinzx.demo.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

/**
 * @ClassName: TransportClientDemo
 * @Author qinzx
 * @Date 2019/10/22 9:55
 * @Copyright (C) 杭州同基汽车科技有限公司
 */
public class TransportClientDemo {

    public static void main(String[] args){
//        private TransportClient client = new PreBuiltTransportClient(new Settings());

        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http"),
                        new HttpHost("localhost", 9201, "http")));

    }
}