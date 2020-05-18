package com.feng.ch04_doubleLinkedlist;

public class DoubleLinkedListMain {
    public static void main(String[] args) {
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();

        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

//        doubleLinkedList.addDoubleLinked(hero1);
//        doubleLinkedList.addDoubleLinked(hero2);
//        doubleLinkedList.addDoubleLinked(hero3);
//        doubleLinkedList.addDoubleLinked(hero4);

        doubleLinkedList.addDoubleLinkedByOrder(hero2);
        doubleLinkedList.addDoubleLinkedByOrder(hero1);
        doubleLinkedList.addDoubleLinkedByOrder(hero4);
        doubleLinkedList.addDoubleLinkedByOrder(hero3);

        System.out.println("初始化的 双链表数据：");
        doubleLinkedList.list();
        // 测试修改
        System.out.println();
        System.out.println("修改后的数据~");
        HeroNode hero5 = new HeroNode(4, "公孙胜", "入云龙");
        doubleLinkedList.updateNode(hero5);
        doubleLinkedList.list();

        // 测试删除
        System.out.println();
        System.out.println("删除后的数据~");
        doubleLinkedList.deleteNode(3);
        doubleLinkedList.list();
    }
}
