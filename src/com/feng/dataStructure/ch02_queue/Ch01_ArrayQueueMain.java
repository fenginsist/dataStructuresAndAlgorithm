package com.feng.dataStructure.ch02_queue;

import java.util.Scanner;

/*
 * 数组 模拟 队列
 * 特点：先进先出
 *
 * 1、先设置两个指针：头指针和尾指针，都为-1.都指向 数组第一个数据 前面的位置。
 * 2、主要方法为 入队列 addQueue 、出队列 getQueue、查看头数据 headQueue、遍历 showQueue
 *
 * 主要就是对 指针的移动。
 *
 * 这个队列 目前出现的问题：
 * 1、目前数组使用一次就不能用，没有达到复用的效果
 * 优化：在下一个示例（类）中优化
 * 2、将这个数组使用算法，改进成一个环形的队列， 取模：%
 * */
public class Ch01_ArrayQueueMain {
    public static void main(String[] args) {

        // 创建一个队列
        Ch01_ArrayQueue arrayQueue = new Ch01_ArrayQueue(3);

        char key = ' '; // 接收用户输入
        Scanner scanner = new Scanner(System.in);  //接收一个字符
        boolean loop = true;

        // 输出一个菜单
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'a':
                    System.out.println("输入一个数");
                    int value = scanner.nextInt();
                    arrayQueue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = arrayQueue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = arrayQueue.headQueue();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序已退出！");
    }
}
