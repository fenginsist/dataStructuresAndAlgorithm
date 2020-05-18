package com.feng.ch10_search;

import java.util.Arrays;

/*
 * 差值查找算法
 *
 * */
public class S3_InsertValueSearch {

    public static void main(String[] args) {
        int[] array = new int[100];
        for (int i = 0; i < 100; i++) {
            array[i] = i + 1;
        }
        System.out.println("数组为：");
        System.out.println(Arrays.toString(array));

        System.out.println();
        int i = insertValueSearch(array, 0, array.length - 1, 66);
        if (-1 == i) {
            System.out.println("找不到");
        } else {
            System.out.println("找到了，下标为：" + i);
        }
    }

    public static int insertValueSearch(int[] array, int left, int right, int findVal) {
        System.out.println("此句话每打印几次，说明调用查询方法几次");
        /*
         * 注意： left > right 和 findVal < array[0] 和 findVal > array[array.length-1] 必须需要
         * 否则我们得到的 mid 可能 越界
         * */
        if (left > right || findVal < array[0] || findVal > array[array.length - 1]) {
            return -1;
        }

        // 求出 mid
        int mid = left + (right - left) * (findVal - array[left]) / (array[right] - array[left]);

        int midVal = array[mid];

        if (findVal > midVal) { // 向右递归
            return insertValueSearch(array, mid + 1, right, findVal);
        } else if (findVal < midVal) { // 向左递归
            return insertValueSearch(array, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }
}
