package com.feng.ch13_huffmantree;

/*
* 创建节点类
* // 为了让 Node 对象 持续排序 Collection 集合排序
* 让 Node 实现 Comparable 接口，重写 compareTo() 方法
* */
public class Node implements Comparable<Node> {

    private int value;// 结点权值
    private Node left; // 指向左子结点
    private Node right; // 指向右子结点

    public Node(int value) {
        this.value = value;
    }

    // 前序遍历
    public void preOrder(){
        System.out.println(this);
        if (this.left != null){
            this.left.preOrder();
        }
        if (this.right != null){
            this.right.preOrder();
        }
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

    @Override
    public int compareTo(Node o) {
        // 表示从小到大进行排序  -(this.value - o.value):从大到小排列
        return this.value - o.value;
    }
}
