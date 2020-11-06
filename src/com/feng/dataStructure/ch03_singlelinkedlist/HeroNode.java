package com.feng.dataStructure.ch03_singlelinkedlist;

/*
 * 定义 HeroNode ，每个 HeroNode 对象就是一个节点, 就是一个 Javabean
 * */
public class HeroNode {
    // data域 ：数据
    public int no;
    public String name;
    public String nickname;
    // next域 ：指向下一个节点
    public HeroNode next;

    // 构造器
    public HeroNode(int hNo, String hName, String hNickName) {
        this.no = hNo;
        this.name = hName;
        this.nickname = hNickName;
    }

    // 为显示方便，重写 toString()
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
