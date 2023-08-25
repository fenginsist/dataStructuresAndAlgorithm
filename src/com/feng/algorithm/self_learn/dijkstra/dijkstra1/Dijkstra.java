package com.feng.algorithm.self_learn.dijkstra.dijkstra1;

import java.util.ArrayList;

/**
 * 天勤视频：https://www.bilibili.com/video/BV1C44y1Y75M/
 * 视频:https://www.bilibili.com/video/BV1o44y1B7NM?p=1
 */
public class Dijkstra {

    public static void main(String[] args) {

        int[][] graph = new int[7][7];
        int N = Short.MAX_VALUE;
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                graph[i][j] = Short.MAX_VALUE;
            }
        }
        graph[0] = new int[]{N, 4, 6, 6, N, N, N};
        graph[1] = new int[]{N, N, 1, N, 7, N, N};
        graph[2] = new int[]{N, N, N, N, 6, 4, N};
        graph[3] = new int[]{N, N, 2, N, N, 5, N};
        graph[4] = new int[]{N, N, N, N, N, N, 6};
        graph[5] = new int[]{N, N, N, N, 1, N, 8};
        graph[6] = new int[]{N, N, N, N, N, N, N};

        dijkstra(0, 6, graph);
        System.out.println("end");


    }

    public static void dijkstra(int v0, int vi, int[][] graph) {
        int length = graph.length;
        // 从顶点vO出发,查找到vi的最短路径
        ArrayList<Integer> listU = new ArrayList<>(); // listU 记录还未处理的节点
        int[] dist = new int[length]; // dist[] 记录各个节点到起始节点的最短权值
        int[] path = new int[length]; // 记录各个节点的上一级节点(用来联系该节点到起始节点的路径)

        int start = v0; // 源点序号
        int end = vi;   // 结束顶点序号

        // 初始化U集合
        for (int i = 0; i < length; i++) {
            if (i == start)  // S={start}
                continue;
            listU.add(i); // u={vi}/{start} ，最初始 放的应该是 1，2，3，4，5，6 （假设 v0=0）
        }
        // 初始化 dist[],path[]
        for (int i = 0; i < length; i++) {
            dist[i] = graph[start][i];// dist[]的当前节点权值就等于start->i节点的权值;初始化所有节点到源点的最短距离
            if (graph[start][i] == Short.MAX_VALUE) {
                path[i] = -1; // 节点i不可达
            } else {
                path[i] = start; // 若start能直达某点,则表示节点i可以直接访问到start;
            }
        }
        /**
         * 以上都是初始化，下面才是正式的 Dijkstra 算法
         */
        int minIndex; // 记录最小值的节点序号
        do {
            System.out.println("set u status is: " + listU);
            /**
             * 获取 listU 中 未处理节点中的  dist中的最小值，获得最小值下标赋值给 minIndex。
             */
            minIndex = listU.get(0); // dist数组下标
            // 每次都获取 dist数组中最小值，然后加入到虚拟的S中
            for (int i = 1; i < listU.size(); i++) {
                if (dist[listU.get(i)] < dist[minIndex]) {
                    minIndex = listU.get(i);
                }
            }
            listU.remove((Integer) minIndex);

            // 更新dist和path数组,主要考察minIndex节点纳入S,即新加入节点最短路径变化.
            for (int i = 0; i < length; i++) {
                if (graph[minIndex][i] != 0 && graph[minIndex][i] < Short.MAX_VALUE) { // 邻接点不可等于0并且小于无穷大。因为：距离之前大于0。
                    // 找到 minIndex 的所有邻接点 + minIndex 的值，如果小于邻接点dist[i]的值，则更新
                    if (dist[minIndex] + graph[minIndex][i] < dist[i]) {
                        // 新加入节点更新
                        dist[i] = graph[minIndex][i] + dist[minIndex];
                        path[i] = minIndex;
                    }
                }
            }
        } while (minIndex != end && !listU.isEmpty());

        // 打印最短路径
        System.out.println("node: " + v0 + "=>" + vi + " ,shortest path is:" + dist[end]);
        //
        String str = "" + vi;
        int i = end;
        do {
            i = path[i];
            str = i + "=>" + str;
        } while (i != start);
        System.out.println(str);
    }
}
