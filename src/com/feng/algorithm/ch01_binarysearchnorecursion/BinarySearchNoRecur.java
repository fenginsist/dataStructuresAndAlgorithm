package com.feng.algorithm.ch01_binarysearchnorecursion;

public class BinarySearchNoRecur {

    public static void main(String[] args) {
        //测试
        int[] arr = {1, 3, 8, 10, 11, 67, 100};
        int index = binarySearch(arr, 10);
        System.out.println("index=" + index);
    }

    /**
     * 二分查找 非递归实现
     *
     * @param arrary
     * @param findValue
     * @return
     */
    public static int binarySearch(int arrary[], int findValue) {

        int left = 0;
        int right = arrary.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (arrary[mid] == findValue) {
                return mid;
            } else if (arrary[mid] > findValue) {
                right = mid - 1; // 需要向左边查找
            } else if (arrary[mid] < findValue) {
                left = mid + 1;
            }
        }
        return -1;
    }
}
