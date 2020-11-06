package com.feng.ch12_tree;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/*
 * 堆排序
 * 堆 是具有以下性质的  完全二叉树：
 * 1、每个结点的值都大于或等于其左右孩子结点的值，称为大顶堆（注意 : 没有要求结点的左孩子的值和右孩子的值的大小关系。）
 * 2、每个结点的值都小于或等于其左右孩子结点的值，称为小顶堆
 *
 * 完全二叉树：
 * 如果该二叉树的所有叶子节点都在最后一层或者倒数第二层，而且最后一层的叶子节点在左边连续，倒数第二层的叶子节点在右边连续，我们称为完全二叉树。
 *
 * 对排序分为三步：
 * 1、将无序序列构建成一个堆，根据升序降序需求选择大顶堆或小顶堆;
 * 2、将堆顶元素与末尾元素交换，将最大元素"沉"到数组末端;
 * 3、重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤，直到整个序列有序。
 * 说明：
 * 第一步直接将二叉树对应的数组 调整成 大顶堆或者 小顶堆
 * 然后对第二步、第三步进行循环操作即可。
 * */
public class T4_HeapSort {

    public static void main(String[] args) {
        // 默认升序排序
//        int[] array = {4, 6, 8, 5, 9};
        int[] array = {4, 6, 8, 5, 9, -1, 90, 89, 56, -999};

        System.out.println("原始数组：");
        System.out.println(Arrays.toString(array));

        heapSort(array);

        System.out.println("测试堆排序速度：");
        testTime();  // 8000数据：88ms; 8万数据: 122ms; 80万数据:380ms; 800万数据：4s
    }

    /*
     * 测试一下 堆排序的速度, 给 80000 个数据，测试一下
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

        heapSort(array2);

        System.out.println();
        long end = System.currentTimeMillis();
        Date date2 = new Date(end); // 上面的也可以不要，但是我想测试
        System.out.println("排序后的时间是=" + format.format(date2));
        System.out.println("共耗时" + (end - start) + "毫秒");
        System.out.println("毫秒转成秒为：" + ((end - start) / 1000) + "秒");
    }


    /*
     * 编写一个堆排序的方法
     * 核心：将树排成 大顶堆或者小顶堆。
     *
     * 1、将一个数组（对应二叉树）， 调整成一个大顶堆
     * 2、将堆顶元素与末尾元素交换，将最大元素"沉"到数组末端;
     * 3、重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤，直到整个序列有序。
     * 循环 2、3 步
     * */
    public static void heapSort(int[] array) {
        int temp = 0;
        //分步调整
//        System.out.println("调整成大顶堆：");
//        adjustHeap(array, 1, array.length);
//        System.out.println("第 1 次："+Arrays.toString(array));  // [4, 9, 8, 5, 6]
//
//        adjustHeap(array, 0, array.length);
//        System.out.println("第 2 次："+Arrays.toString(array));  // [9, 6, 8, 5, 4]

        /*
         * 完成我们最终代码 ， 对上面的 两步规律 进行整合，使用for 循环，使用 array.length / 2 - 1 找到第一个非叶子结点。
         * i = array.length / 2 - 1 : 从左到右，从下到上的第一个非叶子节点的 索引
         * */
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            adjustHeap(array, i, array.length);
        }
        //System.out.println("调整成的大顶堆："+Arrays.toString(array));  // [9, 6, 8, 5, 4]
        // 循环执行完 adjustHeap 方法后，该数组就变成了大顶堆。

        /*
         * 2).将堆顶元素与末尾元素交换，将最大元素"沉"到数组末端;
         * 3).重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤，直到整个序列有序。
         * */
        for (int j = array.length - 1; j > 0; j--) {
            // 交换: 将堆顶元素与末尾元素交换，将最大元素"沉"到数组末端;
            temp = array[j];
            array[j] = array[0];
            array[0] = temp;
            // 每互换一次，都要对根结点 进行调整为大顶堆。
            adjustHeap(array, 0, j);
        }
//        System.out.println("排序后："+Arrays.toString(array));  // [9, 6, 8, 5, 4]
    }




    /*
     * 将一个数组（对应二叉树）， 调整成一个大顶堆
     *
     * 功能： 完成将 以 i 对应的非叶子结点的树，调整成大顶堆
     * 举例： int[] array = {4, 6, 8, 5, 9}; => i = 1 => adjustHeap => {4, 9, 8, 5, 6}
     * 再次调用adjustHeap 传入的是   i = 0 => {9, 4, 8, 5, 6}
     * 再次调用adjustHeap 传入的是   i = 0 => {9, 4, 8, 5, 6} 进行调整 => {9, 6, 8, 5, 4}
     *
     * @param array 待调整的数组
     * @param i 表示 非叶子节点 的在数组中的索引，就是当前结点
     * @param length 表示对多少个元素进行调整，length是在逐渐减少
     * */
    public static void adjustHeap(int[] array, int i, int length) {

        int temp = array[i]; // 先取出 当前 i结点 的值，保存在临时变量
        /*
         * 开始调整
         * 1、k = i * 2 + 1: k 是以 i 为非叶子结点的 左子结点
         * */
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && array[k] < array[k + 1]) { // 如果为true，说明左子结点  小于 右子结点的值
                k++; // 让 k 指向 右子结点，这时  k 为最大值的索引
            }
            if (array[k] > temp) { // 如果右(左)子结点 大于 父结点，说明这里要对右(左)子结点（k）和父结点(i)进行 互换
                array[i] = array[k]; // 把较大的值赋给当前结点
                array[k] = temp;
                i = k; // !!! i指向 k ，改变父结点 ，继续循环比较
            } else {
                break; //!!! 敢break 是因为 这里的i 是从左到右，从下到上，第一个的非叶子节点
            }
        }

        /*
         * 老师是写在这儿，我写在了上面的判断中 ：array[k] = temp;
         * 当代码走到这儿，for循环结束后，已经将以 i 为父结点的树的最大值，放在了最顶（局部）
         * */
//        array[i] = temp;// 将 temp 值 放到调整后的位置。
    }
}
