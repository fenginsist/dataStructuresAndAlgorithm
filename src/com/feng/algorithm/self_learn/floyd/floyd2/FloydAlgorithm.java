package com.feng.algorithm.self_learn.floyd.floyd2;
import java.util.Arrays;

public class FloydAlgorithm {
    public static void main(String[] args) {
        //创建顶点
        char[] vertex = {'A', 'B', 'C', 'D'};

        //创建邻接矩阵
        int[][] matrix = new int[vertex.length][vertex.length];
        int N = Short.MAX_VALUE;
        matrix[0] = new int[]{0, 5, N, 7};
        matrix[1] = new int[]{N, 0, 4, 2};
        matrix[2] = new int[]{3, 3, 0, 2};
        matrix[3] = new int[]{N, N, 1, 0};

        //创建graph对象
        Graph graph = new Graph(matrix,vertex);
        //调用弗洛伊德算法
        graph.floyd();
//        graph.show();
        graph.printPath(1, 0);
        System.out.println();
        System.out.println("shortest path:"+graph.getPre(1, 0));
    }
}

/**
 * 创建图
 */
class Graph {
    /**
     * 存放顶点的数组
     */
    private char[] vertes;
    /**
     * 保存从各个顶点触发到其他顶点的 距离,最后的结果也是保留在该数组
     */
    private int[][] dis;
    /**
     * 保存到达目标顶点的前驱顶点
     */
    private int[][] pre;

    public Graph(int[][] matrix, char[] vertex) {
        this.vertes = vertex;
        this.dis = matrix;                                  // 存放 路径
        this.pre = new int[vertex.length][vertex.length];   // 存放 路径的代价
        //对pre数组初始化,注意存放的是前驱顶点的下标
        for (int i = 0; i < vertex.length; i++) {
            Arrays.fill(pre[i], -1);
        }
    }

    /**
     * 返回路径所需要的距离
     * @param u
     * @param v
     * @return
     */
    int getPre(int u, int v){
        return dis[u][v];
    }

    /**
     * 打印路径。
     */
    void printPath(int u, int v) {
        if (pre[u][v] == -1) { // 如果等于 -1 。说明就是直达的
            System.out.printf(vertes[u] + "->" + vertes[v] + " ");
        } else {
            int mid = pre[u][v];
            printPath(u, mid);
            printPath(mid, v);
        }
    }

    /**
     * 弗洛伊德算法
     */
    public void floyd() {
        //保存距离
        int len;
        //对中间顶点遍历
        for (int k = 0; k < dis.length; k++) {
            //从i顶点开始出发
            for (int i = 0; i < dis.length; i++) {
                //到达j顶点
                for (int j = 0; j < dis.length; j++) {
                    //求出从i顶点出发经过k到达j的距离 若小于dis[i][j],则进行更新
                    if (dis[i][k] + dis[k][j] < dis[i][j]) {
                        dis[i][j] = dis[i][k] + dis[k][j]; //更新距离
                        pre[i][j] = k; //更新前驱顶点
                    }
                }
            }
        }
    }
}
