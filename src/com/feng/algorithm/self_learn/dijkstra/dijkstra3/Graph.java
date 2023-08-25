package com.feng.algorithm.self_learn.dijkstra.dijkstra3;

public interface Graph<V> {
	int edgesSize();	//�ߵ�����
	int  verticesSize() ;		//��������
	void addVertex(V v);	//����һ������
	void addEdge(V from,V to);	//����һ����
	void addEdge(V from,V to,int weight); //����һ����Ȩֵ�ı�
	
	void removeEdge(V from,V to);	//ɾ��һ����
	void removeVertex(V v);			//ɾ��ĳ������
	
	void displayGraph();		//��ʾһ��ͼ
	
	int degree(V v) ;		//���ض���v�Ķ�
	
	void dfs();			//������ȱ���
	void bfs();			//������ȱ���

}
