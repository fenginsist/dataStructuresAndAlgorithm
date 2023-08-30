package com.feng.dataStructure.ch09_sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/*
 * 插入排序
 * 时间复杂度： O(n^2)
 *
 * 排序思想：把n个待排序的元素看成为一个有序表和一个无序表，开始时有序表中只包含 1 个元素，无序表中包含有 n-1 个元素，
 *          排序过程中每次从无序表中取出第一个元素，把它的排序码依次与有序表元素的排序码进行比较，将它插入到有序表中的适当位置，
 *          使之成为新的有序表。
 *
 *
 * 分为三步：
 * 1、每一步进行推导。 deductionInsertSort(int[] array)方法
 *      1.1 先定义两个变量：insertValue、insertIndex，分别代表 待插入的值（从数组第二个值开始）、待插入的下标
 *      1.2 先保存数组第二个值，进行循环，每次循环判断第二值（当前值）与第一个值（前一个值）的大小，若小则将第一个值（前一个值）后移，
 *      1.3 进行循环，循环一次，便将 保存的当前值插入到所应该在的前面的位置。
 * 2、找到规律，合成两个for循环。 insertSort(int[] array)方法，并进行优化，优化 重点，二行代码
 *      先保存第二次数，然后 与前面的数进行比较，如果小，则将前面的数后移，以此类推 进行比较，直到 保存的这个数找到位置 即可。
 *
 * 3、测试 80000 个数据排序所需要的时间。 testTime()方法      1S 左右， 比选择、冒泡快
 * */
public class S3_InsertSort {

    public static void main(String[] args) {
        int[] array = {101, 34, 20, 1};

        System.out.println("原始数组：");
        System.out.println(Arrays.toString(array));

        System.out.println();
//        deductionInsertSort(array);
        int[] ints = insertSort(array);

        System.out.println("排序后：");
        System.out.println(Arrays.toString(ints));

        // 测试 80000 个数据排序 所用的时间
        System.out.println();
        System.out.println("测试 80000 个数据 采用插入排序 所用的时间:");
        testTime();  // 2S
    }

    /*
     * 测试一下 插入排序的速度O(n^2), 给 80000 个数据，测试一下
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

        insertSort(array2);

        System.out.println();
        long end = System.currentTimeMillis();
        Date date2 = new Date(end); // 上面的也可以不要，但是我想测试
        System.out.println("排序后的时间是=" + format.format(date2));
        System.out.println("共耗时" + (end - start) + "毫秒");
        System.out.println("毫秒转成秒为：" + ((end - start) / 1000) + "秒");
    }

    /*
     * 推导后 合成的 插入排序
     * */
    public static int[] insertSort(int[] array) {
        int insertValue = 0; // 保存 待插入的数
        int insertIndex = 0; // 保存 要插入的位置

        for (int i = 1; i < array.length; i++) {
            insertValue = array[i];  // 第一次 为 34 // 待插入的值，无序表的最左边，其下标就是 i
            insertIndex = i - 1;    // 第一次 为 0， // 有序表的最右边
            /*
             * 给 insertValue 找到插入的位置
             * 说明
             * 1、insertIndex >= 0 保证再给 insertValue 找插入位置，不越界
             * 2、insertValue < array[insertIndex] 待插入的数，还没有找到插入位置
             * 3、就需要 array[insertIndex] 后移
             * */
            while (insertIndex >= 0 && insertValue < array[insertIndex]) {
                array[insertIndex + 1] = array[insertIndex];
                insertIndex--;  // 要插入的位置  就要往前移。
            }
            // 当退出 while 循环时，说明插入的位置找到，insertIndex +1；
            // 举例：理解不了，debug 可以帮助理解
            // 这里判断是否需要赋值
            if (insertIndex + 1 != i) { // insertIndex + 1 == i  ，就不需要进行赋值了，因为这时是正好的排序
                array[insertIndex + 1] = insertValue;
            }
        }
        return array;
    }

    /*
     * 推导插入排序
     * */
    public static int[] deductionInsertSort(int[] array) {
        // 使用逐步推导的方式来讲解，便于理解

        // 第一轮{101, 34, 119, 1} =》 {34, 101 119, 1}
        // 定义 待插入 的数、插入的位置
        int insertValue = array[1]; // 保存 第二个数
        int insertIndex = 1 - 1;   // 保存要插入的位置

        /*
         * 给 insertValue 找到插入的位置
         * 说明
         * 1、insertIndex >= 0 保证再给 insertValue 找插入位置，不越界
         * 2、insertValue < array[insertIndex] 待插入的数，还没有找到插入位置
         * 3、就需要 array[insertIndex] 后移
         * */
        while (insertIndex >= 0 && insertValue < array[insertIndex]) {
            array[insertIndex + 1] = array[insertIndex];
            insertIndex--;
        }
        // 当退出 while 循环时，说明插入的位置找到，insertIndex +1；
        // 举例：理解不了，debug 可以帮助理解
        array[insertIndex + 1] = insertValue;

        System.out.println("第一轮插入");
        System.out.println(Arrays.toString(array));


        // 第 2 轮
        insertValue = array[2];
        insertIndex = 2 - 1;
        while (insertIndex >= 0 && insertValue < array[insertIndex]) {
            array[insertIndex + 1] = array[insertIndex];
            insertIndex--;
        }
        array[insertIndex + 1] = insertValue;
        System.out.println("第二轮插入");
        System.out.println(Arrays.toString(array));

        // 第 3 轮
        insertValue = array[3];
        insertIndex = 3 - 1;
        while (insertIndex >= 0 && insertValue < array[insertIndex]) {
            array[insertIndex + 1] = array[insertIndex];
            insertIndex--;
        }
        array[insertIndex + 1] = insertValue;
        System.out.println("第三轮插入");
        System.out.println(Arrays.toString(array));

        return array;
    }
}
