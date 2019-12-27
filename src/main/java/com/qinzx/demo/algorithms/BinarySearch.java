package com.qinzx.demo.algorithms;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * 二分查找
 *
 * @ClassName: BinarySearch
 * @Author qinzx
 * @Date 2019/12/23 16:26
 * @Copyright (C) 杭州同基汽车科技有限公司
 */
public class BinarySearch {
    public static int rank(int key, int[] a) {
        //数组必须是有序的
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            //被查找的键要么不存在，要么必然存在于a[lo..hi]中
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) {
                hi = mid - 1;
            } else if (key > a[mid]) {
                lo = mid + 1;
            } else{
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args){
        int[] whitelist = In.readInts(args[0]);
        Arrays.sort(whitelist);
        while (!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            if (rank(key, whitelist) < 0) {
                StdOut.println(key);
            }
        }
    }
}