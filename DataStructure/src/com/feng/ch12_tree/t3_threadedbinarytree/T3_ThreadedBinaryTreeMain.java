package com.feng.ch12_tree.t3_threadedbinarytree;

/*
 * 线索二叉树
 *
 * 1、n个结点的二叉链表中含有n+1  【公式 2n-(n-1)=n+1】 个空指针域。利用二叉链表中的空指针域，
 * 存放指向该结点在某种遍历次序下的前驱和后继结点的指针（这种附加的指针称为"线索"）
 * 2、这种加上了线索的二叉链表称为线索链表，相应的二叉树称为线索二叉树(Threaded BinaryTree)。
 * 根据线索性质的不同，线索二叉树可分为前序线索二叉树、中序线索二叉树和后序线索二叉树三种
 * 3、一个结点的前一个（左子）结点，称为前驱结点；一个结点的后一个（右子）结点，称为后继结点
 *
 * 一旦线索化后，原来的遍历方式不能使用，从新编写 遍历方法，无需使用递归
 *
 * */
public class T3_ThreadedBinaryTreeMain {

    public static void main(String[] args) {
        // 测试 中序线索二叉树的功能
        HeroNode root = new HeroNode(1, "tom");
        HeroNode node02 = new HeroNode(3, "jack");
        HeroNode node03 = new HeroNode(6, "smith");
        HeroNode node04 = new HeroNode(8, "mary");
        HeroNode node05 = new HeroNode(10, "king");
        HeroNode node06 = new HeroNode(14, "dim");

        // 二叉树，后面我们要递归创建，现在简单处理，使用手动创建
        root.setLeft(node02);
        root.setRight(node03);
        node02.setLeft(node04);
        node02.setRight(node05);
        node03.setLeft(node06);

        // 测试线索化
        ThreadedBinaryTree tree = new ThreadedBinaryTree();
        tree.setRoot(root);
        tree.threadedNodes();

        // 测试 ： 以10号为结点
        HeroNode leftNode05 = node05.getLeft();

        HeroNode rightNode05 = node05.getRight();
        System.out.println("10号结点的前驱结点是=" + leftNode05);
        System.out.println("10号结点的后继结点是=" + rightNode05);

        /*
         * 测试遍历
         * 这里的遍历 不能使用 T1_BinaryTreeMain.java 类中遍历方法了。为什么的话 去看代码即可。重新写
         * */
        System.out.println("使用线索化的方法 中序遍历 线索化二叉树 ");
        tree.threadedInfixList(); //8, 3, 10, 1, 14, 6
        System.out.println();

        System.out.println("使用线索化的方法 前序遍历 线索化二叉树 ");
        tree.threadedPreList(); //1 3 8 10 6 14
        System.out.println();

        /*
         * 这里不对 有问题。
         * */
        System.out.println("使用线索化的方法 后序遍历 线索化二叉树 ");
        tree.threadedPostList(); //8 10 3 14 6 1
        System.out.println();

    }

}

