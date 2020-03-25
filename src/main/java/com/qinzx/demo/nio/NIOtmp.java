package com.qinzx.demo.nio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @author qinzx
 * @date 2020/03/25 12:32
 * @Copyright (C) 杭州同基汽车科技有限公司
 */
public class NIOtmp {
    static byte[] bs = new byte[1024];
    static List<Socket> socketList = new ArrayList<>();
    public static void main(String[] args){
        try {
            ServerSocket serverSocket = new ServerSocket(9098);
            while (true) {
                //阻塞
                Socket clientSocket = serverSocket.accept();
                socketList.add(clientSocket);
                //阻塞
                for (Socket socket : socketList) {
                    int read = socket.getInputStream().read(bs);
                    if (read > 0) {
                        System.out.println(new String(bs));
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}