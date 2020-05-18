package com.feng.ch10_search;

import java.util.Arrays;

/*
 * 斐波那契(黄金分割法)查找算法
 * */
public class S4_FibonacciSearch {

    public static int maxSize = 20;

    public static void main(String[] args) {
        int[] array = {1, 8, 10, 89, 1000, 1234};

        System.out.println("数组为：");
        System.out.println(Arrays.toString(array));

        System.out.println();
        System.out.println("斐波那契数列：");
        System.out.println(Arrays.toString(fib()));

        System.out.println();
        System.out.println("开始查找数据");
        int i = fibSearch(array, 10);
        if (-1 == i) {
            System.out.println("没有找到");
        } else {
            System.out.println("找到了，下标为：" + i);
        }
    }

    /*
     * 因为后面我们 mid = low + F(k-1)-1, 需要使用到斐波那契数列，因此我们需要先获取一个斐波那契数列
     * 非递归方法得到一个斐波那契数列
     * */
    public static int[] fib() {
        int[] f = new int[maxSize];

        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    /*
     * 编写斐波那契查找算法
     * 使用非递归的方式编写算法
     *
     * @param array 数组
     * @param key   我们需要查找的关键码（值）
     * @return      返回对应的下标，如果没有返回 -1
     * */
    public static int fibSearch(int[] array, int key) {

        int low = 0; // 数组 最左侧下标
        int high = array.length - 1; // 数组最右侧下标  5

        int k = 0; //表示斐波那契  分割数值  的下标  // mid = low + F(k-1)-1  中的  k

        int mid = 0; // 存放 mid 值
        int fib[] = fib(); // 获取斐波那契数列  1 1 2 3 5 8 13 21

        // 获取到  斐波那契分割数值的下标
        while (high > fib[k] - 1) { // 只要条件 成立，就说明没有找到， 继续K++， 只要条件不成立，说明找到了 下标 K , 当 k = 5 时，fib[k] - 1= 8，
            k++;
        }

        // 因为 f[k] 值，可能大于 array 的长度，因此我们需要使用 Arrays 类，构造一个新的数组，并指向 temp[]
        // 不足的部分会使用 0 填充
        int[] temp = Arrays.copyOf(array, fib[k]);  // fib[k] 为长度

        /*
        * 实际上需求使用 array 数组最后的数填充 temp
        * 举例:
        * temp = {1,8, 10, 89, 1000, 1234, 0, 0}  => {1,8, 10, 89, 1000, 1234, 1234, 1234,}
        * */
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = array[high];
        }


        /*
         * 使用while来循环处理，找到我们的数 key
         * 叙述一下 初始值：
         * low = 0, high = 5, k = 5
         * */
        while (low <= high) { // 只要这个条件满足，就可以找 初始化为 0<5

            mid = low + fib[k - 1] - 1; // 4,2   // 这里也是对应二分查找、插值查找算法的 mid 计算方法

            if (key < temp[mid]) { //我们应该继续向数组的前面查找(左边)
                high = mid - 1;
                //为甚是 k--
                //说明
                //1. 全部元素 = 前面的元素 + 后边元素
                //2. f[k] = f[k-1] + f[k-2]
                //因为 前面有 f[k-1]个元素,所以可以继续拆分 f[k-1] = f[k-2] + f[k-3]
                //即 在 f[k-1] 的前面继续查找 k--
                //即下次循环 mid = f[k-1-1]-1
                k--;
            } else if (key > temp[mid]) { // 我们应该继续向数组的后面查找(右边)
                low = mid + 1;
                //为什么是k -=2
                //说明
                //1. 全部元素 = 前面的元素 + 后边元素
                //2. f[k] = f[k-1] + f[k-2]
                //3. 因为后面我们有f[k-2] 所以可以继续拆分 f[k-1] = f[k-3] + f[k-4]
                //4. 即在f[k-2] 的前面进行查找 k -=2
                //5. 即下次循环 mid = f[k - 1 - 2] - 1
                k -= 2;
            } else { //找到
                //需要确定，返回的是哪个下标
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;
    }
}
