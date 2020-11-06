package com.feng.ch16_avl;

/*
 * 实体 Node 结点
 * 复制的  ch15_binarysorttree 包中的 Node 类
 * 新添加方法：
 *  leftHeight() 左子树高度
 *  rightHeight() 右子树高度
 *  height() 高度 递归
 *  leftRotate() 左旋转
 *  rightRotate() 右旋转
 *
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

    // 返回左子树的高度
    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return left.height();
    }

    // 返回右子树的高度
    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();
    }

    // 返回 以该结点为根结点的树的高度
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    //左旋转方法
    private void leftRotate() {

        //创建新的结点，以当前根结点的值
        Node newNode = new Node(value);
        //把新的结点的左子树设置成当前结点的左子树
        newNode.left = left;
        //把新的结点的右子树设置成带你过去结点的右子树的左子树
        newNode.right = right.left;
        //把当前结点的值替换成右子结点的值
        value = right.value;
        //把当前结点的右子树设置成当前结点右子树的右子树
        right = right.right;
        //把当前结点的左子树(左子结点)设置成新的结点
        left = newNode;

    }

    //右旋转
    private void rightRotate() {
        Node newNode = new Node(value);
        newNode.right = right;
        newNode.left = left.right;
        value = left.value;
        left = left.left;
        right = newNode;
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

        /*
         * 当添加完一个结点后，如果: (右子树的高度-左子树的高度) > 1 , 左旋转
         * */
        if (rightHeight() - leftHeight() > 1) {
            //如果它的右子树的左子树的高度大于它的右子树的右子树的高度
            if (right != null && right.leftHeight() > right.rightHeight()) {
                //先对右子结点进行右旋转
                right.rightRotate();
                //然后在对当前结点进行左旋转
                leftRotate(); //左旋转..
            } else {
                //直接进行左旋转即可
                leftRotate();
            }
            return; //必须要!!!
        }

        /*
         * 当添加完一个结点后，如果 (左子树的高度 - 右子树的高度) > 1, 右旋转
         * */
        if (leftHeight() - rightHeight() > 1) {
            //如果它的左子树的右子树高度大于它的左子树的高度
            if (left != null && left.rightHeight() > left.leftHeight()) {
                //先对当前结点的左结点(左子树)->左旋转
                left.leftRotate();
                //再对当前结点进行右旋转
                rightRotate();
            } else {
                //直接进行右旋转即可
                rightRotate();
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
