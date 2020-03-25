package com.qinzx.demo.nio;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author qinzx
 * @date 2020/03/25 12:19
 * @Copyright (C) 杭州同基汽车科技有限公司
 */
public class BIOMysqlClient {

    public static void main(String[] args){
        try {
            Socket socket = new Socket("127.0.0.1", 9098);
            Scanner scanner = new Scanner(System.in);
            String next = scanner.nextLine();

            socket.getOutputStream().write(next.getBytes());
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}