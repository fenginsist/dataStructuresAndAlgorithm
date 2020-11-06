package com.feng.dataStructure.ch05_josepfu;

/*
 * 约瑟夫问题
 * Josephu 问题为：设编号为1，2，… n的n个人围坐一圈，约定编号为k（1<=k<=n）的人从1开始报数，数到m 的那个人出列，它的下一位又从1开始报数，
 *                  数到m的那个人又出列，依次类推，直到所有人出列为止，由此产生一个出队编号的序列。
 *
 * 使用 单向循环列表 解决
 * */
public class JosepfuMain {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.showBoy();

        // 测试小孩出圈
        System.out.printf("\n");
        circleSingleLinkedList.countBoy(1, 2, 5); // 2 4 1 5 3
    }
}
