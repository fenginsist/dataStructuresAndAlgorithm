package com.feng.ch12_tree.t3_threadedbinarytree;

/*
 * 创建 HeroNode 结点
 * */
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    /*
     * 说明
     * 1. 如果leftType == 0 表示指向的是左子树, 如果 1 则表示指向前驱结点
     * 2. 如果rightType == 0 表示指向是右子树, 如果 1表示指向后继结点
     * */
    private int leftType;
    private int rightType;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }
}
