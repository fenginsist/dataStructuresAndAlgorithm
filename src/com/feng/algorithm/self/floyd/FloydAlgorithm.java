package com.feng.algorithm.self.floyd;

/**
 * 学习视频：https://www.bilibili.com/video/BV1LE411R7CS
 */
public class FloydAlgorithm {
    public static void main(String[] args) {
        int[][] graph = new int[4][4];
        graph[0][0] = 0;
        graph[0][1] = 5;
        graph[0][2] = Short.MAX_VALUE;
        graph[0][3] = 7;

        graph[1][0] = Short.MAX_VALUE;
        graph[1][1] = 0;
        graph[1][2] = 4;
        graph[1][3] = 2;

        graph[2][0] = 3;
        graph[2][1] = 3;
        graph[2][2] = 0;
        graph[2][3] = 2;

        graph[3][0] = Short.MAX_VALUE;
        graph[3][1] = Short.MAX_VALUE;
        graph[3][2] = 1;
        graph[3][3] = 0;
        int[][] path = new int[4][4];
        Floyd.floyd(graph, path);
        Floyd.printPath(1, 0, path);
    }
}
class Floyd {

    /**
     * 佛洛依德算法，给定邻接矩阵表示的图，和一个存放路径的二维数据path。
     * @param graph
     * @param path
     */
    static void floyd(int[][] graph, int[][] path) {
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
    }

    static void printPath(int u, int v, int[][] path) {
        if (path[u][v] == -1) {
            System.out.printf(u + "->" + v + " ");
        } else {
            int mid = path[u][v];
            printPath(u, mid, path);
            printPath(mid, v, path);
        }
    }
}
