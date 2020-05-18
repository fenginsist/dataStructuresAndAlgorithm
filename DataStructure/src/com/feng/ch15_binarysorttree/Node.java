package com.feng.ch15_binarysorttree;

/*
 * 实体 Node 结点
 * */
public class Node {

    private int value;
    private Node left;
    private Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    // 添加节点
    /*
     * 添加节点
     * 递归形式 添加结点，注意需要满足二叉排序树的要求
     * */
    public void addNode(Node node) {
        if (null == node) {
            return;
        }

        // 判断传入的结点的值，和当前子树的根结点的值关系
        if (node.getValue() < this.value) {
            // 如果当前结点左子结点为null
            if (this.left == null) {
                this.left = node;
            } else {
                // 递归向左子树 添加
                this.left.addNode(node);
            }
        } else { // 添加结点值 大于 当前结点值
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.addNode(node);
            }
        }

    }

    // 中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    /**
     * 查找要删除的结点
     *
     * @param value 要删除的结点的值
     * @return 如果找到返回该结点，否则返回 null
     */
    public Node searchWillDeleteNode(int value) {
        if (value == this.value) { // 找到就是该结点
            return this;
        } else if (value < this.value) { // 如果查找的值小于当前结点，向左子树递归查找
            // 如果左子节点为空
            if (this.left == null) {
                return null;
            }
            return this.left.searchWillDeleteNode(value);
        } else { // 如果查找的值不小于当前结点，向右子树递归查找
            if (this.right == null) {
                return null;
            }
            return this.right.searchWillDeleteNode(value);
        }
    }

    // 查找要删除结点的父结点

    /**
     * @param value 要删除的结点的值
     * @return 返回的是要删除的结点的父结点，如果没有就返回 null
     */
    public Node searchWillDeleteNodeParent(int value) {
        // 如果当前结点就是要删除的结点的父结点，就返回
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            // 如果查找的值 小于 当前结点的值，并且当前结点的左子结点不为空，
            if (value < this.value && this.left != null) {
                return this.left.searchWillDeleteNodeParent(value);//向左子树递归查找
            } else if (value >= this.value && this.right != null) {
                return this.right.searchWillDeleteNodeParent(value); // 向右子树递归查找
            } else {
                return null; // m没有找到父结点
            }
        }
    }
}
