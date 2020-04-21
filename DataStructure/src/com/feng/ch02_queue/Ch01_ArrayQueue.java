package com.feng.ch02_queue;

// 使用数组 模拟队列 -- 编写一个 ArrayQueue 类
public class Ch01_ArrayQueue {
    private int maxSize;  // 便是数组的最大容量
    private int front;    // 队列头
    private int rear;     // 队列尾
    private int[] array;  // 该数据 用于存放数据， 模拟队列

    // 创建 队列 的构造器
    public Ch01_ArrayQueue(int arrMaxSize) {
        this.maxSize = arrMaxSize;
        this.array = new int[maxSize];
        this.front = -1;     // 指向队列头部，分析出front 是指向: 队列头的前一个位置
        this.rear = -1;      // 指向队列尾，指向: 队列尾的数据（即就是队列最后一个数据）
    }

    // 判断是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    // 判断是否为满
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    // 添加数据到队列
    public void addQueue(int num) {
        if (isFull()) {// 判断是否 已满
            System.out.println("队列满，不能添加数据");
            return;
        }
        rear++; // 让 rear 后移
        array[rear] = num;
    }

    // 获取队列的数据，出队列
    public int getQueue() {
        if (isEmpty()) {// 判断是否为空
            throw new RuntimeException("队列空，不能取值");
        }
        front++; //改变了 front 的值。
        return array[front];
    }

    // 显示队列的所有数据
    public void showQueue() {
        if (isEmpty()) {// 判断是否为空
            System.out.println("队列空，没有值可显示");
            return;
        }
        // 遍历
        for (int i = 0; i < array.length; i++) {
            System.out.printf("array[%d]=%d\n", i, array[i]);
        }
    }

    // 显示队列的头部，注意不是取出数据，仅是显示数据
    public int headQueue() {
        if (isEmpty()) {// 判断是否为空
            throw new RuntimeException("队列空，不能显示头部");
        }
        return array[front + 1]; // 没有改变 front 的值
    }
}
