package com.feng.ch08_recursion;

public class R3_Queue8 {

    // 定义 一个max 表示共有多少个黄后
    int max = 8;
    // 定义数组 array ，保存皇后放置位置的结果，比如 arr = {0 ,4 ,7, 5, 2, 6, 1, 3}
    int[] array = new int[max];
    static int count = 0;
    static int judgeCount = 0;

    public static void main(String[] args) {
        R3_Queue8 queue8 = new R3_Queue8();
        queue8.check(0);

        System.out.printf("一共有%d种解法\n", count);
        System.out.printf("一共判断冲突的次数%d次", judgeCount); // 1.5w
    }

    /*
     * 编写一个方法， 放置第 n 个皇后
     * 特别注意： check 是每一次 递归时，进入到 check中都有 for (int i = 0; i<max; i++) , 因此 会有回溯
     * */
    public void check(int n) {
        if(n == max) {  //n = 8 , 其实8个皇后就既然放好
            print();
            return;
        }

        // 依次放入皇后，并判断是否冲突
        for(int i = 0; i < max; i++) {
            // 先把当前这个皇后 n ，放到改行的第 1 列
            array[n] = i;
            // 判断当放置 第 n 个皇后到 i 列，是否冲突
            if (judge(n)) {
                // 接着放 n+1 个皇后，即开始递归
                check(n + 1);
            }
            /*
             * 如果冲突，就继续执行 array[n] = i; 即将第 n 个皇后，放置在本行的 后移的一个位置
             * */
        }
    }

    // 查看当我们放置第 n 个皇后，就去检测该皇后是否和前面已经摆放的皇后冲突
    /*
     * Math.abs() : 求绝对值的方法
     *
     * @param n 表示第 n 个皇后
     * @return
     * */
    private boolean judge(int n) {
        judgeCount++;
        for (int i = 0; i < n; i++) {
            /*
             * 说明：
             * 1、array[i] == array[n] ： 表示判断 第 n 个皇后是否和前面的 n-1 个皇后在同一列
             * 2、Math.abs(n - i) == Math.abs(array[n] - array[i]) ：
             * */
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) { // 如果为 true，则为同一列
                return false;
            }
        }
        return true;
    }

    // 写一个方法，可以将皇后摆放的位置输出
    private void print() {
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
