package com.feng.ch09_sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/*
 * 选择排序
 * 时间复杂度： O(n^2)
 * 思路：
 *
 * 学习分为三步：
 * 1、每一步进行推导。 deductionSelectSort(int[] array)方法
 *    1.1 首先定义两个变量，minIndex、 min，分别表示 最小值下标、最小值
 *    2.1 假定第一个数据为最小值，然后拿着这个数据和其他数据比较（使用遍历）
 *    1.2 找到最小值后，将其与数组的第一个值进行互换（默认排序是从小到大）
 * 2、找到规律，合成两个for循环。 selectSort(int[] array)方法，并进行优化：
 * 3、测试 80000 个数据排序所需要的时间。 testTime()方法         5S 左右， 比冒泡快
 * */
public class S2_SelectSort {
    public static void main(String[] args) {
        int array[] = new int[]{101, 34, 119, 1};
        System.out.println("排序前：");
        System.out.println(Arrays.toString(array));

        System.out.println();
        System.out.println("排序后：");
//        deductionSelectSort(array); // 推导 的过程
        int[] ints = selectSort(array); // 推导后的方法
        System.out.println(Arrays.toString(ints));

        // 测试 80000 个数据排序 所用的时间
        System.out.println();
        System.out.println("测试 80000 个数据 采用选择排序 所用的时间:");
        testTime(); // 5S
    }

    /*
     * 测试一下 选择排序的速度O(n^2), 给 80000 个数据，测试一下
     * */
    public static void testTime() {
        // 创建一个 80000个的随机的数组
        System.out.println();
        int array2[] = new int[80000];
        for (int i = 0; i < 80000; i++) {
            array2[i] = (int) (Math.random() * 8000000); // 生成一个[ 0, 8000000] 数
        }
//        System.out.println(Arrays.toString(array2)); // 不在打印，耗费时间太长


        long start = System.currentTimeMillis();  //返回以毫秒为单位的当前时间
        System.out.println("long start:" + start);
        Date date = new Date(start); // 上面的也可以不要，但是我想测试
        System.out.println("date:" + date);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        System.out.println("排序前的时间是=" + format.format(date));

        selectSort(array2);

        System.out.println();
        long end = System.currentTimeMillis();
        Date date2 = new Date(end); // 上面的也可以不要，但是我想测试
        System.out.println("排序后的时间是=" + format.format(date2));
        System.out.println("共耗时" + (end - start) + "毫秒");
        System.out.println("毫秒转成秒为：" + ((end - start) / 1000) + "秒");
    }

    /*
     * 推导后，合成的方法【重点掌握】
     * */
    public static int[] selectSort(int[] array) {
        /*
         * 在 deductionSelectSort()方法 推导的过程，发现了规律，因此，可以使用for循环解决。
         * 4个数据， 每次循环比较，找到最小值，放到最前面。共需要 3 次。  而每次找的时候 需要比较循环 3 次。正好为 【数据的大小-1】
         * */
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            int min = array[i];
            for (int j = i + 1; j < array.length; j++) {
                if (min > array[j]) {  // 说明假定的最小值，并不是最小
                    minIndex = j; // 重置 min
                    min = array[j]; // 重置 minIndex
                }
            }
            // 将最小值，放在 array[i], 即交换: 交换时 做一个判断，如果 比较后，不需要赋值，那么下面两行不在执行，否则就重复了
            if (minIndex != i) {
                array[minIndex] = array[i];
                array[i] = min;
            }
//            System.out.println("第一轮后：");
//            System.out.println(Arrays.toString(array));
        }
        return array;
    }

    /*
     * 推导的方法
     * */
    public static void deductionSelectSort(int[] array) {
        /*
         * 使用逐步推导的方式来，讲解选择排序
         * 第1轮
         * 原始的数组；101, 34, 119, 1
         * 第一轮排序：[1, 34, 119, 101]
         * 算法 先简单--》做复杂，就是可以把一个复杂的算法，拆分成简单的问题-》逐步解决
         * */

        // 第一轮排序后：
        int minIndex = 0;
        int min = array[0];
        for (int i = 0 + 1; i < array.length; i++) {
            if (min > array[i]) {  // 说明假定的最小值，并不是最小
                minIndex = i; // 重置 min
                min = array[i]; // 重置 minIndex
            }
        }
        // 将最小值，放在 array[0], 即交换: 交换时 做一个判断，如果 比较后，不需要赋值，那么下面两行不在执行，否则就重复了
        if (minIndex != 0) {
            array[minIndex] = array[0];
            array[0] = min;
        }
        System.out.println("第一轮后：");
        System.out.println(Arrays.toString(array));


        // 第二轮排序后：
        minIndex = 1;
        min = array[1];
        for (int i = 1 + 1; i < array.length; i++) {
            if (min > array[i]) {  // 说明假定的最小值，并不是最小
                minIndex = i; // 重置 min
                min = array[i]; // 重置 minIndex
            }
        }
        // 将最小值，放在 array[0], 即交换: 交换时 做一个判断，如果 比较后，不需要赋值，那么下面两行不在执行，否则就重复了
        if (minIndex != 1) {
            array[minIndex] = array[1];
            array[1] = min;
        }

        System.out.println("第二轮后：");
        System.out.println(Arrays.toString(array));

        // 第三轮排序后：
        minIndex = 2;
        min = array[2];
        for (int i = 2 + 1; i < array.length; i++) {
            if (min > array[i]) {  // 说明假定的最小值，并不是最小
                minIndex = i; // 重置 min
                min = array[i]; // 重置 minIndex
            }
        }
        // 将最小值，放在 array[0], 即交换: 交换时 做一个判断，如果 比较后，不需要赋值，那么下面两行不在执行，否则就重复了
        array[minIndex] = array[2];
        array[2] = min;
        System.out.println("第三轮后：");
        System.out.println(Arrays.toString(array));
    }
}
