package com.feng.ch03_singlelinkedlist;

/*
 * 定义 SingleLinkedList ，管理英雄
 * */
public class SingleLinkedList {

    // 初始化一个头结点，头结点不要动, 不存放具体的数据
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    // 添加节点到单向链表
    /*
     * 第一种添加方式：直接在链表最后添加
     * 思路： 当不考虑编号顺序时，1、找到当前链表的最后节点  2、将新节点挂载到最后节点上-》将最后这个节点的next 指向 新的节点
     * */
    public void addLinked(HeroNode newNode) {
        // 因为头结点不能动，因此需要一个辅助节点 temp 来进行遍历
        HeroNode temp = head;
        // 遍历链表，找到最后
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        // 当退出 循环时，temp就指向了链表的最后
        // 将最后这个节点的next 指向新的节点
        temp.next = newNode;
    }

    /*
     *
     * 第二种方式在添加节点时（英雄），根据顺序（排名）将英雄插入到指定位置
     * （如果有这个排名，则添加失败，并给出提示）
     *  重点：newNode.next = temp.next;  temp.next = newNode;  这两句顺序不能颠倒
     * */
    public void addLinkedByOrder(HeroNode newNode) {
        // 因为头结点不能动，因此需要一个辅助节点 temp 来帮助找到添加的位置
        // 因为 单链表，我们找的 temp 是位于添加位置的前一个节点，否则插入不了
        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no > newNode.no) {
                break;
            } else if (temp.next.no == newNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            System.out.printf("准备插入的英雄的编号 %d 已经存在了，不能加入\n", head.no);
        } else {
            // 先挂载后面，在挂载前面
            newNode.next = temp.next;
            temp.next = newNode;
        }
    }

    public void updateNode(HeroNode newNode){
        //
        if (head.next == null){
            System.out.println("链表为空~");
            return;
        }
        // 定义辅助变量
        HeroNode temp = head.next;
        boolean flag = false;
        while (true){
            if (temp == null){
                break; // 已经遍历完链表
            }
            if (temp.no == newNode.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            temp.name = newNode.name;
            temp.nickname = newNode.nickname;
        }else{
            System.out.println("链表中没有该英雄~");
        }
    }

    /*
    * 删除节点
    * 思路：
    * 1、head 不能动，因此 需要一个 temp 辅助节点找到待删除节点的前一个节点
    * 2、说明 我们在比较时， 是 temp.next.no 和 需要删除的节点的 No 比较
    * */
    public void deleteNode(int no){
        if(head.next == null){
            System.out.println("链表为空， 无需删除~");
            return;
        }

        HeroNode temp = head;
        boolean flag = false; // 标志是否找到待删除节点
        while (true){
            if (temp.next == null){
                break;
            }
            if (temp.next.no == no){
                flag = true;
                break;
            }
            temp = temp.next; // temp 后移一位， 遍历
        }
        if (flag){ // 找到
            temp.next = temp.next.next;
        }else{ // 没找到
            System.out.printf("要删除的节点 %d 节点不存在", no);
        }
    }

    //显示链表【遍历】
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 因为头结点不能动， 因此需要一个辅助节点 temp 来遍历
        HeroNode temp = head.next;
        while (true) {
            if (temp == null) {
                return;
            }
            // 输出节点信息
            System.out.println(temp);
            temp = temp.next;
        }
    }
}
