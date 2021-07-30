package com.ncu.tree;

/*
 * 顺序二叉树
 */
public class ArrBinaryTreeDemo {

	public static void main(String[] args) {

		int[] arr = {1,2,3,4,5,6,7};
		ArrBinaryTree tree = new ArrBinaryTree(arr);
		System.out.println("前序");
		tree.preOrder();
		System.out.println("中序");
		tree.midOrder();
		System.out.println("后序");
		tree.postOrder();
	}

}

class ArrBinaryTree{
	private int[] arr;
	
	public ArrBinaryTree(int[] arr) {
		this.arr = arr;
	}
	
	//重载，直接把0代入index
	public void preOrder() {
		this.preOrder(0);
	}
	public void midOrder() {
		this.midOrder(0);
	}
	public void postOrder() {
		this.postOrder(0);
	}
	//前序遍历
	public void preOrder(int index) {
		if(arr == null || arr.length == 0) {
			System.out.println("数组为空");
			return;
		}
		System.out.println(arr[index]);
		//向左递归
		if((index * 2 + 1) < arr.length) {
			preOrder(2 * index + 1);
		}
		//向右递归
		if((index * 2 + 2) < arr.length) {
			preOrder(index * 2 + 2);
		}
		
	}
	//中序遍历
	public void midOrder(int index) {
		if(arr == null || arr.length == 0) {
			System.out.println("数组为空");
			return;
		}
		
		//向左递归
		if((index * 2 + 1) < arr.length) {
			midOrder(2 * index + 1);
		}
		System.out.println(arr[index]);
		//向右递归
		if((index * 2 + 2) < arr.length) {
			midOrder(index * 2 + 2);
		}
		
	}
	//后序遍历
	public void postOrder(int index) {
		if(arr == null || arr.length == 0) {
			System.out.println("数组为空");
			return;
		}
		
		//向左递归
		if((index * 2 + 1) < arr.length) {
			postOrder(2 * index + 1);
		}
		//向右递归
		if((index * 2 + 2) < arr.length) {
			postOrder(index * 2 + 2);
		}
		System.out.println(arr[index]);
		
	}
}
