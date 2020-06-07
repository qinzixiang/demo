package com.qinzx.demo.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * NIO 示例
 * @author qinzx
 * @date 2020/03/25 12:43

 */
public class TomcatServer {
    static ByteBuffer byteBuffer = ByteBuffer.allocate(512);
    static List<SocketChannel> channelList = new ArrayList<>();
    public static void main(String[] args){
        try {
            ServerSocketChannel serverSocket = ServerSocketChannel.open();
            InetSocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 9098);
            serverSocket.bind(socketAddress);
            //设置非阻塞
            serverSocket.configureBlocking(false);
            while (true) {
                for (SocketChannel socketChannel : channelList) {
                    int read = socketChannel.read(byteBuffer);
                    if (read > 0) {
                        System.out.println("read------------" + read);
                        byteBuffer.flip();
                        byte[] bs = new byte[read];
                        byteBuffer.get(bs);
                        String content = new String(bs);
                        System.out.println(content);
                        byteBuffer.clear();
                    }
                }
                SocketChannel accept = serverSocket.accept();
                if (accept != null) {
                    System.out.println("conn succ");
                    accept.configureBlocking(false);
                    channelList.add(accept);
                    System.out.println(channelList.size() + "list--size");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}