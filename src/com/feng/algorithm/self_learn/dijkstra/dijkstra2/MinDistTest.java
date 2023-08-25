package com.feng.algorithm.self_learn.dijkstra.dijkstra2;

public class MinDistTest {

	public static void main(String[] args) {
		Dijkstra<Character> directNet = new  Dijkstra<>(10);
		
		directNet.addVertex('A');
		directNet.addVertex('B');
		directNet.addVertex('C');
		directNet.addVertex('D');
		directNet.addVertex('E');
		directNet.addVertex('F');
//		directNet.addVertex('G');
		
//		directNet.addEdge('A', 'B', 4);
//		directNet.addEdge('A', 'C', 6);
//		directNet.addEdge('A', 'D', 6);
//		directNet.addEdge('B', 'C', 1);
//		directNet.addEdge('B', 'E', 7);
//		directNet.addEdge('C', 'E', 6);
//		directNet.addEdge('C', 'F', 4);
//		directNet.addEdge('D', 'C', 2);
//		directNet.addEdge('D', 'F', 5);
//		directNet.addEdge('E', 'G', 6);
//		directNet.addEdge('F', 'E', 1);
//		directNet.addEdge('F', 'G', 8);

		directNet.addEdge('A', 'B', 6);
		directNet.addEdge('A', 'C', 3);
		directNet.addEdge('B', 'D', 5);
		directNet.addEdge('B', 'C', 2);
		directNet.addEdge('C', 'B', 2);
		directNet.addEdge('C', 'D', 3);
		directNet.addEdge('C', 'E', 4);
		directNet.addEdge('D', 'E', 2);
		directNet.addEdge('D', 'F', 3);
		directNet.addEdge('E', 'D', 2);
		directNet.addEdge('E', 'F', 5);
		
		directNet.displayGraph();
		
		directNet.dijkstra('A', 'F');

	}

}
