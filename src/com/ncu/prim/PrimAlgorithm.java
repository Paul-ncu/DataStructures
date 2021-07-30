package com.ncu.prim;

import java.util.Arrays;

public class PrimAlgorithm {
	public static void main(String[] args) {
		char[] data = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
		int verxs = data.length;
		int noEdge = Integer.MAX_VALUE;
		int[][] weight = {
				{noEdge, 5, 7, noEdge, noEdge, noEdge, 2},
				{5, noEdge, noEdge, 9, noEdge, noEdge, 3},
				{7, noEdge, noEdge, noEdge, 8, noEdge, noEdge},
				{noEdge, 9, noEdge, noEdge, noEdge, 4, noEdge},
				{noEdge, noEdge, 8, noEdge, noEdge, 5, 4},
				{noEdge, noEdge, noEdge, 4, 5, noEdge, 6},
				{2, 3, noEdge, noEdge, 4, 6, noEdge}
		};
		
		MGraph mGraph = new MGraph(verxs);
		MinTree minTree = new MinTree();
		minTree.createGraph(mGraph, verxs, data, weight);
		minTree.showGraph(mGraph);
		minTree.pirm(mGraph, 5);
		
	}
}

class MinTree {
	
	public void createGraph(MGraph graph, int verxs, char data[], int[][] weight) {
		for (int i = 0; i < verxs; i++) {
			graph.data[i] = data[i];
			for (int j = 0; j < verxs; j++) {
				graph.weight[i][j] = weight[i][j];
			}
		}
	}
	
	public void showGraph(MGraph graph) {
		for (int[] link : graph.weight) {
			System.out.println(Arrays.toString(link));
		}
	}
	
	public void pirm(MGraph graph, int v) {
		boolean[] visited = new boolean[graph.verxs];
		visited[v] = true;
		int minWeight = Integer.MAX_VALUE;
		int h1 = -1, h2 = -1;
		for (int k = 1; k < graph.verxs; k++) {
			
			for (int i = 0; i < graph.verxs; i++) {
				for (int j = 0; j < graph.verxs; j++) {
					if (visited[i] && !visited[j] && graph.weight[i][j] < minWeight) {
						minWeight = graph.weight[i][j];
						h1 = i;
						h2 = j;
					}
				}
			}
			System.out.println("边<" + graph.data[h1] + "," + graph.data[h2] + "> 权值: " + minWeight);
			visited[h2] = true;
			minWeight = Integer.MAX_VALUE;
		}
	}
}

class MGraph {
	// 图节点的个数
	int verxs;
	char[] data;
	int[][] weight;
	
	public MGraph(int verxs) {
		this.verxs = verxs;
		data = new char[verxs];
		weight = new int[verxs][verxs];
	}
	
}
