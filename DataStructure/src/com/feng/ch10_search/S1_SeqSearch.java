package com.feng.ch10_search;

/*
 * 线性查找
 *  1、没有顺序的数组
 *  2、 直接循环遍历、比较
 * */
public class S1_SeqSearch {

    public static void main(String[] args) {
        int array[] = {1, 9, 11, -1, 34, 89}; // 没有顺序的数组
        int i = seqSearch(array, 11);
        if (-1 == i) {
            System.out.println("没有找到");
        } else {
            System.out.println("找到，下标为=" + i);
        }
    }

    /*
     * 这里我们实现的线性查找是找到一个满足条件的值，就返回
     * @param array
     * @param num
     * @return
     * */
    public static int seqSearch(int[] array, int num) {

        for (int i = 0; i < array.length; i++) {
            if (num == array[i]) {
                return i;
            }
        }
        return -1;
    }
}
