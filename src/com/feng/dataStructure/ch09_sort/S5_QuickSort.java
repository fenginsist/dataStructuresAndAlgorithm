package com.feng.dataStructure.ch09_sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/*
 * 快速排序：
 *       对冒泡排序的优化
 * 基本思想：
 * 通过一趟排序将要排序的数据分割成独立的两部分，其中一部分的所有数据都比另外一部分的所有数据都要小，然后再 按此方法对这两部分数据分别进行快速排序，
 * 整个排序过程可以递归进行 ，以此达到整个数据变成有序序列
 *
 * 思路分析：
 * 1、先把数组中的一个数当做基准数，一般会把数组中最左边的数当做基准数。
 * 2、然后从两边进行检索，先从右边往左检索比基准数小的，再从左边往右检索比基准数大的。
 * 3、如果检索到了，就停下，交换这两个元素。然后继续检索。
 * 4、如果这两个索引指针相遇了，就退出循环。
 * 5、将基准数与这两个索引遇到的数字进行对换。
 * 6、基准数在这里就归位了，左边的数字都比基准数小，右边的数字都比基准数大。
 * 7、然后使用递归进行排序。
 *
 * 时间复杂度：O(nlog2n)
 * 速度测试 ： 8万数据： 1S不到， 80万数据： 1S 不到， 800万数据：1S不到 ，速度超级快
 * */
public class S5_QuickSort {

    public static void main(String[] args) {
        int[] array = {-9, 78, 0, 23, -567, 70, -1, 900, 4561};
        System.out.println("初始数组：");
        System.out.println(Arrays.toString(array));

        System.out.println();
        System.out.println("排序后：");
        quickSort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));

        // 测试 80000 个数据排序 所用的时间
        System.out.println();
        System.out.println("测试 80000 个数据 采用快速排序（交换式） 所用的时间:");
        testTime();   // 8万数据： 1S不到， 80万数据： 1S 不到， 800万数据：2-3
    }

    /*
     * 测试一下 快速排序的速度, 给 80000 个数据，测试一下
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

        quickSort(array2, 0, array2.length - 1);   // 移位式

        System.out.println();
        long end = System.currentTimeMillis();
        Date date2 = new Date(end); // 上面的也可以不要，但是我想测试
        System.out.println("排序后的时间是=" + format.format(date2));
        System.out.println("共耗时" + (end - start) + "毫秒");
        System.out.println("毫秒转成秒为：" + ((end - start) / 1000) + "秒");
    }


    /*
     * 快速排序方法：通俗异同，直接看注释即可
     *
     * @param array 需要排序的数组
     * @param left  最左边的下标，也是索引
     * @param right 最右边的下标，也是索引
     * */
    public static void quickSort(int[] array, int left, int right) {
        // 进行判断，如果左边索引比右边索引要大，是不合法的，直接使用return 结束这个方法
        if (right < left) {
            return;
        }

        // 定义变量保存基准数，这里的基准数用最左边的数来代替。
        int base = array[left];
        // 定义变量 i ，指向最左边
        int i = left;
        // 定义变量 j, 指向最右边
        int j = right;

        // 当 i 和 j 不相遇的时候，就在循环中进行检索。
        while (i != j) {
            // 先 由j 从右往左检索比基准数小的，如果检索到比基准数小的就停下
            // 如果检索比基准数大的或者相等的，就继续检索
            while (i < j && array[j] >= base) {
                j--; // j 从右往左移动
            }
            // i 从左往右检索。找比基准数 大的，
            while (i < j && array[i] <= base) {
                i++; // i 从左往右移动
            }

            // 如果代码走到这儿，i 和 j 都停下了，。然后交换 i 和 j 位置的元素
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }

        /*
         * 如果上面的 while 循环条件不成立了，会跳出这个循环，往下执行。
         * 如果这个条件不成立 说明 i 和 j 相遇了，也就是相等
         * 如果 i 和 j 相遇了，就交换基准数这个元素 和相遇位置的元素。
         * 把 相遇位置的元素 赋值 给基准数这个位置的元素
         * */
        array[left] = array[i];
        // 把基准数赋值给相遇位置的元素
        array[i] = base;

        /*
         * 代码走到这里，说明 基准数就归位了，左边的数字都比基准数小，右边的数字都比基准数大。完成了第一次排序
         * 以后使用递归去排序
         * */
        // 对基准数的 左边进行排序
        quickSort(array, left, i - 1);

        // 排右边
        quickSort(array, j + 1, right);
    }
}
