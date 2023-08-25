package com.feng.algorithm.self_learn.dijstra.dijstra3;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * @author chp
 *
 * @param <V>
 * 
 * 无向邻接图
 */
public class UndiretedListGraph<V> implements Graph<V> {
	Vertex<V>[] vertexList; // ??? ????
	private int edges; // ???????
	private int vertices; // ?????????

	public UndiretedListGraph(int size) {
		vertexList = new Vertex[size];
		this.edges = 0;
		this.vertices = 0;
	}

	class Vertex<V> {
		V data; // ?????
		LinkedList<Integer> adj; // ????

		Vertex(V data) {
			this.data = data; // ?????
			this.adj = new LinkedList<>(); // �??????????????????
		}
	}

	@Override
	public int edgesSize() {
		// TODO Auto-generated method stub
		return this.edges;
	}

	@Override
	public int verticesSize() {
		// TODO Auto-generated method stub
		return this.vertices;
	}

	@Override
	public void addVertex(V v) {
		// ???????????
		this.vertexList[this.vertices++] = new Vertex<>(v);

	}

	@Override
	public void addEdge(V from, V to) {
		// ?????????
		int i = this.getPosition(from);
		int j = this.getPosition(to);

		this.vertexList[i].adj.add(j);
		this.vertexList[j].adj.add(i);
		this.edges++;
	}

	private int getPosition(V v) {
		// TODO Auto-generated method stub
		for (int i = 0; i < this.vertices; i++) {
			if (this.vertexList[i].data.equals(v)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public void addEdge(V from, V to, int weight) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeEdge(V from, V to) {
		// ????????
		int i = this.getPosition(from);
		int j = this.getPosition(to);

		this.vertexList[i].adj.remove(new Integer(j)); // ?????
		this.vertexList[j].adj.remove(new Integer(i)); // ?????????????????????????????index?????????
		this.edges++;

	}

	@Override
	public void removeVertex(V v) {
		// ??????????
		/*
		 * 1. ???????v?????index 2. ???�????? 3. ???????????????index 4. ??????????????????? 5. ???�??????? 6.
		 * ?????????????????????????????index????????
		 */
		int index = this.getPosition(v);

		this.edges = this.edges - this.degree(v);

		for (int i : this.vertexList[index].adj) {
			this.vertexList[i].adj.remove(new Integer(index));
		}

		for (int i = index; i < this.vertices - 1; i++) {
			this.vertexList[i] = this.vertexList[i + 1];
		}
		this.vertices--;
		for (int i = 0; i < this.vertices; i++) {
			LinkedList<Integer> list = vertexList[i].adj;
			for (int j = 0; j < list.size(); j++) {
				if (list.get(j) > index) {
					list.set(j, list.get(j) - 1);
				}

			}
		}

	}

	@Override
	public void displayGraph() {
		// ???????
		System.out.println("????????????????????");
		for (int i = 0; i < this.vertices; i++) {
			System.out.print("????" + this.vertexList[i].data);
			System.out.println(",????" + this.vertexList[i].adj);
		}

	}

	@Override
	public int degree(V v) {
		// TODO Auto-generated method stub
		return this.vertexList[getPosition(v)].adj.size();
	}

	@Override
	public void dfs() {
		// ??????????
		boolean[] beTraversed = new boolean[this.vertices];
		// ??????????????????false
		beTraversed[0] = true;
		System.out.print("???????????????");
		System.out.print(this.vertexList[0].data);
		this.dfs(0, beTraversed);
		System.out.println();
	}

	private void dfs(int i, boolean[] beTraversed) {
		// ???i?????????????????
		for (int j : this.vertexList[i].adj) {
			if (!beTraversed[j]) {
				beTraversed[j] = true;
				System.out.print(this.vertexList[j].data);
				this.dfs(j, beTraversed);
			}
		}
	}

	@Override
	public void bfs() {
		// ??????????
		boolean[] beTraversed = new boolean[this.vertices];
		// ??????????????????false
		beTraversed[0] = true;
		Queue<Integer> queue = new LinkedList<>();
		beTraversed[0] = true;
		queue.offer(0);

		System.out.print("????????????");
		while (!queue.isEmpty()) {
			int index = queue.poll();
			System.out.print(this.vertexList[index].data);
			for (int i : this.vertexList[index].adj) {
				// ?????????????????????
				if (!beTraversed[i]) {
					beTraversed[i] = true;
					queue.offer(i);
				}
			}
		}
		System.out.println();

	}



}
