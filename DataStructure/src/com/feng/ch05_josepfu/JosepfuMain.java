package com.feng.ch05_josepfu;

/*
* 约瑟夫问题
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
