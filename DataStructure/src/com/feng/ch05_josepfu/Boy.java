package com.feng.ch05_josepfu;

/*
* 创建一个 Boy 类，代表一个节点
* */
public class Boy {
    private int no;
    private Boy next; // 指向下一个节点， 默认 null。

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
