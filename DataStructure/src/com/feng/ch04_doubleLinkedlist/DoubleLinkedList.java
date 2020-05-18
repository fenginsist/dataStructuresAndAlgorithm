package com.feng.ch04_doubleLinkedlist;

/*
 * 创建一个双向链表的类
 * */
public class DoubleLinkedList {

    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    /*
     * 添加节点：按顺序添加
     * */
    public void addDoubleLinkedByOrder(HeroNode newNode) {
        HeroNode temporary = head;
        boolean flag = false;
        while (true) {
            if (temporary.next == null) {  // 当前数据
                break;
            }
            if (temporary.next.no > newNode.no) {
                break;
            } else if (temporary.next.no == newNode.no) {
                flag = true;
                break;
            }
            temporary = temporary.next;
        }
        if (flag) {
            System.out.printf("准备插入的英雄的编号 %d 已经存在了, 不能加入\n", newNode.no);
        } else {
            if (temporary.next == null){
                // 挂载前面
                temporary.next = newNode;
                newNode.pre = temporary;
            }else {
                //挂载后面
                newNode.next = temporary.next;
                temporary.next.pre = newNode;
                // 挂载前面
                temporary.next = newNode;
                newNode.pre = temporary;
            }
        }

    }

    /*
     * 添加节点：直接添加到双向链表的最后
     * */
    public void addDoubleLinked(HeroNode newNode) {
        HeroNode temporary = head;
        while (true) {
            if (temporary.next == null) { // 最后一个 出去
                break;
            }
            temporary = temporary.next; // 后移
        }
        /*
         * 当退出while循环时，temp就指向了链表的最后
         * 形成一个双向链表
         * */
        temporary.next = newNode;
        newNode.pre = temporary;
    }

    /*
     * 修改：可以看到双向链表的节点内容修改和单向链表一样
     * */
    public void updateNode(HeroNode newNode) {
        if (head.next == null) {
            return;
        }
        HeroNode temporary = head.next;
        boolean flag = false;
        while (true) {
            if (temporary == null) { // 没有匹配着
                break;
            }
            if (temporary.no == newNode.no) {
                flag = true;
                break;
            }
            temporary = temporary.next;
        }
        if (flag) {      // 根据flag 判断是否找到要修改的节点
            temporary.name = newNode.name;
            temporary.nickname = newNode.nickname;
        } else {
            System.out.printf("没有找到 编号 %d 的节点，不能修改\n", newNode.no);
        }
    }

    /*
     * 删除节点
     * 1 对于双向链表，我们可以直接找到要删除的这个节点
     * 2 找到后，自我删除即可
     * */
    public void deleteNode(int no) {
        if (head.next == null) {
            System.out.println("链表为空，无法删除");
            return;
        }
        HeroNode temporary = head.next;
        boolean flag = false;
        while (true) {
            if (temporary == null) {
                break;
            }
            if (temporary.no == no) {
                flag = true;
                break;
            }
            temporary = temporary.next;
        }
        if (flag) {
            temporary.pre.next = temporary.next;
            if (temporary != null) {
                temporary.next.pre = temporary.pre;
            }
        } else {
            System.out.printf("要删除的 %d 节点不存在\n", no);
        }
    }

    //显示链表【遍历】
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 因为头结点不能动， 因此需要一个辅助节点 temp 来遍历
        HeroNode temporary = head.next;
        while (temporary != null) {
            // 输出节点信息
            System.out.println(temporary);
            temporary = temporary.next;
        }
    }
}
