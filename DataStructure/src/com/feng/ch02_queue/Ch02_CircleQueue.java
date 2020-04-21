package com.feng.ch02_queue;

/*
* 数组 模拟 环形队列
* 对上一个示例进行优化：可重复使用
* 充分利用数组，将数组看做一个环形的，（通过取模的方式来实现的即可）
* 重点：1、front、rear 都为0，
*       2、rear 指向队列的最后一个元素的后一个位置. 因为希望空出一个空间做为约定.，最大下标不存值，做判断。
*       3、添加、获取、遍历、查看头信息 时的 指针后移都要注意
* */
public class Ch02_CircleQueue {
    private int maxSize; // 表示数组的最大容量
    // front 变量的含义做一个调整： front 就指向队列的第一个元素, 也就是说 arr[front] 就是队列的第一个元素
    // front 的初始值 = 0
    private int front; // 队列头
    // rear 变量的含义做一个调整：rear 指向队列的最后一个元素的后一个位置. 因为希望空出一个空间做为约定.
    // rear 的初始值 = 0
    private int rear; // 队列尾
    private int[] array; // 该数据用于存放数据，模拟

    // 创建队列的构造器
    public Ch02_CircleQueue(int arrMaxSize) {
        this.maxSize = arrMaxSize;
        this.array = new int[maxSize];
//        this.front = 0;
//        this.rear = 0;
    }

    // 判断队列 是否满
    public Boolean isFull() {
        return (rear + 1) % maxSize == front;  // 举例： maxSize=6，最大下标为5，  （5+1）%6=0   为true，则为满
    }

    // 判断队列是否为空
    public Boolean isEmpty() {
        return rear == front;  // 初始值皆为 0
    }

    /*
     * 添加数据 到 队列
     * 直接将数据加入:  rear 初始为 0，直接赋值即可，赋值完后，需将 rear 向后移一位。
     * */
    public void addQueue(int n) {
        // 判断 队列 是否满
        if (isFull()) {
            System.out.println("队列满，不能加入数据~");
            return;
        }
        array[rear] = n;
        // 将 rear 后移，这里必须考虑取模
        rear = (rear + 1) % maxSize;
    }

    // 获取 队列 的数据，出队列
    public int getQueue() {
        // 判断 队列 是否为空
        if (isEmpty()) {
            throw new RuntimeException("队列空，不能取数据");
        }
        // 这里 需要分析出 front 是指向队列的第一个元素
        // 1. 先把 front 对应的值保留到一个临时变量
        // 2. 将 front 后移, 考虑取模
        // 3. 将临时保存的变量返回
        int value = array[front];
        front = (front + 1) % maxSize; // 改变 front 所指的数据。
        return value;
    }

    // 显示队列的所有数据
    public void showQueue() {
        // 遍历
        if (isEmpty()) {
            System.out.println("队列为空，没有数据~~~");
            return;
        }
        // 思想：从 front 开始遍历，遍历多少个元素
        // 动脑筋
        /*
        * i =  front, 我开始写错了，写成了 0 ，所以就有了问题。
        * */
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, array[i % maxSize]);
        }
    }

    // 求出当前队列有效数据的个数
    public int size() {
        // rear = 2
        // front = 1
        // maxSize = 3
        return (rear + maxSize - front) % maxSize;
    }

    // 显示 队列的投数据，注意不是取出数据
    public int headQueue() {
        // 判断 是否为空
        if (isEmpty()) {
            throw new RuntimeException("队列空的，没有数据~~~");
        }
        return array[front];
    }
}
