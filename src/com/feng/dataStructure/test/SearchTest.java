package com.feng.dataStructure.test;

import org.junit.Test;

/*
 * 查找算法 归总
 * 1、线性查找
 * 2、二分查找
 * 3、差值查找
 * 4、斐波那契(黄金分割法)查找算法  ： 有难度
 *
 * 查找前的 数组  都为有序的
 * */
public class SearchTest {

    private int[] array = {1, 8, 10, 89, 1000, 1234};

    /*
     * 线性查找
     * */
    @Test
    public void seqSearchTest() {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 10) {
                System.out.println("查找到其下标为：" + i);
                break;
            }
        }
    }

    /*
     * 二分查找
     * */
    @Test
    public void binarySearchTest() {
        int resIndex = binarySearch(array, 0, array.length - 1, 1000);
        System.out.println("resIndex=" + resIndex);
        if (resIndex == -1) {
            System.out.println("没有找到");
        } else {
            System.out.println("找到，下标为=" + resIndex);
        }
    }

    public int binarySearch(int array[], int left, int right, int findValue) {


        if (left > right || findValue < array[0] || findValue > array[array.length - 1]) {
            return -1;
        }

        int mid = (left + right) / 2;
        int midValue = array[mid];

        if (findValue < midValue) {
            return binarySearch(array, left, mid - 1, findValue);
        }
        if (findValue > midValue) {
            return binarySearch(array, mid + 1, right, findValue);
        } else {
            return mid;
        }
    }

    /*
     * 二分查找 非递归
     * */
    @Test
    public void binarySearchTwoTest() {

        int findValue = 89;

        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;

            if (findValue>array[mid]){
                left = mid +1;
            }else if (findValue<array[mid]){
                right = right-1;
            }else {
                System.out.println(mid);
                break;
            }
        }
    }

    /*
     * 差值查找
     * */
    @Test
    public void insertValueSearchTest() {
        int i = insertValueSearch(array, 0, array.length - 1, 89);
        if (-1 == i) {
            System.out.println("找不到");
        } else {
            System.out.println("找到了，下标为：" + i);
        }
    }

    public int insertValueSearch(int array[], int left, int right, int findValue) {
        if (left > right || findValue < array[0] || findValue > array[array.length - 1]) {
            return -1;
        }

        int mid = left + (right - left) * (findValue - array[left]) / (array[right] - array[left]);
        int midValue = array[mid];

        if (findValue < midValue) {
            return binarySearch(array, left, mid - 1, findValue);
        }
        if (findValue > midValue) {
            return binarySearch(array, mid + 1, right, findValue);
        } else {
            return mid;
        }
    }
}
