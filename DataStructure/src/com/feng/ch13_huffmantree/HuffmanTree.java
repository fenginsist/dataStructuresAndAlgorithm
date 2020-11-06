package com.feng.ch13_huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * 哈弗曼树
 * 构成赫夫曼树的步骤：
 * 1、从小到大进行排序, 将每一个数据，每个数据都是一个节点 ， 每个节点可以看成是一颗最简单的二叉树
 * 2、取出根节点权值最小的两颗二叉树
 * 3、组成一颗新的二叉树, 该新的二叉树的根节点的权值是前面两颗二叉树根节点权值的和
 * 4、再将这颗新的二叉树，以根节点的权值大小 再次排序， 不断重复  1-2-3-4 的步骤，直到数列中，所有的数据都被处理，就得到一颗赫夫曼树
 *
 * 注意点：这里使用 ArrayList 集合 储存 数组的元素，表示每个元素为一个二叉树，这里仅保存根节点
 * */
public class HuffmanTree {

    public static void main(String[] args) {
        int array[] = {13, 7, 8, 3, 29, 6, 1};
        Node root = createHuffmanTree(array);

        // 测试一把
        preOrder(root);
    }

    // 前序遍历
    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("是空树，不能遍历~~~");
        }
    }

    /*
     * 创建 哈弗曼树的方法
     * @param array 需要创建成哈弗曼树的数组
     * @return 创建好的哈弗曼树root 结点
     * */
    public static Node createHuffmanTree(int[] array) {
        /*
         * 第一步，为了操作方便
         * 1、遍历 array 数组
         * 2、将 array 的每个元素构成 一个 Node
         * 3、将 Node 放入到 ArrayList 中
         * */
        List<Node> nodes = new ArrayList<>();
        for (int value : array) {
            nodes.add(new Node(value));
        }

        /*
         * 第二步：
         * 处理过程 是循环过程
         * 1、从小到大进行排序, 将每一个数据，每个数据都是一个节点 ， 每个节点可以看成是一颗最简单的二叉树
         * 2、取出根节点权值最小的两颗二叉树
         * 3、组成一颗新的二叉树, 该新的二叉树的根节点的权值是前面两颗二叉树根节点权值的和
         * 4、再将这颗新的二叉树，以根节点的权值大小 再次排序， 不断重复  1-2-3-4 的步骤，直到数列中，所有的数据都被处理，就得到一颗赫夫曼树
         * 最后，循环完后，list集合中，只有一个数据，也就是哈弗曼树的根节点。
         * */
        while (nodes.size() > 1) {
            // 排序 从小到大
            Collections.sort(nodes);
//            System.out.println("nodes=" + nodes); //nodes=[Node{value=1}, Node{value=3}, Node{value=6}, Node{value=7}, Node{value=8}, Node{value=13}, Node{value=29}]

            // 取出根节点权值最小的两颗二叉树
            // 1、取出权值最小的结点（二叉树）
            Node leftNode = nodes.get(0);
            // 2、取出权值第二小的结点（二叉树）
            Node rightNode = nodes.get(1);

            // 3、构建一颗新的二叉树
            Node parent = new Node(leftNode.getValue() + rightNode.getValue());
            parent.setLeft(leftNode);
            parent.setRight(rightNode);

            // 4、从 ArrayList 中删除 处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            // 5、将parent 加入到 nodes
            nodes.add(parent);
//            System.out.println("第一次处理后：" + nodes);
        }

        // 返回 哈弗曼树的 root 结点
        return nodes.get(0);
    }
}
