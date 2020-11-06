package com.feng.dataStructure.ch06_stack.s1_arraystack;

import java.util.Scanner;

/*
* 数组 模拟 栈
*
* 前面的 数组模拟队列中，不能复用，是因为有头指针和尾指针，优化成 环形链表即可。
* 这里数组模拟栈，是可以复用的因为 只有一个栈顶指针 top
* */
public abstract class ArrayStackMain {
    public static void main(String[] args) {
        // 测试 ArrayStack 是否正确
        // 先创建一个 ArrayStack 对象 -》栈
        ArrayStack stack = new ArrayStack(4);
        String key = "";
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);

        while (loop) {
            System.out.println("show：表示显示栈");
            System.out.println("exit：退出程序");
            System.out.println("push：表示添加数据到栈（入栈）");
            System.out.println("pop：表示从栈取数据（出栈）");
            System.out.println("请输入您的选择");
            key = scanner.next();
            switch (key) {
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数：");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int res = stack.pop();
                        System.out.printf("出栈的数据是 %d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.printf("程序退出成功~~~");
    }
}
