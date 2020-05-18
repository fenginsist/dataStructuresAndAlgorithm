package com.feng.ch12_tree.t2_arraybinarytree;

/*
* 顺序储存二叉树
*
* 从数据存储来看，数组存储方式和树的存储方式可以相互转换，即数组可以转换成树，树也可以转换成数组
* 对应的规律为：
* 1、第n个元素的左子节点为  2 * n + 1
* 2、第n个元素的右子节点为  2 * n + 2
* 3、第n个元素的父节点为  (n-1) / 2
* 4、注意 上面的 n : 表示二叉树中的第几个元素(按0开始编号）比如 根节点 的索引 n = 0;
* 也是对应数组里的元素，下标正好也是从0开始的。
*
* 这里将使用数组 来 储存二叉树，进行前序、后序、中序的排列。
*
* 扩展、思考：
* 二叉树转化为数组储存，其实就是二叉树的 层次遍历 后的结果。
* */
public class T2_ArrayBinaryTreeMain {

    public static void main(String[] args) {
//        int array[] = {1, 3, 4, 6, 8, 9, 15};
        int array[] = {1, 2, 3, 4, 5, 6, 7};

        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(array);
        System.out.println("前序序遍历：");
        arrayBinaryTree.preOrder(); // 1 3 6 8   4 9 15
        System.out.println();

        System.out.println("中序遍历：");
        arrayBinaryTree.infixOrder(); // 6 3 8 1 9 4 15
        System.out.println();

        System.out.println("后序遍历：");
        arrayBinaryTree.postOrder(); // 6 8 3 9 15 4 1
    }
}


