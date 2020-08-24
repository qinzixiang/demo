package com.algorithm.part1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * 基础题目选解：
 * 1.学会用常量表简化代码
 * 2.信息学会用状态变量辅助字符串读入
 */
public class Primary {
    public static void main(String[] args) throws IOException {
//        wertyu();
        switchText();
    }

    /**
     * 将字符转换为键盘上前一位的字符：
     * 输入一个错位后敲出的字符串，输出打字员本来想打出的句子。
     * 样例输入：o s, gomr ypfsu/
     * 样例输出：i am fine today.
     */
    static void wertyu() {
        Scanner scanner = new Scanner(System.in);
        char[] list = new char[]{'`', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '-', '=',
                'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', '[', ']', '\\', 'a', 's', 'd', 'f',
                'g', 'h', 'j', 'k', 'l', ';', '\'', 'z', 'x', 'c', 'v', 'b', 'n', 'm', ',', '.', '/'};
        String c = null;
        System.out.println("输入字符, 按下 'q' 键退出。");
        do {
            c = scanner.nextLine();
            char[] chars = c.toCharArray();
            for (char alp : chars) {
                for (int i = 0; i < list.length; i++) {
                    if (list[i] == alp) {
                        if (i > 1) {
                            System.out.print(list[i - 1]);
                        } else if (i == 0) {
                            System.out.print(list[i]);
                        }
                    }
                }
                if (alp == ' ') {
                    System.out.print(" ");
                }
            }
        } while (!c.equals("q"));
    }

    /**
     * 将文本中不区分左右的双引号，替换为对应的左右双引号。
     * 由于环境配置的中文字符双引号字体左右双引号也不明显，故用中括号代替
     */
    static void switchText() {
        Scanner scanner = new Scanner(System.in);
        String c;
        do {
            c = scanner.nextLine();
            char[] chars = c.toCharArray();
            boolean flag = false;
            for (char aChar : chars) {
                if (aChar == '"') {
                    System.out.print((flag = !flag) ? "[" : "]");
                } else {
                    System.out.print(aChar);
                }
            }
        } while (!c.equals("q"));
    }
}
