package com.feng.ch12_tree.t1_binarytree;

import java.util.LinkedList;

/*
 * 定义 BinaryTree 二叉树
 * */
class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    // 前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    // 中序遍历
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    // 后序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    /*
     * 层次遍历
     * */
    public void middleOrder() {
        if (this.root != null) {
            LinkedList<HeroNode> queue = new LinkedList();
            queue.add(root);
            HeroNode current = null;

            while (!queue.isEmpty()) {
                current = queue.poll();//出队队头元素并访问
                System.out.println(current);
                if (current.getLeft() != null) {//如果当前节点的左节点不为空入队
                    queue.offer(current.getLeft());
                }
                if (current.getRight() != null) {//如果当前节点的右节点不为空，把右节点入队
                    queue.offer(current.getRight());
                }
            }
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    /*
     * 前序遍历查找
     * */
    public HeroNode preOrderSearch(int no) {
        if (root != null) {
            return root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    /*
     * 中序遍历查找
     * */
    public HeroNode infixOrderSearch(int no) {
        if (root != null) {
            return root.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    /*
     * 后序遍历查找
     * */
    public HeroNode postOrderSearch(int no) {
        if (root != null) {
            return root.postOrderSearch(no);
        } else {
            return null;
        }
    }

    /*
     * 层次遍历查找
     * */
    public HeroNode middleOrderSearch(int no) {
        if (root != null) {
            LinkedList<HeroNode> queue = new LinkedList();
            queue.add(root);
            HeroNode current = null;
            HeroNode result = null;

            while (!queue.isEmpty()) {
                current = queue.poll();//出队队头元素并访问
                if (no == current.getNo()){
                    result = current;
                    break;
                }
                if (current.getLeft() != null) {//如果当前节点的左节点不为空入队
                    queue.offer(current.getLeft());
                }
                if (current.getRight() != null) {//如果当前节点的右节点不为空，把右节点入队
                    queue.offer(current.getRight());
                }
            }
            return result;
        } else {
            return null;
        }
    }


    /*
     * 递归删除结点
     * */
    public void deleteNode(int no) {
        if (root != null) {
            //  如果只有一个节点，这里立即判断root 是不是就是要删除结点
            if (root.getNo() == no) {
                root = null;
            } else {
                root.deleteNode(no);
            }
        } else {
            System.out.println("空树，不能删除~~");
        }
    }
}
