package com.feng.ch06_stack.s2_linkedstack;

public class LinkedStackMain {
    public static void main(String[] args) {
        LinkedStack stack = new LinkedStack();

        StackNode node1 = new StackNode(1);
        StackNode node2 = new StackNode(2);
        StackNode node3 = new StackNode(3);
        StackNode node4 = new StackNode(4);
        StackNode node5 = new StackNode(5);

        stack.push(node1);
        stack.push(node2);
        stack.push(node3);
        stack.push(node4);
        stack.push(node5);

        System.out.println("初始化的栈：");
        stack.list();

        // 测试出栈
        System.out.println();
        System.out.println("出栈的数据："+stack.pop());

        System.out.println();
        System.out.println("出栈后的栈：");
        stack.list();
    }
}
