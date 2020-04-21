package com.feng.ch03_singlelinkedlist;

import java.util.Stack;

public class SingleLinkedListMain {
    public static void main(String[] args) {
        // 测试
        // 先创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        SingleLinkedList singleLinkedList = new SingleLinkedList();
        // 添加节点
//        singleLinkedList.addLinked(hero1);
//        singleLinkedList.addLinked(hero2);
//        singleLinkedList.addLinked(hero4);
//        singleLinkedList.addLinked(hero3);

        singleLinkedList.addLinkedByOrder(hero1);
        singleLinkedList.addLinkedByOrder(hero2);
        singleLinkedList.addLinkedByOrder(hero4);
        singleLinkedList.addLinkedByOrder(hero3);
        // 显示节点
        System.out.println("显示初始添加后的节点~");
        singleLinkedList.list();

        // 测试修改
        System.out.println();
        System.out.println("测试修改后的结果");
        HeroNode updateNode = new HeroNode(2, "小卢", "玉麒麟");
        singleLinkedList.updateNode(updateNode);
        singleLinkedList.list();

        // 测试删除
        System.out.println();
        System.out.println("测试删除后的结果");
        singleLinkedList.deleteNode(2);
        singleLinkedList.deleteNode(3);
        singleLinkedList.list();

        // 测试返回单链表有效个数
        System.out.println();
        System.out.println("单链表有效个数=" + getLength(singleLinkedList.getHead()));

        // 测试 查看倒数第 K 个节点的 数据
        System.out.println();
        HeroNode lastIndexNode = findLastIndexNode(singleLinkedList.getHead(), 1);
        System.out.println("倒数第 K 个节点的 res=" + lastIndexNode);

        // 测试单链表的反转
        System.out.println();
        System.out.println("测试反转后的链表");
        reverseList(singleLinkedList.getHead());
        singleLinkedList.list();

        // 测试单链表的逆序打印
        System.out.println();
        System.out.println("测试单链表的逆序打印");
        reversePrint(singleLinkedList.getHead());
    }

    /*
     * 方法：实现单链表的逆序打印【百度面试题】
     * 方式1：先反转，在打印。
     * 方式2：利用栈 这个数据结构，将各个节点压入栈中，然后利用栈的先进先出的特点，实现逆序打印的效果
     * */
    public static void reversePrint(HeroNode head){
        if (head.next == null){
            return;
        }

        Stack<HeroNode> stack = new Stack<>();
        HeroNode temp = head.next;
        while (temp != null){
            stack.push(temp);
            temp = temp.next;
        }
        while (stack.size()>0){
            System.out.println(stack.pop());
        }
    }

    /*
     * 将单链表反转 【腾讯面试题】
     * */
    public static void reverseList(HeroNode head) {
        //如果当前链表为空，或者只有一个节点，无需反转，直接返回
        if (head.next == null || head.next.next == null) {
            return;
        }

        //定义一个辅助的指针(变量)，帮助我们遍历原来的链表
        HeroNode temp = head.next;
        HeroNode next = null;
        HeroNode reverseHead = new HeroNode(0, "", "");

        /*
         * 遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表reverseHead 的最前端
         * 动脑筋
         * */
        while (temp != null) {
            next = temp.next;//先暂时保存当前节点的下一个节点，因为后面需要使用
            temp.next = reverseHead.next;//将cur的下一个节点指向新的链表的最前端
            reverseHead.next = temp;//将cur 连接到新的链表上
            temp = next;//让cur后移
        }
        //将head.next 指向 reverseHead.next , 实现单链表的反转
        head.next = reverseHead.next;
    }


    /*
     * 方法：查找单链表中的倒数第k个结点 【新浪面试题】
     * 思路：
     * 1. 编写一个方法，接收head节点，同时接收一个index
     * 2. index 表示是倒数第index个节点
     * 3. 先把链表从头到尾遍历，得到链表的总的长度 getLength
     * 4. 得到size 后，我们从链表的第一个开始遍历 (size-index)个，就可以得到
     * 5. 如果找到了，则返回该节点，否则返回nulll
     * */
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        //判断如果链表为空，返回null
        if (head.next == null) {
            return null;
        }
        //第一次遍历得到链表的长度(节点个数)
        int size = getLength(head);
        //先做一个index的校验
        if (index < 0 || index > size) {
            return null;
        }
        //第二次遍历  size-index 位置，就是我们倒数的第K个节点
        //定义给辅助变量， for 循环定位到倒数的index
        HeroNode temp = head.next;
        for (int i = 0; i < size - index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    /*
     * 方法：获取单链表的有效节点个数（如果是带头结点的链表，需要不统计头结点）
     * @param head 链表的头节点
     * @return 返回的就是有效节点的个数
     * */
    public static int getLength(HeroNode head) {
        if (head.next == null) {
            return 0;
        }
        HeroNode temp = head.next;
        int count = 0;
        /*while (true){
            if (temp== null){
                break;
            }
            count++;
            temp = temp.next;
        }*/
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }


}
