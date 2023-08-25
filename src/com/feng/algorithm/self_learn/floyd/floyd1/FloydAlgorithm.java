package com.feng.algorithm.self_learn.floyd.floyd1;

/**
 * 学习视频：https://www.bilibili.com/video/BV1LE411R7CS
 */
public class FloydAlgorithm {
    public static void main(String[] args) {
        int[][] graph = new int[4][4];
        int N = Short.MAX_VALUE;
        graph[0] = new int[]{0, 5, N, 7};
        graph[1] = new int[]{N, 0, 4, 2};
        graph[2] = new int[]{3, 3, 0, 2};
        graph[3] = new int[]{N, N, 1, 0};

        int[][] path = new int[4][4];
        int[][] A = Floyd.floyd(graph, path);
        int u = 1;
        int v = 0;
        Floyd.printPath(u, v, path);
        System.out.println();
        System.out.println(u + "->" + v +" shortest path is :" + A[u][v]);
    }
}
class Floyd {

    /**
     * 佛洛依德算法，给定邻接矩阵表示的图，
     * path[][]:存放路径中间的节点，如果是-1就是直达
     * A[][]:存放任意两个节点之间的距离
     * 举例：从1-0，从A得出距离是6，从path得出 1-3-2-0
     * @param graph
     * @param path
     */
    static int[][] floyd(int[][] graph, int[][] path) {
        int n = graph.length;
        int v, i, j;
        int[][] A = new int[n][n];
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                A[i][j] = graph[i][j];
                path[i][j] = -1;
            }
        }

        for (v = 0; v < n; v++) {
            for (i = 0; i < n; i++) {
                for (j = 0; j < n; j++) {
                    if (A[i][j] > A[i][v] + A[v][j]) {
                        A[i][j] = A[i][v] + A[v][j];
                        path[i][j] = v;
                    }
                }
            }
        }
        return A;
    }

    /**
     * 递归打印路径
     * @param u
     * @param v
     * @param path
     */
    static void printPath(int u, int v, int[][] path) {
        if (path[u][v] == -1) { // 如果等于 -1 。说明就是直达的
            System.out.printf(u + "->" + v + " ");
        } else {
            int mid = path[u][v];
            printPath(u, mid, path);
            printPath(mid, v, path);
        }
    }
}
