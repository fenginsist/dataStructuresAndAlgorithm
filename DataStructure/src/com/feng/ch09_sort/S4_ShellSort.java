package com.feng.ch09_sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/*
 * 希尔排序：
 *      对插入排序的 进一步优化
 * 使用到了 分组，并且缩小增量的 思想。
 *      第一次分组，gap(分组的次数)=【数组大小】/2,
 *      第二次分组，gap = （第一次分组次数）/2,
 *      第三次分组，gap = （上一次分组次数）/2,
 *
 * 有两种方式：
 * 第一种：交换式
 *      里面使用了冒泡排序的 交换思想
 *      效率低，容易理解  80000数据排序使用 14-19 S
 *
 * 第二种：移位式
 *      里面使用了 简单插入排序的 插入移位思想
 *      效率高，不易理解  8万数据： 1S ， 80万数据：  1S， 800万数据：4S
 * */
public class S4_ShellSort {
    public static void main(String[] args) {
        int[] array = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        System.out.println("初始数组：");
        System.out.println(Arrays.toString(array));

        System.out.println();
        System.out.println("排序后");
//        deductionShellSortBychange(array);
        int[] ints = shellSortByChange(array); // 交换式
        System.out.println(Arrays.toString(ints));


        // 测试 80000 个数据排序 所用的时间
        System.out.println();
        System.out.println("测试 80000 个数据 采用希尔排序（交换式） 所用的时间:");
        testTime();   // 交互式：19S, 移位式：1S
    }

    /*
     * 测试一下 希尔排序的速度O(n^2), 给 80000 个数据，测试一下
     * */
    public static void testTime() {
        // 创建一个 80000个的随机的数组
        int array2[] = new int[80000];
        for (int i = 0; i < 80000; i++) {
            array2[i] = (int) (Math.random() * 80000); // 生成一个[ 0, 8000000] 数
        }
//        System.out.println(Arrays.toString(array2)); // 不在打印，耗费时间太长


        long start = System.currentTimeMillis();  //返回以毫秒为单位的当前时间
        System.out.println("long start:" + start);
        Date date = new Date(start); // 上面的也可以不要，但是我想测试
        System.out.println("date:" + date);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        System.out.println("排序前的时间是=" + format.format(date));

        shellSortByChange(array2); // 交互式
//        shellSortByMove(array2);   // 移位式

        System.out.println();
        long end = System.currentTimeMillis();
        Date date2 = new Date(end); // 上面的也可以不要，但是我想测试
        System.out.println("排序后的时间是=" + format.format(date2));
        System.out.println("共耗时" + (end - start) + "毫秒");
        System.out.println("毫秒转成秒为：" + ((end - start) / 1000) + "秒");
    }

    /*
     * 对交换式的希尔排序进行优化 -》》移位法
     * */
    public static int[] shellSortByMove(int[] array) {
        // 增量gap, 并逐步的缩小增量
        for (int gap = array.length / 2; gap > 0; gap /= 2) { // 共分 3 组
            // 从第 gap 个元素，逐个对其所在的组进行直接插入排序
            for (int i = gap; i < array.length; i++) {
                // 这里使用了 前面学习的 直接插入排序
                int j = i;
                int temp = array[j];
                if (array[j] < array[j - gap]) {
                    while ((j - gap) >= 0 && temp < array[j - gap]) {
                        //移动
                        array[j] = array[j - gap];
                        j -= gap;
                    }
                    //当退出while后，就给temp找到插入的位置
                    array[j] = temp;
                }
            }
        }
        return array;
    }

    /*
     * 交换式排序 整合
     * */
    public static int[] shellSortByChange(int[] array) {
        int temporary = 0;
        int count = 0;
        for (int gap = array.length / 2; gap > 0; gap /= 2) {  // 三次分组。
            for (int i = gap; i < array.length; i++) {    // 有几组，循环遍历几次，也就比较几次。
                // 遍历各组中所有的元素（共 gap 组，每组有  个元素），步长为 gap
                // 这里使用了交换：也就是前面学习的 冒泡排序的思想
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (array[j] > array[j + gap]) {
                        temporary = array[j];
                        array[j] = array[j + gap];
                        array[j + gap] = temporary;
                    }
                }
            }
//            System.out.println("希尔排序第" + (++count) + "轮=" + Arrays.toString(array));
        }
        return array;
    }

    /*
     * 希尔排序：采用交换式
     * 使用逐步推导的方式
     * */
    public static int[] deductionShellSortBychange(int[] array) {
        // 临时变量，存放交换的数据
        int temporary = 0;

        /*
         * 希尔排序的第 1 轮排序
         * */
        System.out.println("第 1 轮排序");
        // 因为第 1 轮排序，是将 10 个数据分成了5组 ，所以要比较 5 次， i 从 5 开始
        for (int i = 5; i < array.length; i++) { // 共遍历 5 次，即 比较 5 次
            // 遍历各组中所有的元素（共 5 组，每组有2个元素），步长为 5
            for (int j = i - 5; j >= 0; j -= 5) {  // 仅比较一次，走一次逻辑，就知道啦
                if (array[j] > array[j + 5]) {
                    temporary = array[j];
                    array[j] = array[j + 5];
                    array[j + 5] = temporary;
                }
            }
        }
        System.out.println(Arrays.toString(array));

        /*
         * 希尔排序的第 2 轮排序
         * */
        System.out.println("第 2 轮排序");
        // 因为第 1 轮排序，是将 10 个数据分成了  5/2 = 2组
        for (int i = 2; i < array.length; i++) {
            // 遍历各组中所有的元素（共 5 组，每组有2个元素），步长为 5
            for (int j = i - 2; j >= 0; j -= 2) {
                if (array[j] > array[j + 2]) {
                    temporary = array[j];
                    array[j] = array[j + 2];
                    array[j + 2] = temporary;
                }
            }
        }
        System.out.println(Arrays.toString(array));

        /*
         * 希尔排序的第 3 轮排序
         * */
        System.out.println("第 3 轮排序");
        // 因为第 3 轮排序，是将 10 个数据分成了  2/2 = 1组
        for (int i = 1; i < array.length; i++) {
            // 遍历各组中所有的元素（共 5 组，每组有2个元素），步长为 5
            for (int j = i - 1; j >= 0; j -= 1) {
                if (array[j] > array[j + 1]) {
                    temporary = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temporary;
                }
            }
        }
        System.out.println(Arrays.toString(array));

        return array;
    }
}
