package com.qinzx.demo.nio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 传统io，有两个阻塞，客户端连接后不关闭会一直占用连接，不支持并发
 * 需要借助多线程支持并发
 * @author qinzx
 * @date 2020/03/25 12:16
 */
public class BIOMysqlServer {
    static byte[] bs = new byte[1024];
    public static void main(String[] args){
        try {
            ServerSocket serverSocket = new ServerSocket(9098);
            while (true) {
                System.out.println("waiting connection");
                //阻塞 放弃CPU
                Socket clientSocket = serverSocket.accept();
                System.out.println("conn succ");
                System.out.println("wait data");
                //阻塞 放弃CPU
                clientSocket.getInputStream().read(bs);
                System.out.println("receive data");

                System.out.println(new String(bs));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}