package com.feng.ch09_sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/*
 * 基数排序（稳定式排序）(桶排序)
 * 是空间换时间的经典算法
 *
 * 速度： 这也正是 空间换取时间的原因所在
 *      相当之快：8万数据： 134ms， 80万数据： 269ms ， 800万数据：1s 左右 ，
 *      当为8000万数据时，会报错：java.lang.OutOfMemoryError: Java heap space，内存溢出错误，
 *      因为 80000000 * 11（数组） *4（一个int 4个字节） /1024（k） /1024(m)/1024（g） = 3.3G ,会用到 3.3G内存，所以会内存溢出
 * */
public class S7_RadixSort {

    public static void main(String[] args) {
        int array[] = {53, 3, 542, 748, 14, 214};
        System.out.println("初始数组：");
        System.out.println(Arrays.toString(array));

//        deductionRadixSort(array); // 测试推导方法
        radixSort(array);       // 测试推导后 合成的方法

        System.out.println();
        System.out.println("排序后的数组：");
        System.out.println(Arrays.toString(array));

        // 测试 80000 个数据排序 所用的时间
        System.out.println();
        System.out.println("测试 8000000 个数据 采用基数排序 所用的时间:");
        testTime();   // 8万数据： 134ms， 80万数据： 269ms ， 800万数据：1s 左右 ， 8000万数据：内存溢出
    }

    /*
     * 测试一下 归并排序的速度, 给 80000 个数据，测试一下
     * */
    public static void testTime() {
        // 创建一个 80000个的随机的数组
        int array2[] = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            array2[i] = (int) (Math.random() * 8000000); // 生成一个[ 0, 8000000] 数
        }
//        System.out.println(Arrays.toString(array2)); // 不在打印，耗费时间太长


        long start = System.currentTimeMillis();  //返回以毫秒为单位的当前时间
        System.out.println("long start:" + start);
        Date date = new Date(start); // 上面的也可以不要，但是我想测试
        System.out.println("date:" + date);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        System.out.println("排序前的时间是=" + format.format(date));

        radixSort(array2);

        System.out.println();
        long end = System.currentTimeMillis();
        Date date2 = new Date(end); // 上面的也可以不要，但是我想测试
        System.out.println("排序后的时间是=" + format.format(date2));
        System.out.println("共耗时" + (end - start) + "毫秒");
        System.out.println("毫秒转成秒为：" + ((end - start) / 1000) + "秒");
    }

    /*
     * 根据下面的推导过程，我们可以得到最终的基数排序代码
     * */
    public static void radixSort(int[] array) {

        //1、得到数组中最大的数的位数
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        //2、得到最大数是几位数
        int maxLength = (max + "").length();
        /*
         * 定义一个二维数组，表示 10 个桶，每个桶就是一个一维数组
         * 说明
         * 1、二维数组包含 10 个一维数组
         * 2、为了防止在放入数的时候，数据溢出，则每个一维数组（桶），大小定位 array.length
         * 3、很明显，基数排序是使用空间换时间的经典算法
         * */
        int[][] bucket = new int[10][array.length];

        /*
         * 为了记录每个桶中，实际存放了多少个数据，我们定义一个一维数组来记录各个桶的每次放入的数据的个数
         * 可以这样理解：
         * 比如：bucketElementCounts[0] , 记录的就是 bucket[0] 桶的放入数据个数
         * */
        int[] bucketElementCounts = new int[10];

        // 这里使用循环将代码处理
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            /*
             * 每一轮（针对每个元素的 对应位 进行排序处理）第一次是个位，第二次是十位，第三次是百位，第四次是千位。。。。
             *      为了解决这个问题，在上面的 for 循环中，添加了一个 变量 n ，每次 * 10，因为下面需要取出相应的 个位、十位、百位。。。
             * 注意点：
             *  每轮处理后，需要将每个 bucketElementCounts[i] = 0 ！！！
             * */
            for (int j = 0; j < array.length; j++) {
                // 取出每个元素的 个位 的值
                int digitOfElement = (array[j] / n) % 10; // =3,第三个桶   //
                // 放入到对应的桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = array[j];  // 我觉得第二个 值 有问题
                bucketElementCounts[digitOfElement]++;
            }
            //按照这个桶的顺序(一维数组的下标依次取出数据，放入原来数组)
            int index = 0;
            // 遍历每一个桶，并将桶中的每一个数据，放入到原数组
            for (int k = 0; k < bucket.length; k++) {// 或者为：k < bucketElementCounts.length  bucket.length 都为 10
                // 如果桶中有数据，我们才放到原数据
                if (bucketElementCounts[k] != 0) { // bucket[i] !=0
                    // 循环该桶，即第 k 个桶（即第K 个数组中），放入  ；bucketElementCounts[k]： 为桶的几个数据
                    for (int j = 0; j < bucketElementCounts[k]; j++) { // bucket[i].length 是桶的长度，但是这里应该为 桶里数据的个数
                        // 取出元素放入到 array
                        array[index] = bucket[k][j];
                        index++;
                    }
                }
                // 第 i+1 轮处理后，需要将每个 bucketElementCounts[i] = 0 ！！！
                bucketElementCounts[k] = 0;
            }
//            System.out.println("第 " + (i + 1) + " 轮，对" + n + "位数的排序处理 array = " + Arrays.toString(array)); //
        }
    }

    /*
     * 基数排序
     * 使用逐步推导的方式
     * */
    public static void deductionRadixSort(int[] array) {

        /*
         * 定义一个二维数组，表示 10 个桶，每个桶就是一个一维数组
         * 说明
         * 1、二维数组包含 10 个一维数组
         * 2、为了防止在放入数的时候，数据溢出，则每个一维数组（桶），大小定位 array.length
         * 3、很明显，基数排序是使用空间换时间的经典算法
         * */
        int[][] bucket = new int[10][array.length];

        /*
         * 为了记录每个桶中，实际存放了多少个数据，我们定义一个一维数组来记录各个桶的每次放入的数据的个数
         * 可以这样理解：
         * 比如：bucketElementCounts[0] , 记录的就是 bucket[0] 桶的放入数据个数
         * */
        int[] bucketElementCounts = new int[10];

        /*
         * 第 1 轮（针对每个元素的个位进行排序处理）
         * 注意点：
         *  第 1 轮处理后，需要将每个 bucketElementCounts[i] = 0 ！！！
         * */
        for (int j = 0; j < array.length; j++) {
            // 取出每个元素的 个位 的值
            int digitOfElement = (array[j] / 1) % 10; // =3,第三个桶   //
            // 放入到对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = array[j];  // 我觉得第二个 值 有问题
            bucketElementCounts[digitOfElement]++;
        }
        //按照这个桶的顺序(一维数组的下标依次取出数据，放入原来数组)
        int index = 0;
        // 遍历每一个桶，并将桶中的每一个数据，放入到原数组
        for (int k = 0; k < bucket.length; k++) {// 或者为：k < bucketElementCounts.length  bucket.length 都为 10
            // 如果桶中有数据，我们才放到原数据
            if (bucketElementCounts[k] != 0) { // bucket[i] !=0
                // 循环该桶，即第 k 个桶（即第K 个数组中），放入  ；bucketElementCounts[k]： 为桶的几个数据
                for (int j = 0; j < bucketElementCounts[k]; j++) { // bucket[i].length 是桶的长度，但是这里应该为 桶里数据的个数
                    // 取出元素放入到 array
                    array[index] = bucket[k][j];
                    index++;
                }
            }
            // 第 1 轮处理后，需要将每个 bucketElementCounts[i] = 0 ！！！
            bucketElementCounts[k] = 0;
        }
        System.out.println("第 1 轮，对个位数的排序处理 array = " + Arrays.toString(array)); //[542, 53, 3, 14, 214, 748]

        /*
         * 第 2 轮（针对每个元素的个位进行排序处理）
         * 注意点：
         *      取出每个元素的 十位 的值：int digitOfElement = (array[j]/10) % 10
         * */
        for (int j = 0; j < array.length; j++) {
            // 取出每个元素的 十位 的值
            int digitOfElement = (array[j] / 10) % 10; // =3,第三个桶   //
            // 放入到对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = array[j];  // 我觉得第二个 值 有问题
            bucketElementCounts[digitOfElement]++;
        }
        //按照这个桶的顺序(一维数组的下标依次取出数据，放入原来数组)
        index = 0;
        // 遍历每一个桶，并将桶中的每一个数据，放入到原数组
        for (int k = 0; k < bucket.length; k++) {// 或者为：k < bucketElementCounts.length  bucket.length 都为 10
            // 如果桶中有数据，我们才放到原数据
            if (bucketElementCounts[k] != 0) { // bucket[i] !=0
                // 循环该桶，即第 k 个桶（即第K 个数组中），放入  ；bucketElementCounts[k]： 为桶的几个数据
                for (int j = 0; j < bucketElementCounts[k]; j++) { // bucket[i].length 是桶的长度，但是这里应该为 桶里数据的个数
                    // 取出元素放入到 array
                    array[index] = bucket[k][j];
                    index++;
                }
            }
            // 第 2 轮处理后，需要将每个 bucketElementCounts[i] = 0 ！！！
            bucketElementCounts[k] = 0;
        }
        System.out.println("第 2 轮，对十位数的排序处理 array = " + Arrays.toString(array)); // [3, 14, 214, 542, 748, 53]


        /*
         * 第 3 轮（针对每个元素的个位进行排序处理）
         * 注意点：
         *      取出每个元素的 百位 的值：int digitOfElement = (array[j]/100) % 10
         * */
        for (int j = 0; j < array.length; j++) {
            // 取出每个元素的 百位 的值
            int digitOfElement = (array[j] / 100) % 10; // =3,第三个桶   //
            // 放入到对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = array[j];  // 我觉得第二个 值 有问题
            bucketElementCounts[digitOfElement]++;
        }
        //按照这个桶的顺序(一维数组的下标依次取出数据，放入原来数组)
        index = 0;
        // 遍历每一个桶，并将桶中的每一个数据，放入到原数组
        for (int k = 0; k < bucket.length; k++) {// 或者为：k < bucketElementCounts.length  bucket.length 都为 10
            // 如果桶中有数据，我们才放到原数据
            if (bucketElementCounts[k] != 0) { // bucket[i] !=0
                // 循环该桶，即第 k 个桶（即第K 个数组中），放入  ；bucketElementCounts[k]： 为桶的几个数据
                for (int j = 0; j < bucketElementCounts[k]; j++) { // bucket[i].length 是桶的长度，但是这里应该为 桶里数据的个数
                    // 取出元素放入到 array
                    array[index] = bucket[k][j];
                    index++;
                }
            }
            // 第 3 轮处理后，需要将每个 bucketElementCounts[i] = 0 ！！！
            bucketElementCounts[k] = 0;
        }
        System.out.println("第 3 轮，对百位数的排序处理 array = " + Arrays.toString(array)); // [3, 14, 214, 542, 748, 53]
    }
}
