package com.feng.dataStructure.ch06_stack.s2_linkedstack;

public class StackNode {
    private int no;
    private StackNode next;

    public StackNode(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public StackNode getNext() {
        return next;
    }

    public void setNext(StackNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "StackNode{" +
                "no=" + no +
                '}';
    }
}
