package com.feng.interview.revise;

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
        int[] arr2 = {3, 1, 2, 8, 5, 4};
        insertSort(arr2);
        System.out.println(Arrays.toString(arr2));
    }

    public void testStr(){
        String str = new String();
        StringBuffer sb = new StringBuffer();
        StringBuilder sbr = new StringBuilder();
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
        for (int i = 1; i < array.length; i++) {
            int insertValue = array[i]; // 待插入的值，无序表的最左边，其下标就是 i
            int insertIndex = i - 1;    // 有序表的最右边
            while (insertIndex >= 0 && insertValue < array[insertIndex]) {
                array[insertIndex + 1] = array[insertIndex];
                insertIndex--;
            }
            if (insertIndex + 1 != i) { // insertIndex+1 == i  ，就不需要进行赋值了，因为这时是正好的排序
                array[insertIndex + 1] = insertValue;
            }
        }
    }
}
