package com.feng.ch09_sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/*
 * 冒泡排序
 * 时间复杂度为 O(n^2)
 * 思想：
 *  第一次 前两个数进行比较，将较大的数 往后移，直到最大数放到数组最后面。也就是冒泡到最后面。
 *  第二次 依次进行比较，将第二大的数 往后移，直到放到最大数的左边，
 *  依次比较 后移 即可。
 * 学习分为三步：
 * 1、每一步进行推导。 deductionBubbleSort(int[] array)方法
 * 2、找到规律，合成两个for循环。 bubbleSort(int[] array)方法，并进行优化：比较后没有交换的不进行循环一趟
 * 3、测试 80000 个数据排序所需要的时间。 testTime()方法         80000 个数据 26S 左右
 * */
public class S1_BubbleSort {
    public static void main(String[] args) {
        int[] array = {3, 9, -1, 10, -2};

        System.out.println("排序前：");
        System.out.println(Arrays.toString(array));

        System.out.println();
        System.out.println("排序后：");
//        deductionBubbleSort(array);  // 推导的方法。
        int[] ints = bubbleSort(array); // 推导后的方法
        System.out.println(Arrays.toString(ints));

        // 测试 80000 个数据排序 所用的时间
        System.out.println();
        System.out.println("测试 80000 个数据 采用冒泡排序 所用的时间:");
        testTime();
    }

    /*
     * 测试一下 冒泡排序的速度O(n^2), 给 80000 个数据，测试一下
     * */
    public static void testTime() {
        // 创建一个 80000个的随机的数组
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

        bubbleSort(array2);

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
    public static int[] bubbleSort(int[] array) {
        int temporary = 0; // 临时变量
        boolean flag = false;
        for (int i = 0; i < array.length - 1; i++) { // 总共为 4 趟 大遍历。
            for (int j = 0; j < array.length - 1 - i; j++) { // 每次循环 为 进行比较循环，每次大循环之后，确定后大的数之后，在循环的次数 就会减 1
                // 如果前面的数比后面的数大，则交换
                if (array[j] > array[j + 1]) {
                    flag = true;
                    temporary = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temporary;
                }
            }
//            System.out.println("第" + (i + 1) + "躺排序后的数组");
//            System.out.println(Arrays.toString(array));
            if (!flag) { // 在一趟排序中，一次交换都没有发生过
                break;
            } else {
                flag = false; // 重置flag!!!, 进行下次判断
            }
        }
        return array;
    }

    /*
     * 推导的方法
     * */
    public static int[] deductionBubbleSort(int[] array) {
        /*
         * 为了容易理解，把冒泡排序的演变过程展示一下：
         * */
        //1、第一趟排序，就是将最大的数排在最后
        int temporary = 0; // 临时变量
        for (int i = 0; i < array.length - 1 - 0; i++) { // 总共为 4次 大循环遍历
            // 如果前面的数比后面的数大，则交换
            if (array[i] > array[i + 1]) {
                temporary = array[i];
                array[i] = array[i + 1];
                array[i + 1] = temporary;
            }
        }

        System.out.println("每步演练的过程：：");
        System.out.println("第一趟排序后的数组：");
        System.out.println(Arrays.toString(array));

        // 第二趟排序，就在将第二大的数排在倒数第二位
        for (int i = 0; i < array.length - 1 - 1; i++) { // 总共为 4次 大循环遍历
            // 如果前面的数比后面的数大，则交换
            if (array[i] > array[i + 1]) {
                temporary = array[i];
                array[i] = array[i + 1];
                array[i + 1] = temporary;
            }
        }
        System.out.println("第二趟排序后的数组：");
        System.out.println(Arrays.toString(array));

        // 第三趟排序，就在将第二大的数排在倒数第三位
        for (int i = 0; i < array.length - 1 - 2; i++) { // 总共为 4次 大循环遍历
            // 如果前面的数比后面的数大，则交换
            if (array[i] > array[i + 1]) {
                temporary = array[i];
                array[i] = array[i + 1];
                array[i + 1] = temporary;
            }
        }
        System.out.println("第三趟排序后的数组：");
        System.out.println(Arrays.toString(array));

        // 第四趟排序，就在将第二大的数排在倒数第四位
        for (int i = 0; i < array.length - 1 - 3; i++) { // 总共为 4次 大循环遍历
            // 如果前面的数比后面的数大，则交换
            if (array[i] > array[i + 1]) {
                temporary = array[i];
                array[i] = array[i + 1];
                array[i + 1] = temporary;
            }
        }
        System.out.println("第四趟排序后的数组：");
        System.out.println(Arrays.toString(array));

        return array;
    }


}
