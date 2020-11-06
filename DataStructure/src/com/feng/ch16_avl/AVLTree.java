package com.feng.ch16_avl;

/*
* 复制的 二叉排序树 的  tree 代码
* 这里没有变动，主要添加 在 Node 节点实体类中
* */
public class AVLTree {


    private Node root;

    public Node getRoot() {
        return root;
    }

    // 添加
    public void addNode(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.addNode(node);
        }
    }

    // 中序遍历
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉排序树为空~~~");
        }
    }

    // 查找要删除的  结点
    public Node searchWillDeleteNode(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchWillDeleteNode(value);
        }
    }

    // 查找要删除的结点的  父结点
    public Node searchWillDeleteNodeParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchWillDeleteNodeParent(value);
        }
    }

    // 删除结点
    /*
     * 删除结点需要分三种情况
     * 一、第一种情况: 删除叶子节点 (比如：2, 5, 9, 12)
     *  思路
     *  (1) 需求先去找到要删除的结点  targetNode
     *  (2)  找到targetNode 的 父结点 parent
     *  (3) 确定targetNode 的子结点是左子结点还是右子结点
     *  (4) targetNode 是 parent 的左子结点还是右子结点
     *  (5) 如果 targetNode 有左子结点
     *      5. 1 如果 targetNode 是 parent 的左子结点
     *          parent.left = targetNode.left;
     *      5.2  如果 targetNode 是 parent 的右子结点
     *          parent.right = targetNode.left;
     *  (6) 如果targetNode 有右子结点
     *      6.1 如果 targetNode 是 parent 的左子结点
     *          parent.left = targetNode.right;
     *      6.2 如果 targetNode 是 parent 的右子结点
     *          parent.right = targetNode.right
     *
     * 二、第二种情况: 删除只有一颗子树的节点 比如 1
     *  思路
     *  (1) 需求先去找到要删除的结点  targetNode
     *  (2)  找到targetNode 的 父结点 parent
     *  (3) 确定targetNode 的子结点是左子结点还是右子结点
     *  (4) targetNode 是 parent 的左子结点还是右子结点
     *  (5) 如果 targetNode 有左子结点
     *      5. 1 如果 targetNode 是 parent 的左子结点
     *          parent.left = targetNode.left;
     *      5.2  如果 targetNode 是 parent 的右子结点
     *          parent.right = targetNode.left;
     *  (6) 如果targetNode 有右子结点
     *      6.1 如果 targetNode 是 parent 的左子结点
     *          parent.left = targetNode.right;
     *      6.2 如果 targetNode 是 parent 的右子结点
     *          parent.right = targetNode.right
     *
     * 三、情况三 ： 删除有两颗子树的节点. (比如：7, 3，10 )
     * 思路
     * (1) 需求先去找到要删除的结点  targetNode = 10
     * (2)  找到targetNode 的 父结点 parent
     * (3)  从targetNode 的右子树找到最小的结点
     * (4) 用一个临时变量，将 最小结点的值保存 temp = 11
     * (5)  删除该最小结点
     * (6)  targetNode.value = temp
     *
     * */
    public void deleteNode(int value) {
        if (root == null) {
            return;
        } else {
            //1、需求先去找到要删除的结点  targetNode
            Node targetNode = searchWillDeleteNode(value);
            if (targetNode == null) {
                return;
            }
            // 如果发现 root 根结点 没有左右结点，说明要删除的就是根结点，
            if (root.getLeft() == null && root.getRight() == null) {
                root = null;
                return;
            }

            // 去找到 targetNode 的父结点
            Node parentNode = searchWillDeleteNodeParent(value);

            /*
             * 第一种情况：如果要删除的结点是叶子结点
             * */
            if (targetNode.getLeft() == null && targetNode.getRight() == null) {
                // 判断 targetNode 是父结点的左子结点，还是右子结点
                if (parentNode.getLeft() != null && parentNode.getLeft().getValue() == value) { // 是左子结点
                    parentNode.setLeft(null);
                } else if (parentNode.getRight() != null && parentNode.getRight().getValue() == value) { // 是右子结点
                    parentNode.setRight(null);
                }
            }

            /*
             * 第二种情况：如果要删除的结点 是 只有一颗子树的节点（仅有左子结点或仅有右子结点）
             * */
            if (targetNode.getLeft() != null && targetNode.getRight() == null) { // targetNode 仅有 左子结点
                // 判断 targetNode 是父结点的左子结点，还是右子结点
                if (parentNode != null){
                    if (parentNode.getLeft() != null && parentNode.getLeft().getValue() == value) { // targetNode是 parentNode 的左子结点
                        parentNode.setLeft(targetNode.getLeft());
                    } else if (parentNode.getRight() != null && parentNode.getRight().getValue() == value) {// targetNode是 parentNode 的右子结点
                        parentNode.setRight(targetNode.getLeft());
                    }
                } else {
                    root = targetNode.getLeft();
                }
            } else if (targetNode.getLeft() == null && targetNode.getRight() != null) {// targetNode 仅有 右子结点
                if (parentNode != null){
                    if (parentNode.getLeft() != null && parentNode.getLeft().getValue() == value) {
                        parentNode.setLeft(targetNode.getRight());
                    } else if (parentNode.getRight() == null && parentNode.getRight().getValue() == value) {
                        parentNode.setRight(targetNode.getRight());
                    }
                } else{
                    root = targetNode.getRight();
                }
            }

            /*
             * 第三种情况：删除有两颗子树的节点. (比如：7, 3，10 )
             * */
            if (targetNode.getLeft() != null && targetNode.getRight() != null) {
                /*
                 * 执行两个功能：
                 * 1、返回的以 node 为根结点的二叉排序树 的最小结点的值
                 * 2、删除 node 为 根结点的二叉排序树的最小结点
                 * */
                int minValue = deleteRightTreeMin(targetNode.getRight());
                // 将返回的最小值 赋值给 要删除的 目标结点
                targetNode.setValue(minValue);
            }
        }
    }

    /*
     * 方法功能：
     * 1、返回的以 node 为根结点的二叉排序树 的最小结点的值
     * 2、删除 node 为 根结点的二叉排序树的最小结点
     *
     * @param node // 传入的结点 （当做二叉排序树的根结点）
     * @return 返回的 以 node 为根结点的二叉排序树的最小结点的值
     * */
    public int deleteRightTreeMin(Node node) {
        Node target = node;
        while (target.getLeft() != null) {
            target = target.getLeft();
        }
        // 这是 target 指向了最小值
        // 删除最小结点
        deleteNode(target.getValue()); // 这一步 就是删除 叶子结点
        return target.getValue();
    }

}
