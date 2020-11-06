package com.feng.dataStructure.test;

import org.junit.Test;
import sun.security.krb5.internal.PAEncTSEnc;

import java.util.Arrays;

/*
 * 排序算法 归总
 *
 * 1、冒泡排序             n^2
 * 2、选择排序             n^2     不稳定
 * 3、插入排序 有点难度     n^2
 * 4、希尔排序 有点难度     nlog2n  不稳定
 * 5、快速排序 好理解   递归 nlog2n 不稳定
 * 6、归并排序 有点难度 递归 nlog2n
 * 7、基数排序（桶排序） 有意思，好理解  以空间换时间的稳定式排序
 *
 * 8、堆排序                nlog2n    不稳定
 * */
public class SortTest {

    private int[] array = {3, 9, 1, 10, 20};

    /*
     * 冒泡排序
     * */
    @Test
    public void bubbleSortTest() {
        int temp;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) { // 为true 说明 左边的大于右边的，则互换
                    temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
            }
        }
        System.out.println("排序后的数组：" + Arrays.toString(array));
    }


    /*
     * 选择排序
     * */
    @Test
    public void selectSortTest() {
        int min;
        int minIndex;
        for (int i = 0; i < array.length - 1; i++) {
            // 先假设第一个数为最小值，并保存
            min = array[i];
            minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < min) {
                    min = array[j];
                    minIndex = j;
                }
            }
            // 找到最小值，交换
            array[minIndex] = array[i];
            array[i] = min;
        }
        System.out.println("排序后的数组：" + Arrays.toString(array));
    }

    /*
     * 插入排序 while循环 有点意思
     * */
    @Test
    public void insertSortTest() {
        int insertValue; // 待插入值
        int insertIndex; // 要插入的位置 索引

        for (int i = 1; i < array.length; i++) {
            insertValue = array[i];
            insertIndex = i - 1;

            while (insertIndex >= 0 && insertValue < array[insertIndex]) { // 当要插入索引大于0 且 待插入值 小于要插入索引的值时，要后移。
                array[insertIndex + 1] = array[insertIndex];
                insertIndex--;
            }
            array[insertIndex + 1] = insertValue;
        }
        System.out.println("排序后的数组：" + Arrays.toString(array));
    }

    /*
     * 希尔排序 交换式
     * */
    @Test
    public void shellSortByChangeTest() {
        int temp;
        for (int gap = array.length / 2; gap > 0; gap = gap / 2) {
            for (int i = gap; i < array.length; i++) {
                for (int j = i - gap; j >= 0; j = j - gap) { // 保证下面比较 ，1和5,2和6，3和7
                    if (array[j] > array[j + gap]) {
                        temp = array[j + gap];
                        array[j + gap] = array[j];
                        array[j] = temp;
                    }
                }
            }
        }
        System.out.println("排序后的数组：" + Arrays.toString(array));
    }

    /*
     * 希尔排序 移动式 有点难度
     * */
    @Test
    public void shellSortByMoveTest() {
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
        System.out.println("排序后的数组：" + Arrays.toString(array));
    }

    /*
     * 快速排序  递归  好理解
     * */
    @Test
    public void quickSortTest() {
        quickSort(array, 0, array.length - 1);
        System.out.println("排序后的数组：" + Arrays.toString(array));
    }

    public static void quickSort(int[] array, int left, int right) {
        if (left > right) {
            return;
        }

        int i = left; //左指针
        int j = right; // 右指针

        int base = array[i]; // 基数

        while (i != j) {
            // 从 左向右 走
            while (i < j && array[i] < base) {
                i++;
            }

            // 从 又向左 走
            while (i < j && array[j] > base) {
                j--;
            }

            // 代码走到这儿，说明 左边找到比 base 大的， 右边找到 比 base 小的，进行互换
            int temp = array[j];
            array[j] = array[i];
            array[i] = temp;
        }

        // 如果代码走到这儿，说明 左指针 和 右指针相等了（即 i 和 j 相等了），将 base 与 i 或者 j 互换即可。

        array[i] = base;
        base = array[i];

        // 左递归
        quickSort(array, left, i - 1);
        // 右递归
        quickSort(array, j + 1, right);

    }

    /*
     * 归并排序
     * */
    @Test
    public void mergetSortSortTest() {
        int[] temp = new int[array.length];
        mergetSort(array, 0, array.length - 1, temp);
        System.out.println("排序后的数组：" + Arrays.toString(array));
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
        while (tempLeft <= right) {
            array[tempLeft] = temp[t];
            t++;
            tempLeft++;
        }

    }

    /*
     * 基数排序（桶排序） 不是递归，很有意思
     * */
    @Test
    public void radixSortTest() {

        int max = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        int maxLength = (max + "").length();

        // 定义10个桶，
        int bucket[][] = new int[10][array.length];
        int[] bucketElementCounts = new int[10]; // 10

        for (int i = 0, n = 1; i < maxLength; i++, n = n * 10) {

            for (int j = 0; j < array.length; j++) {
                int digitOfElement = (array[j] / n) % 10;
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = array[j];
                bucketElementCounts[digitOfElement]++;
            }

            // 遍历每一个桶(也就是遍历二维数组)，并将桶中的每一个数据，放入到原数组
            int index = 0;
            for (int k = 0; k < bucket.length; k++) { // k<10
                if (bucketElementCounts[k] != 0) {
                    for (int j = 0; j < bucketElementCounts[k]; j++) {
                        array[index] = bucket[k][j];
                        index++;
                    }
                }
                // 每一轮 处理后，需要将每个 bucketElementCounts[i] = 0 ！！！
                bucketElementCounts[k] = 0;
            }
        }

        System.out.println("排序后的数组：" + Arrays.toString(array));
    }


    /*
     * 堆排序
     * */
    @Test
    public void heapSortTest() {

        // 1).调整成大顶堆
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            adjustHeap(array, i, array.length);
        }

        // 2).将堆顶元素与末尾元素交换，将最大元素"沉"到数组末端;
        // 3).重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤，直到整个序列有序。
        int temp = 0;
        for (int j = array.length - 1; j > 0; j--) {
            temp = array[j];
            array[j] = array[0];
            array[0] = temp;
            adjustHeap(array, 0, j);
        }
        System.out.println("排序后的数组：" + Arrays.toString(array));

    }

    // 调整成 大顶堆
    public static void adjustHeap(int array[], int i, int length) {
        int temp = array[i]; // 先取出 当前 i结点 的值，保存在临时变量

        for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {
            if (k + 1 < length && array[k] < array[k + 1]) {
                k++;
            }
            if (array[k] > temp) {
                array[i] = array[k];
                array[k] = temp;
                i = k; // !!! i指向 k ，改变父结点 ，继续循环比较
            } else {
                break;
            }
        }
    }
}
