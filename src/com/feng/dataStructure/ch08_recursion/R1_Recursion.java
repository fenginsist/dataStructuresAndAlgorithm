package com.feng.dataStructure.ch08_recursion;

/*
 * 递归
 * */
public class R1_Recursion {
    public static void main(String[] args) {
        // 通过打印问题，回顾递归调用机制
        test01(4);  // 输出的顺序：n=2 n=3 n=4

        //加上一个 else
        System.out.println();
        test02(4);  // 输出，仅输出 2；


        // 阶乘问题
        System.out.println();
        int res = factorial(4);
        System.out.println("res=" + res);
    }

    /**
     * 打印问题.
     * 当 n 为 4时 输出的顺序：n=2 n=3 n=4
     *
     * @param n
     */
    public static void test01(int n) {
        if (n > 2) {
            test01(n - 1);   // 如果为 + 时，会成为 栈溢出，报错：java.lang.StackOverflowError
        }
        System.out.println("n=" + n);
    }

    /**
     * 测试 else
     * 仅输出 2
     *
     * @param n
     */
    public static void test02(int n) {
        if (n > 2) {
            test01(n - 2);
        } else {
            System.out.println("n=" + n);
        }
    }

    /**
     * 阶乘问题
     *
     * @param n
     * @return
     */
    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        } else {
            return n * factorial(n - 1); //n=3时， f(3) = 3*f(2)=3*2*f(1)= 3*2*1, 依次类推
        }
    }
}
