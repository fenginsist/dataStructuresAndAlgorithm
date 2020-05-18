package com.feng.ch17_graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {

    private ArrayList<String> vertexList; // 储存定点集合
    private int[][] edges; // 存储图对应的邻接矩阵
    private int numOfEdges;// 表示边的数目

    //定义给数组boolean[], 记录某个结点是否被访问
    private boolean[] isVisited;

    // 构造器
    public Graph(int n) {
        // 初始化矩阵和 vertexList
        this.vertexList = new ArrayList<String>(n);
        this.edges = new int[n][n];
        this.numOfEdges = 0;

    }

    /*
     * 深度 优先遍历的方法
     * */

    // 得到 第一个邻接结点 的下标 w

    /**
     * @param index 传入的下标的行
     * @return 如果存在就返回对应的下标，否则返回 -1
     */
    public int getFirstNeighbor(int index) {
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[index][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    // 根据前一个邻接结点的下标 来 获取下一个邻接结点
    public int getNextNeighbor(int v1, int v2) {
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    // 深度优先遍历算法
    // i 第一次就是 0
    public void dfs(boolean isVisited[], int i) {
        // 首先我们访问该结点，输出
        System.out.print(getValueByIndex(i) + "->");
        // 将结点设置为已经访问
        isVisited[i] = true;
        // 查找结点 i 的第一个邻接结点 w (w就是下标)  （i, w） 就是直接关系，前提 w 不为 -1
        int w = getFirstNeighbor(i);

        while (w != -1) { // 说明 有邻接结点
            if (!isVisited[w]) { // 为 true时，说明未被访问。
                dfs(isVisited, w); // 递归
            }
            // 如果 w 结点被访问， 则根据前一个邻接结点的下标 来 获取下一个邻接结点
            w = getNextNeighbor(i, w);


        }
    }

    // 对 dfs 进行一个重载，遍历所有结点，并进行 dfs
    public void depthFirstSearch() {
        isVisited = new boolean[vertexList.size()];
        // 遍历所有结点，进行dfs（回溯）
        for (int i = 0; i < getNumOfVertex(); i++) { // getNumOfVertex() 结点个数
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

    /*
     * 广度 优先遍历的方法， 没用递归
     * */

    // 对一个节点进行广度优先遍历的方法
    private void bfs(boolean[] isVisited, int i) {
        int u; // 表示队列的头结点对应下标
        int w; // 邻接结点 w
        //队列，记录结点访问的顺序
        LinkedList queue = new LinkedList();
        //访问结点，输出结点信息
        System.out.print(getValueByIndex(i) + "=>");
        //标记为已访问
        isVisited[i] = true;
        //将结点加入队列
        queue.addLast(i);

        while (!queue.isEmpty()) {
            //取出队列的头结点下标
            u = (Integer) queue.removeFirst();
            //得到第一个邻接结点的下标 w
            w = getFirstNeighbor(u);
            while (w != -1) {//找到
                //是否访问过
                if (!isVisited[w]) {
                    System.out.print(getValueByIndex(w) + "=>");
                    //标记已经访问
                    isVisited[w] = true;
                    //入队
                    queue.addLast(w);
                }
                //以u为前驱点(起始点)，找w后面的下一个邻结点
                w = getNextNeighbor(u, w); //体现出我们的广度优先  // 以 u 为一行的 下一个 w 的结点
            }
        }
    }

    //遍历所有的结点，都进行广度优先搜索
    public void broadFirstSearch() {
        isVisited = new boolean[vertexList.size()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
    }


    /*
     * 图中常用方法
     * */
    // 返回结点的个数
    public int getNumOfVertex() {
        return vertexList.size();
    }
    // 得到的边数目
    // getNumOfEdges() 方法

    // 返回结点 i （下标） 对应的数据 0->"A" 1->"B" 2->"C"
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    // 返回v1 和 v2 的权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    // 显示图对应的矩阵
    public void show() {
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }

    // 插入 结点
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    // 添加边

    /**
     * @param v1     表示点的下标 即第几个定点 “A”-“B” "A"->0 "B"->1
     * @param v2     第二个顶点对应的下标
     * @param weight 表示关联 ， 0或者1
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight; // 反向也可以
        numOfEdges++;
    }


    public ArrayList<String> getVertexList() {
        return vertexList;
    }

    public void setVertexList(ArrayList<String> vertexList) {
        this.vertexList = vertexList;
    }

    public int[][] getEdges() {
        return edges;
    }

    public void setEdges(int[][] edges) {
        this.edges = edges;
    }

    public int getNumOfEdges() {
        return numOfEdges;
    }

    public void setNumOfEdges(int numOfEdges) {
        this.numOfEdges = numOfEdges;
    }
}
