package com.feng.algorithm.self.dijstra;

import java.util.ArrayList;

/**
 * 天勤视频：https://www.bilibili.com/video/BV1C44y1Y75M/
 * 视频:https://www.bilibili.com/video/BV1o44y1B7NM?p=1
 */
public class Test {

    public static void main(String[] args) {

        int[][] graph = new int[7][7];
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                graph[i][j] = Short.MAX_VALUE;
            }
        }
        graph[0][1] = 4;
        graph[0][2] = 6;
        graph[0][3] = 6;
        graph[1][2] = 1;
        graph[1][4] = 7;
        graph[2][4] = 6;
        graph[2][5] = 4;
        graph[3][5] = 5;
        graph[4][6] = 6;
        graph[5][4] = 1;
        graph[5][6] = 7;

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
            if (i == start) { // S={start}
                continue;
            }
            listU.add(i); // u={vi}/{start}
        }
        // 初始化dist[],path[]
        for (int i = 0; i < length; i++) {
            dist[i] = graph[start][i];// dist[]的当前节点权值就等于start->i节点的权值;初始化所有节点到源点的最短距离
            if (graph[start][i] == Short.MAX_VALUE) {
                path[i] = -1; // 节点i不可达
            } else {
                path[i] = start; // 若start能直达某点,则表示节点i可以直接访问到start;
            }
        }

        int minIndex; // 记录最小值的节点序号
        // int minIndexByI=0;
        do {
            System.out.println("集合U的状态: " + listU);
            minIndex = listU.get(0);// dist数组下标
            // 每次都获取 dist数组中最小值，然后加入到虚拟的S中
            for (int i = 1; i < listU.size(); i++) {
                if (dist[listU.get(i)] < dist[minIndex]) {
                    minIndex = listU.get(i);
                    // minIndexByI = i;
                }
            }
            listU.remove((Integer) minIndex);
            // listU.remove(minIndexByI);

            // 更新dist和path数组,主要考察minIndex节点纳入S,即新加入节点最短路径变化.
            for (int i = 0; i < length; i++) {
                if (graph[minIndex][i] != 0 && graph[minIndex][i] < Short.MAX_VALUE) {
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
        System.out.println("节点" + v0 + "=>" + vi + "最短路径是: " + dist[end]);
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
