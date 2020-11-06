package com.feng.ch09_sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/*
 * 归并排序
 *
 * 速度测试： 8万数据： 117Ms， 80万数据： 395Ms， 800万数据：2-3S
 * */
public class S6_MergetSort {

    public static void main(String[] args) {
        int[] array = {8, 4, 5, 7, 1, 3, 6, 2};  // 8-> merge 7次， 80000-> merge 80000-1=7999次

        int[] temp = new int[array.length];
        System.out.println("原始数组：");
        System.out.println(Arrays.toString(array));

        System.out.println();
        System.out.println("排序后：");
        mergetSort(array, 0, array.length - 1, temp);
        System.out.println(Arrays.toString(array));

        // 测试 80000 个数据排序 所用的时间
        System.out.println();
        System.out.println("测试 80000 个数据 采用归并排序（交换式） 所用的时间:");
        testTime();   // 8万数据： 1S不到， 80万数据： 1S 不到， 800万数据：2-3
    }

    /*
     * 测试一下 归并排序的速度, 给 80000 个数据，测试一下
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

        int[] temp = new int[array2.length];
        mergetSort(array2, 0, array2.length - 1, temp);   // 移位式

        System.out.println();
        long end = System.currentTimeMillis();
        Date date2 = new Date(end); // 上面的也可以不要，但是我想测试
        System.out.println("排序后的时间是=" + format.format(date2));
        System.out.println("共耗时" + (end - start) + "毫秒");
        System.out.println("毫秒转成秒为：" + ((end - start) / 1000) + "秒");
    }


    /*
     * 分 + 合的方法
     * */
    public static void mergetSort(int[] array, int left, int right, int[] temp) {
        if (left < right) {// 数据校验
            int mid = (left + right) / 2; // 中间索引
            // 向左递归 分解
            mergetSort(array, left, mid, temp);
            // 向右递归 分解
            mergetSort(array, mid + 1, right, temp);
            // 以上为分解，打印之后，与初始数组没区别
            // 以下这一句为合并
            merge(array, left, mid, right, temp);
        }
    }


    /*
     * 合并的方法
     *
     * @param array 排序的原始数组
     * @param left  左边有序序列的初始索引
     * @param mid   中间索引
     * @param right 右边索引
     * @param temp  做中转的数组
     * */
    public static void merge(int[] array, int left, int mid, int right, int[] temp) {
//        System.out.println("XXXX");
        int i = left; // 初始化 i, 左边有序序列的初始索引
        int j = mid + 1; // 初始化j, 右边有序序列的初始索引
        int t = 0; // 指向 temp 数组的 当前索引。

        // (一)
        // 先把左右两边（有序）的数据按照规则填充到 temp数组
        // 直到 左右两边的有序序列，有一边处理完毕为止
        while (i <= mid && j <= right) {
            /*
             * 如果 左边的有序序列 的当前元素，小于等于右边有序序列的 当前元素
             * 既然左边的当前元素，填充到 temp 数组
             * 然后 t++, i++
             * */
            if (array[i] <= array[j]) {
                temp[t] = array[i];
                t += 1;
                i += 1;
            } else {
                // 如果 右边的有序序列的当前元素 小于 左边有序序列的当前元素，则将右边的当前元素填充到 temp数组，然后 t++, j++
                temp[t] = array[j];
                t += 1;
                j += 1;
            }
        }

        /*
         * (二)
         * 当代码走到这里，说明 左边 或者 右边 ，已经有一方 有剩余的。
         * 把有 剩余数据 的一边的数据 依次全部填充到 temp
         * */
        // 左边 有序序列还有剩余的元素，就全部填充到 temp
        while (i <= mid) {
            temp[t] = array[i];
            t++;
            i++;
        }
        // 右边 有序序列还有剩余的元素，就全部填充到 temp
        while (j <= right) {
            temp[t] = array[j];
            t++;
            j++;
        }

        /*
         * (三)
         * 将 temp 数组的元素拷贝到 array
         * 注意，并不是每次都拷贝所有
         * */
        t = 0;
        int tempLeft = left;//
        // 第一次 合并： tempLeft = 0, right = 1, // tempLeft = 2, right = 3 // tempLeft = 0, right = 3
        // 最后一次，tempLeft = 0  right = 7
//        System.out.println("tempLeft=" + tempLeft + ",right=" + right);
        while (tempLeft <= right) {
            array[tempLeft] = temp[t];
            t++;
            tempLeft++;
        }

    }


}
