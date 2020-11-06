package com.feng.ch12_tree.t3_threadedbinarytree;

/*
 * 定义 ThreadedBinaryTree 实现了线索化功能的二叉树
 * */
class ThreadedBinaryTree {
    private HeroNode root;

    // 为了实现线索化，需要创建要给指向当前结点的前驱结点的指针
    // 在递归进行线索化时，pre 总是要保留前一个结点
    private HeroNode pre = null;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    // 重载 threadedNodes
    public void threadedNodes() {
        this.threadedNodes(root);
    }

    /*
     * 编写对二叉树 进行中序线索化的方法
     *
     * @param node 就是当前需要线索化的结点
     * */
    public void threadedNodes(HeroNode node) {
        // node == null。不能线索化
        if (node == null) {
            return;
        }
        // (1) 先线索化左子树
        threadedNodes(node.getLeft());

        // (2) 再线索化当前结点
        // 2.1 先处理当前结点的前驱结点
        // 以8结点来理解: 8结点的.left = null , 8结点的.leftType = 1
        if (node.getLeft() == null) {
            // 让当前结点的左指针指向前驱结点
            node.setLeft(pre);
            // 修改当前结点的左指针的类型，指向前驱结点
            node.setLeftType(1);
        }
        // 2.2 处理后继节点
        if (pre != null && pre.getRight() == null) {
            // 前驱结点的右指针指向当前结点
            pre.setRight(node);
            // 修改前驱结点的右指针类型
            pre.setRightType(1);
        }
        // !!! 每处理一个结点后，让当前结点是下一个结点的前驱结点
        pre = node;

        // (3) 先线索化右子树
        threadedNodes(node.getRight());
    }

    /*
     * 中序遍历 线索化二叉树的方法
     * */
    public void threadedInfixList() {
        // 定义一个变量，储存当前遍历的节点，从root开始
        HeroNode node = root;
        if (root == null) {
            System.out.println("链表为空，无法遍历");
        }
        while (node != null) {
            // 循环的找到 leftType == 1 的结点，第一个找到就是 8 结点
            // 后面随着遍历而变化，因为当 leftType== 1 时。说明该结点是按照线索化
            // 处理后的有效节点
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }

            // 打印当前这个结点
            System.out.println(node);

            // 如果当前结点的右指针指向的是后继结点，就一直输出
            while (node.getRightType() == 1) {
                // 获取到当前结点的后继结点
                node = node.getRight();
                System.out.println(node);
            }
            // 替换这个遍历的节点
            node = node.getRight();
        }
    }

    /*
     * 前序遍历 线索化二叉树的方法
     * */
    public void threadedPreList() {
        // 定义一个变量，储存当前遍历的节点，从root开始
        HeroNode node = root;
        if (root == null) {
            System.out.println("链表为空，无法遍历");
        }

        while (node != null) {
            // 打印当前这个结点
            System.out.println(node);

            // 循环的找到 leftType == 1 的结点，第一个找到就是 8 结点
            // 后面随着遍历而变化，因为当 leftType == 1 时。说明该结点是按照线索化
            // 处理后的有效节点
            while (node.getLeftType() == 0) {
                node = node.getLeft();
                System.out.println(node);
            }

            // 如果当前结点的右指针指向的是后继结点，就一直输出
            while (node.getRightType() == 1) {
                // 获取到当前结点的后继结点
//                System.out.println(node);
                node = node.getRight();
            }
            // 替换这个遍历的节点
            node = node.getRight();
        }
    }

    /*
     * 后序遍历 线索化二叉树的方法
     * */
    public void threadedPostList() {
        // 定义一个变量，储存当前遍历的节点，从root开始
        HeroNode node = root;
        if (root == null) {
            System.out.println("链表为空，无法遍历");
        }

        while (node != null) {
            // 循环的找到 leftType == 1 的结点，第一个找到就是 8 结点
            // 后面随着遍历而变化，因为当 leftType == 1 时。说明该结点是按照线索化
            // 处理后的有效节点
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }

            // 如果当前结点的右指针指向的是后继结点，就一直输出
            while (node.getRightType() == 1) {
                // 获取到当前结点的后继结点
                System.out.println(node);
                node = node.getRight();
            }

            // 打印当前这个结点
            System.out.println(node);
            // 替换这个遍历的节点
            node = node.getRight();
        }
    }
}
