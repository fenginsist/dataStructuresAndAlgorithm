package com.feng.dataStructure.ch16_avl;

/*
* 二叉平衡树
* */
public class AVLTreeMain {

    public static void main(String[] args) {
//        int[] array = {4, 3, 6, 5, 7, 8}; // 右子树 高于 左子树 ，需要左旋转
//        int[] array = { 10, 12, 8, 9, 7, 6 }; // 左子树 高于 右子树，需要右旋转
        int[] array = { 10, 11, 7, 6, 8, 9 };

        // 创建一个 AVLTree 对象
        AVLTree avlTree = new AVLTree();
        // 添加节点
        for (int i = 0; i < array.length ; i++){
            avlTree.addNode(new Node(array[i]));
        }

        // 遍历
        System.out.println("中序遍历：");
        avlTree.infixOrder();

//        System.out.println("在没有平衡处理前：");
//        System.out.println("树的高度="+avlTree.getRoot().height()); //4
//        System.out.println("右子树的高度="+avlTree.getRoot().rightHeight()); //1
//        System.out.println("左子树的高度="+avlTree.getRoot().leftHeight()); //3
//        System.out.println("当前根结点："+avlTree.getRoot());

        System.out.println("平衡处理后：");
        System.out.println("树的高度="+avlTree.getRoot().height()); //
        System.out.println("右子树的高度="+avlTree.getRoot().rightHeight()); //
        System.out.println("左子树的高度="+avlTree.getRoot().leftHeight()); //
        System.out.println("当前根结点："+avlTree.getRoot());

    }
}
