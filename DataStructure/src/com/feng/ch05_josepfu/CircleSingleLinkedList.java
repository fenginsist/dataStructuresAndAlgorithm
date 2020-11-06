package com.feng.ch05_josepfu;

/*
 * 创建一个环形的单向链表
 * */
public class CircleSingleLinkedList {

    // 创建一个 first 节点，当前没有编号
    private Boy first = new Boy(-1);

    // 添加小孩节点， 构建一个环形的链表
    public void addBoy(int nums) {
        // 校验 nums
        if (nums < 1) {
            System.out.println("nums的值不正确~");
            return;
        }
        // 辅助指针，帮助构建环形链表, 相当于尾指针
        Boy curBoy = null;
        // 使用 for 循环来创创建我们的环形链表
        for (int i = 1; i <= nums; i++) {
            //根据编号，创建小孩节点
            Boy boy = new Boy(i);
            if (i == 1) {
                first = boy;
                first.setNext(boy); // 构成环，指向本身。
                curBoy = first; // 让curBoy 指向第一个小孩
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    // 遍历当前的环形链表
    public void showBoy() {
        if (first == null) {
            System.out.println("没有任何小孩节点~~");
            return;
        }
        // 因为first 不能动，因此 我们使用一个辅助指针完成遍历
        Boy curBoy = first;
        while (true) {
            System.out.printf("小孩的编号 %d \n", curBoy.getNo());
            if (curBoy.getNext() == first) { // 达到最后
                break;
            }
            curBoy = curBoy.getNext();
        }
    }

    /*
     * 根据用户的输入，计算出 小孩出圈的顺序
     * @param startNo 表示 从第几个小孩开始数数
     * @param countNum表示 数几下
     * @param nums    表示最初有多少小孩在圈中
     * */
     public void countBoy(int startNo, int countNum, int nums) {
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数输入有误，请重新输入~~");
            return;
        }
         /*
         * 需要创建 辅助指针（变量）helper，帮助完成小孩出圈,  事先应该指向环形链表的最后这个节点
         * 1、先让 helper 指向 first，
         * 2、当 循环完毕后，helper指向的是 最后一个节点。
         * */
        Boy helper = first;
        while (true){
            if (helper.getNext() == first){ // 说明helper 指向最后小孩节点
                break;
            }
            helper = helper.getNext();
        }
        // 小孩报数前，先让 first 和 helper 移动 k-1 次，这里的 k 为 startNo
         for (int i = 0; i<startNo -1 ; i++){
             first = first.getNext();
             helper = helper.getNext();
         }
         // 当小孩报数时，让 first 和 helper 指针同时移动 m-1 次，然后出圈,这里 m 为 countNum
         //  这里是一个循环操作，直到圈中只有一个节点
         while (true){
             if (helper == first){
                 break;
             }
             //让 first 和 helper 指针同时移动 countNum-1 次
             for (int i= 0; i<countNum-1; i++){
                 first = first.getNext();
                 helper = helper.getNext();
             }
             // 这时 first 指向的节点，就是要出圈的小孩节点
             System.out.printf("小孩%d 出圈 \n", first.getNo());
             // 这时将 first 指向的小孩节点出圈
             first = first.getNext(); // first 节点向前走一个，因为要出圈
             helper.setNext(first);   // helper 指向 first，这时 出圈的节点没有引用了，就被垃圾回收机制回收了

         }
         System.out.printf("最后留在圈中的小孩编号%d \n", first.getNo());
    }

}
