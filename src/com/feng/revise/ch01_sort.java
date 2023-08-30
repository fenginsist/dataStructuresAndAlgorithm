package com.feng.revise;

import java.util.Arrays;

/**
 * win git test
 */
public class ch01_sort {
    public static void main(String[] args) {
        System.out.println("--------------------------------冒泡排序");
        int[] arrays = {1, 3, 2, 8, 5, 4};
        int[] ints = bubbleSort(arrays);
        System.out.println(Arrays.toString(ints));
        System.out.println("--------------------------------选择排序");
        int[] arr1 = {1, 3, 2, 8, 5, 4};
        selectSort(arr1);
        System.out.println(Arrays.toString(arr1));
        System.out.println("--------------------------------插入排序");
        int[] arr2 = {1, 3, 2, 8, 5, 4};
        insertSort(arr2);
        System.out.println(Arrays.toString(arr2));
    }

    /**
     * 冒泡排序
     * @param array
     * @return
     */
    public static int[] bubbleSort(int[] array){
        int temp;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        return array;
    }

    /**
     * 选择排序
     * @param array
     */
    public static void selectSort(int[] array) {

        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            int min = array[i];
            for (int j = i + 1; j < array.length; j++) {
                if (min > array[j]) {
                    minIndex = j;
                    min = array[j];
                }
            }
            if (minIndex != i) {
                array[minIndex] = array[i];
                array[i] = min;
            }
        }

    }


    /**
     * 插入排序
     * @param array
     */
    public static void insertSort(int[] array) {

    }
}
