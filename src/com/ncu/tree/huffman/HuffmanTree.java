package com.ncu.tree.huffman;

import java.util.ArrayList;
import java.util.Collections;

/*
 * 构成Huffman树的步骤：
 * 1.从小到大进行排序，将每一个数据，每一个数据都是一个节点，
 *   每个节点可以看成是一颗最简单的二叉树
 * 2.取出根节点权值最小的两棵二叉树
 * 3.组成一棵新的二叉树，该新的二叉树的根节点的权值是前面两棵二叉树根节点权值的和
 * 4.再将这颗新的二叉树，以根节点的权值大小再次排序，不断重复1-2-3-4的步骤，
 *   直到数列中，所有的数据都被处理，就得到一棵Huffman树
 */

public class HuffmanTree {

	public static void main(String[] args) {
		int arr[] = {13, 7, 8, 3, 29, 6, 1};
		Node huffmanTree = createHuffmanTree(arr);
		huffmanTree.preOrder();
	}
	
	// 创建Huffman树的方法
	public static Node createHuffmanTree(int[] arr) {
		
		ArrayList<Node> listNodes = new ArrayList<Node>();
		for (int value : arr) {
			listNodes.add(new Node(value));
		}
		
		while(listNodes.size() > 1) {
			// 排序从小到大
			Collections.sort(listNodes);
			//System.out.println(listNodes);
			
			// 取出权值最小的节点
			// 一个节点也可以看成是二叉树
			// 取出权值最小的节点
			Node leftNode = listNodes.get(0);
			// 取出权值次小的节点
			Node rightNode = listNodes.get(1);
			
			// 构建一个新的二叉树
			Node parent = new Node(leftNode.value + rightNode.value);
			parent.left = leftNode;
			parent.right = rightNode;
			
			// listNodes中删除处理过的二叉树
			listNodes.remove(leftNode);
			listNodes.remove(rightNode);
			
			// 再将新的节点加入到listNodes中
			listNodes.add(parent);
		}
		Node node = listNodes.get(0);
		return node;
	}
	
}
// 创建节点类
// 为了让Node 对象支持排序Collection集合排序
// 让Node 实现Comparable接口
class Node implements Comparable<Node>{
	int value;// 节点权值
	Node left;// 指向左子节点
	Node right;// 指向右子节点
	
	public Node(int value) {
		this.value = value;
	}
	
	public void preOrder() {
		System.out.println(this.value);
		if (this.left != null) {
			this.left.preOrder();
		}
		if (this.right != null) {
			this.right.preOrder();
		}
	}
	
	public Node(int value, Node left, Node right) {
		super();
		this.value = value;
		this.left = left;
		this.right = right;
	}

	@Override
	public String toString() {
		return "Node [value=" + value + ", left=" + left + ", right=" + right + "]";
	}

	@Override
	public int compareTo(Node o) {
		return this.value - o.value;
	}
}