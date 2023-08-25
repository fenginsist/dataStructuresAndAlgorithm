package com.feng.algorithm.self_learn.dijstra.dijstra2;

import java.util.*;

/**
 * @ClassName dijkstra.test.Dijkstra
 * @Description TODO
 * @Author YuanJie
 * @Date 2022/6/2 17:54
 */
public class Dijkstra<V>{
    private List<V> vertexList;
    private int[][] edgeMatrix;
    private int edges;
    private int vertices;
    private int size;

    public Dijkstra(int size) {
        this.size = size;
        vertexList = new ArrayList<>();
        edgeMatrix = new int[size][size];
        this.edges = 0;
        this.vertices = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j) {
                    this.edgeMatrix[i][j] = 0;
                } else {
                    this.edgeMatrix[i][j] = Integer.MAX_VALUE;  //顶点i没有一条边直达顶点j
                }

            }
        }
    }
    public int edgesSize() {
        return this.edges;
    }

    public int verticesSize() {
        return this.vertices;
    }

    public void addVertex(V v) {
        // 加入一个顶点
        this.vertexList.add(v);
        this.vertices++;
    }

    public void addEdge(V from, V to) {
        this.addEdge(from, to, 1);
    }

    public void addEdge(V from, V to, int weight) {
        int i = this.vertexList.indexOf(from);
        int j = this.vertexList.indexOf(to);
        this.edgeMatrix[i][j] = weight;
        this.edges++;

    }

    public void displayGraph() {
        // 显示当前图
        System.out.println("这是一个无向带权图（网、邻接矩阵）：");
        System.out.println("顶点：" + this.vertexList);
        System.out.println("网的邻接矩阵是：");
        for (int i = 0; i < this.vertices; i++) {
            for (int j = 0; j < this.vertices; j++) {
                if (this.edgeMatrix[i][j] == Integer.MAX_VALUE) {
                    System.out.print("∞\t");
                } else {
                    System.out.print(this.edgeMatrix[i][j] + "\t");
                }
            }
            System.out.println();
        }
    }

    public void dijkstra(V v0, V vi) {
        // 从顶点vO出发,查找到vi的最短路径
        ArrayList<Integer> listU = new ArrayList<>(); // listU 记录还未处理的节点
        int[] dist = new int[this.vertices]; // dist[] 记录各个节点到起始节点的最短权值
        int[] path = new int[this.vertices]; // 记录各个节点的上一级节点(用来联系该节点到起始节点的路径)

        int start = this.vertexList.indexOf(v0); // 源点序号
        int end = this.vertexList.indexOf(vi);   // 结束顶点序号

        // 初始化U集合
        for (int i = 0; i < this.vertices; i++) {
            if (i == start) { // S={start}
                continue;
            }
            listU.add(i); // u={vi}/{start}
        }
        // 初始化dist[],path[]
        for (int i = 0; i < this.vertices; i++) {
            dist[i] = this.edgeMatrix[start][i];// dist[]的当前节点权值就等于start->i节点的权值;初始化所有节点到源点的最短距离
            if (this.edgeMatrix[start][i] == Integer.MAX_VALUE) {
                path[i] = -1; // 节点i不可达
            } else {
                path[i] = start; // 若start能直达某点,则表示节点i可以直接访问到start;
            }
        }

        int minIndex; // 记录最小值的节点序号
        do {
            System.out.println("集合U的状态: " + listU);
            minIndex = listU.get(0);// dist数组下标
            // 每次都获取 dist数组中最小值，然后加入到虚拟的S中
            for (int i = 1; i < listU.size(); i++) {
                if (dist[listU.get(i)] < dist[minIndex]) {
                    minIndex = listU.get(i);
                }
            }
            listU.remove((Integer) minIndex);

            // 更新dist和path数组,主要考察minIndex节点纳入S,即新加入节点最短路径变化.
            for (int i = 0; i < this.vertices; i++) {
                if (this.edgeMatrix[minIndex][i] != 0 && this.edgeMatrix[minIndex][i] < Integer.MAX_VALUE) {
                    // 找到 minIndex 的所有邻接点 + minIndex 的值，如果小于邻接点dist[i]的值，则更新
                    if (dist[minIndex] + this.edgeMatrix[minIndex][i] < dist[i]) {
                        // 新加入节点更新
                        dist[i] = this.edgeMatrix[minIndex][i] + dist[minIndex];
                        path[i] = minIndex;
                    }
                }
            }
        } while (minIndex != end && !listU.isEmpty());

        System.out.println("节点" + v0 + "=>" + vi + "最短路径是: " + dist[end]);
        String str = "" + vi;
        int i = end;
        do {
            i = path[i];
            str = this.vertexList.get(i) + "=>" + str;
        } while (i != start);
        System.out.println(str);
    }
}
