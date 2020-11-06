package com.feng.dataStructure.ch06_stack.s2_linkedstack;

public class LinkedStack {
    private StackNode head = new StackNode(0);

    public StackNode getHead() {
        return head;
    }

    /*
     * 入栈
     * */
    public void push(StackNode newNode) {
        if (head.getNext() == null) { // 第一次添加
            head.setNext(newNode);
        } else {
            newNode.setNext(head.getNext()); // 原来 栈 挂载到 新结点上，构成新栈
            head.setNext(newNode); //  新栈挂载到 头结点上。
        }
    }

    /*
     * 出栈
     * */
    public StackNode pop() {
        if (head.getNext() == null) {
            throw new RuntimeException("栈为空，无法出栈");
        }
        /*
         * 两种返回方式
         * */
//        int value = head.getNext().getNo();
//        head.setNext(head.getNext().getNext());
//        return value;
        StackNode next = head.getNext();
        head.setNext(next.getNext());
        return next;
    }


    /*
     * 遍历
     * */
    public void list() {
        if (head.getNext() == null) {
            System.out.println("栈为空，无法遍历");
            return;
        }
        StackNode temporary = head.getNext();
        while (true) {
            if (temporary == null) {
                break;
            }
            System.out.printf("节点编号%d\n", temporary.getNo());
            temporary = temporary.getNext();
        }
    }
}
