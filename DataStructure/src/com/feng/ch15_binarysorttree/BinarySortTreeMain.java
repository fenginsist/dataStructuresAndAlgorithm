package com.feng.ch15_binarysorttree;

/*
 * 二叉排序树测试类
 * 主要学习
 * 1、二叉排序树的定义： BST: (Binary Sort(Search) Tree), 对于二叉排序树的任何一个非叶子节点，
 *                   要求左子节点的值比当前节点的值小，右子节点的值比当前节点的值大。
 * 2、二叉排序树的添加
 * 3、二叉排序树的删除：删除分三种情况
 *    3.1 删除叶子结点的情况
 *    3.2 删除仅有一颗子树的情况
 *    3.3 删除有两颗子树的情况
 *
 * 最后有个bug 需要注意一点，在删除 仅有一颗子树的请求，如果删除的为根结点，其返回的 父结点是为 null 的，所以要对其父结点 判断 是否为空。
 * */
public class BinarySortTreeMain {

    public static void main(String[] args) {

        int[] array = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree = new BinarySortTree();

        // 循环添加结点 到 二叉排序树
        for (int i = 0; i < array.length; i++) {
            binarySortTree.addNode(new Node(array[i]));
        }

        // 中序遍历二叉树
        System.out.println("中序遍历二叉树：");
        binarySortTree.infixOrder(); // 1 3 5 7 9 10 12  说明 ： 中序遍历后 就是升序排列的


        // 删除叶子结点
        binarySortTree.deleteNode(2);
        binarySortTree.deleteNode(5);
        binarySortTree.deleteNode(9);
        binarySortTree.deleteNode(12);
        System.out.println("删除叶子结点 后的中序遍历：");
        binarySortTree.infixOrder();

        // 删除有一颗子树的结点
//        binarySortTree.deleteNode(1);
//        System.out.println("删除只有一个子树的 结点后的中序遍历：");
//        binarySortTree.infixOrder();

        // 删除两颗子树的结点
//        binarySortTree.deleteNode(3);
//        System.out.println("删除有两个结点的目标结点 后的中序遍历：");
//        binarySortTree.infixOrder();

        // 删除所有结点
        binarySortTree.deleteNode(2);
        binarySortTree.deleteNode(5);
        binarySortTree.deleteNode(9);
        binarySortTree.deleteNode(12);
        binarySortTree.deleteNode(7);
        binarySortTree.deleteNode(3);
        binarySortTree.deleteNode(10);
        binarySortTree.deleteNode(1);
        System.out.println("删除所有结点 后的中序遍历：");
        binarySortTree.infixOrder();

    }
}
