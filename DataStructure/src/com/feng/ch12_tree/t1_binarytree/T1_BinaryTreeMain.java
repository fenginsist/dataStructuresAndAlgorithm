package com.feng.ch12_tree.t1_binarytree;

/*
* 二叉树
* 二叉树的 形成
*
* 二叉树的 遍历
* 1、前序遍历
* 2、中序遍历
* 3、后序遍历
* 4、层次遍历
*
* 二叉树的 查找
* 1、前序查找
* 2、中序查找
* 3、后序查找
* 4、层次查找
* */
public class T1_BinaryTreeMain {

    public static void main(String[] args) {
        // 先需要创建一颗二叉树
        BinaryTree binaryTree = new BinaryTree();
        // 创建需要的节点

        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "武松");
        HeroNode node6 = new HeroNode(6, "武大郎");
        HeroNode node7 = new HeroNode(7, "李逵");

        /*
         * 说明，我们先手动创建该二叉树，后面我们学习递归的方式创建二叉树
         * */
        binaryTree.setRoot(root);
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        node3.setRight(node7);

        /*
         * 测试 二叉树 的遍历
         * */
        System.out.println("前序遍历："); // 输出 1 2 4 5 3 6 7
        binaryTree.preOrder();
        System.out.println();

        System.out.println("中序遍历："); // 输出 4 2 5 1 6 3 7
        binaryTree.infixOrder();
        System.out.println();

        System.out.println("后序遍历："); // 输出 4 5 2 6 7 3 1
        binaryTree.postOrder();
        System.out.println();

        System.out.println("层次遍历："); // 输出 1 2 3 4 5 6 7
        binaryTree.middleOrder();
        System.out.println();

        /*
         * 新添加一个节点
         * */
        HeroNode node8 = new HeroNode(8, "关胜");
        node6.setLeft(node8);

        /*
         * 测试 新增加结点 后的遍历
         * */
        System.out.println("添加新节点后的 前序遍历："); // 输出  1 2 3 5 4
        binaryTree.preOrder();
        System.out.println();

        System.out.println("添加新节点后的 中序遍历："); // 输出 2 1 5 3 4
        binaryTree.infixOrder();
        System.out.println();

        System.out.println("添加新节点后的 后序遍历："); // 输出 2 5 4 3 1
        binaryTree.postOrder();
        System.out.println();

        System.out.println("添加新节点后的 层次遍历："); // 输出 2 5 4 3 1
        binaryTree.middleOrder();
        System.out.println();

        /*
         * 测试二叉树的 查找
         * */
        //  递归4次
        System.out.println("二叉树的 前序遍历查找");
        HeroNode heroNode = binaryTree.preOrderSearch(5);
        if (null == heroNode) {
            System.out.printf("没有找到 no = %d 的英雄", 5);
        } else {
            System.out.printf("找到了， 信息为 no= %d name=%s \n", heroNode.getNo(), heroNode.getName());
        }
        System.out.println();

        // 递归 3次
        System.out.println("二叉树的 中序遍历查找");
        HeroNode heroNode1 = binaryTree.infixOrderSearch(5);
        if (null == heroNode1) {
            System.out.printf("没有找到 no = %d 的英雄", 5);
        } else {
            System.out.printf("找到了， 信息为 no= %d name=%s \n", heroNode1.getNo(), heroNode1.getName());
        }
        System.out.println();

        // 递归 2次
        System.out.println("二叉树的 后序遍历查找");
        HeroNode heroNode2 = binaryTree.postOrderSearch(5);
        if (null == heroNode2) {
            System.out.printf("没有找到 no = %d 的英雄\n", 5);
        } else {
            System.out.printf("找到了， 信息为 no= %d name=%s \n", heroNode2.getNo(), heroNode2.getName());
        }
        System.out.println();

        System.out.println("二叉树的 层次遍历查找");
        HeroNode heroNode3 = binaryTree.middleOrderSearch(4);
        if (null == heroNode3) {
            System.out.printf("没有找到 no = %d 的英雄\n", 5);
        } else {
            System.out.printf("找到了， 信息为 no= %d name=%s \n", heroNode3.getNo(), heroNode3.getName());
        }
        System.out.println();

        /*
         * 测试删除结点
         * */
        System.out.println("删除前，前序遍历");
        binaryTree.preOrder(); // 1 2 3 5 4


//        binaryTree.deleteNode(5);
        binaryTree.deleteNode(3);
        System.out.println("删除后，前序遍历");
        binaryTree.preOrder(); // 1 2 3 4 // 1 2
    }
}




