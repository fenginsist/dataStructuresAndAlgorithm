package com.feng.dataStructure.ch02_queue;

import java.util.Scanner;

/*
 * 数组 模拟 环形队列
 *
 * 1、这次的两个指针：头指针和尾指针 都为 0 ，指向为队列的 第一个数据
 * 2、主要方法为 入队列 addQueue 、出队列 getQueue、查看头数据 headQueue、遍历 showQueue
 * 3、核心思想：取模思想
 *      队列满标志：(rear + 1) % maxSize == front   ***
 *      队列空标志：rear == front == 0
 *      入队列时，案例1 时，因为初始值为 -1，所有先自加（后移），在赋值，但是这里头指针指向 0，所以先赋值，在后移，后移时 也需要取模（rear = (rear + 1) % maxSize）
 *      出队列时，案例1 时，因为初始值为 -1，所有先自加（后移），在弹出，这里直接弹出即可，但是头指针后移时，也需取模 front = (front + 1) % maxSize
 *
 *      ** 重点： 遍历队列时，直接从 front 头指针进行遍历。这是是重点。**
 *
 *      查看队列头时，直接返回 array[front]
 *
 * 对上一个示例进行优化：可重复使用
 * 充分利用数组，将数组看做一个环形的，（通过取模的方式来实现的即可）
 * 重点：1、front、rear 都为0，
 *       2、rear 指向队列的最后一个元素的后一个位置. 因为希望空出一个空间做为约定.，最大下标不存值，做判断。
 *       3、添加、获取、遍历、查看头信息 时的 指针后移都要注意
 * */
public class Ch02_CircleQueueMain {
    public static void main(String[] args) {
        // 创建一个队列
        Ch02_CircleQueue arrayQueue = new Ch02_CircleQueue(4);

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
