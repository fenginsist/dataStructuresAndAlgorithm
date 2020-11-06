package com.feng.dataStructure.ch17_graph;

public class GraphMain {

    public static void main(String[] args) {
        // 测试 图是否创建
        int n = 8; // 结点个数
//        String vertexs[] = {"A","B","C","D","E"};
        String vertexs[] = {"1", "2", "3", "4", "5", "6", "7", "8"};

        Graph graph = new Graph(n);

        // 循环添加节点
        for (String vertex : vertexs){
            graph.insertVertex(vertex);
        }

        // 添加边
        // ab ac bc bd be
//        graph.insertEdge(0, 1, 1); // ab
//        graph.insertEdge(0, 2, 1);
//        graph.insertEdge(1, 2, 1);
//        graph.insertEdge(1, 3, 1);
//        graph.insertEdge(1, 4, 1);

        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(3, 7, 1);
        graph.insertEdge(4, 7, 1);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(2, 6, 1);
        graph.insertEdge(5, 6, 1);

        // 显示邻接矩阵
        graph.show();
        System.out.println();

        // 深度遍历
        System.out.println("深度优先遍历");
        graph.depthFirstSearch(); // A->B->C->D->E->    1->2->4->8->5->3->6->7->
        System.out.println();
        System.out.println();

        // 广度遍历
        System.out.println("广度优先遍历");
        graph.broadFirstSearch(); // A=>B=>C=>D=>E=>    1=>2=>3=>4=>5=>6=>7=>8=>
    }
}
