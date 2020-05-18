package com.feng.ch06_stack.s2_linkedstack;

public class LinkedStack {
    private StackNode head = new StackNode(0);

    public StackNode getHead() {
        return head;
    }

    /*
     * 入栈
     * */
    public void push(StackNode newNode) {
        if (head.getNext() == null) { // 第一次天机
            head.setNext(newNode);
        } else {
            newNode.setNext(head.getNext());
            head.setNext(newNode);
        }
    }

    /*
     * 出栈
     * */
    public StackNode pop(){
        if (head.getNext() == null){
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
    public void list(){
        if (head.getNext() == null){
            System.out.println("栈为空，无法遍历");
            return;
        }
        StackNode temporary = head.getNext();
        while (true){
            if (temporary == null){
                break;
            }
            System.out.printf("节点编号%d\n",temporary.getNo());
            temporary = temporary.getNext();
        }
    }
}
