package com.ncu.kruskal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class KruskalCase {

	public static void main(String[] args) {
		char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
		int[][] matrix = {
				{  0,  12, INF, INF, INF,  16,  14},
				{ 12,   0,  10, INF, INF,   7, INF},
				{INF,  10,   0,   3,   5,   6, INF},
				{INF, INF,   3,   0,   4, INF, INF},
				{INF, INF,   5,   4,   0,   2,   8},
				{ 16,   7,   6, INF,   2,   0,   9},
				{ 14, INF, INF, INF,   8,   9,   0}
		};
		
		KruskalCase kruskal = new KruskalCase(vertexs, matrix);
		kruskal.print();
		List<EData> list = kruskal.kruskal();
		System.out.println(list);
	
	}
	
	private int edgeNum;
	private char[] vertexs;
	private int[][] matrix;
	private static final int INF = Integer.MAX_VALUE;
	
	public KruskalCase(char[] vertexs, int[][] matrix) {
		this.vertexs = vertexs;
		this.matrix = matrix;
		
		for (int i = 0; i < vertexs.length; i++) {
			for (int j = i + 1; j < vertexs.length; j++) {
				if (matrix[i][j] != INF) {
					this.edgeNum++;
				}
			}
		}
	}
	
	public List<EData> kruskal() {
		int[] ends = new int[edgeNum];
		List<EData> edges = getEdges();
		sort(edges);
		List<EData> res = new ArrayList<EData>();
		
		for (int i = 0; i < edgeNum; i++) {
			int p1 = getPosition(edges.get(i).start);
			int p2 = getPosition(edges.get(i).end);
			
			int m = getEnd(ends, p1);
			int n = getEnd(ends, p2);
			
			if (m != n) {
				ends[m] = n;
				res.add(edges.get(i));
			}
		}
		return res;
		
	}
	
	private int getEnd(int[] ends, int i) {
		while (ends[i] != 0) {
			i = ends[i];
		}
		return i;
	}

	public void print() {
		for (int[] link : matrix) {
			System.out.println(Arrays.toString(link));
		}
	}
	
	private void sort(List<EData> edges) {
		Collections.sort(edges);
	}
	
	private List<EData> getEdges() {
		List<EData> list = new ArrayList<EData>(edgeNum);
		for (int i = 0; i < matrix.length; i++) {
			for (int j =  i + 1; j < matrix.length; j++) {
				if (matrix[i][j] != INF) {
					list.add(new EData(getPointByIndex(i), getPointByIndex(j), matrix[i][j]));
				}
			}
		}
		return list;
	}
	
	private char getPointByIndex(int i) {
		return vertexs[i];
	}
	
	private int getPosition(char ch) {
		for (int i = 0; i < vertexs.length; i++) {
			if (vertexs[i] == ch) {
				return i;
			}
		}
		return -1;
	}
}

class EData implements Comparable<EData>{
	char start;
	char end;
	int weight;
	public EData(char start, char end, int weight) {
		this.start = start;
		this.end = end;
		this.weight = weight;
	}
	
	@Override
	public String toString() {
		return "EData [start=" + start + ", end=" + end + ", weight=" + weight + "]";
	}

	@Override
	public int compareTo(EData o) {
		if (this.weight >= o.weight) {
			return 1;
		} else {
			return -1;	
		}
	}
}
