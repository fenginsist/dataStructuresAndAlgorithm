package com.feng.algorithm.ch02_DivideAndConquer;

/*
 * 汉诺塔问题，递归-回溯 解决 使用分治算法
 * */
public class HanoiTower {

    public static void main(String[] args) {
        // 测试
        hanoiTower(5, 'A', 'B', 'C');
    }

    /*
     * 汉诺塔 的移动方法
     * 使用分治算法
     * */
    public static void hanoiTower(int num, char a, char b, char c) {
        // 如果只有一个盘
        if (num == 1) {
            System.out.println("第 1 个盘从 " + a + "->" + c);
        } else {
            // 如果我们有 n >= 2 情况，我们总是可以看做是两个盘 1.最下边的盘 2. 上面的盘
            // 1、 先把 最上面的 所有盘 A-B
            hanoiTower(num - 1, a, c, b);
            // 2、把最下边的盘 A->C
            System.out.println("第 " + num + " 个盘从 " + a + "->" + c);
            // 3、把 B 塔 的所有盘 从 B-> C ,移动过程使用到 a 塔。
            hanoiTower(num - 1, b, a, c);
        }
    }
}
