package com.feng.dataStructure.ch04_doubleLinkedlist;

// 定义HeroNode ，每个 HeroNode 就是一个节点
public class HeroNode {
    public int no;
    public String name;
    public String nickname;

    public HeroNode pre; // 指向上一个节点，默认为null
    public HeroNode next; // 指向下一个节点，默认为null

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    /* 为显示方法，重写 toString 方法*/
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
