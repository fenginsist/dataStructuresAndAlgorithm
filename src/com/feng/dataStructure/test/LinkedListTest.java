package com.feng.dataStructure.test;

import java.util.concurrent.LinkedBlockingQueue;

public class LinkedListTest {

    public static void main(String[] args) {
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();

        linkedBlockingQueue.add("111");
        System.out.println(linkedBlockingQueue.peek());
        System.out.println(linkedBlockingQueue.peek());
    }
}
