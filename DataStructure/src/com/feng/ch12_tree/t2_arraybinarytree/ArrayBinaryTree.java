package com.feng.ch12_tree.t2_arraybinarytree;

/*
 * 编写一个 ArrayBinaryTree， 实现顺序存储二叉树遍历
 * 数组 和 二叉树的 对应规律
 * */
class ArrayBinaryTree {

    private int[] array; // 存储数据结点的数组

    public ArrayBinaryTree(int[] array) {
        this.array = array;
    }

    // 重载perOrder（）
    public void preOrder() {
        this.preOrder(0);
    }
    public void infixOrder() {
        this.infixOrder(0);
    }
    public void postOrder() {
        this.postOrder(0);
    }



    /*
     * 编写一个方法，完成顺序储存二叉树的一个前序遍历
     * @param index 数组的下标
     * */
    public void preOrder(int index) {
        // 如果数组为空，或者 array。length = 0
        if (array == null || array.length == 0) {
            System.out.println("数组为空，不能按照二叉树的前序遍历");
        }
        // 输出当前这个元素
        System.out.print(array[index] + " ");
        // 向左递归遍历
        if ((index * 2 + 1) < array.length) {
            preOrder(2 * index + 1);
        }

        // 向右递归遍历
        if ((index * 2 + 2) < array.length) {
            preOrder(2 * index + 2);
        }
    }

    /*
     * 方法，完成顺序储存二叉树的一个中序遍历
     * @param index 数组的下标
     * */
    public void infixOrder(int index){
        // 如果数组为空，或者 array。length = 0
        if (array == null || array.length == 0) {
            System.out.println("数组为空，不能按照二叉树的前序遍历");
        }
        // 向左递归遍历
        if ((index * 2 + 1) < array.length) {
            infixOrder(2 * index + 1);
        }
        // 输出当前这个元素
        System.out.print(array[index] + " ");
        // 向右递归遍历
        if ((index * 2 + 2) < array.length) {
            infixOrder(2 * index + 2);
        }

    }

    /*
     * 方法，完成顺序储存二叉树的一个后序遍历
     * @param index 数组的下标
     * */
    public void postOrder(int index){
        // 如果数组为空，或者 array。length = 0
        if (array == null || array.length == 0) {
            System.out.println("数组为空，不能按照二叉树的前序遍历");
        }
        // 向左递归遍历
        if ((index * 2 + 1) < array.length) {
            postOrder(2 * index + 1);
        }
        // 向右递归遍历
        if ((index * 2 + 2) < array.length) {
            postOrder(2 * index + 2);
        }
        // 输出当前这个元素
        System.out.print(array[index] + " ");
    }


}
