package com.ncu.queue;

import java.util.Scanner;

/*
 * 目前数组使用一次就不能使用了，没有达到复用的功能
 * 改进成环形队列可以达到这一功能。
 */
public class ArrayQueueDemo {

	public static void main(String[] args) {
		ArrayQueue queue = new ArrayQueue(3);
		Scanner scanner = new Scanner(System.in);
		char key;
		boolean loop = true;
		while(loop) {
			System.out.println("显示队列：s");
			System.out.println("退出程序：e");
			System.out.println("添加数据到队列：a");
			System.out.println("从队列取出数据：g");
			System.out.println("查看队列头的数据：h");
			key = scanner.next().charAt(0);
			switch (key) {
			case 's':
				queue.showQueue();
				break;
			case 'e':
				loop = false;
				break;
			case 'a':
				System.out.println("请输入一个数据：");
				queue.addQueue(scanner.nextInt());
				break;
			case 'g':
				System.out.println("得到的数据为：" + queue.getQuene());
				break;
			case 'h':
				System.out.println("头数据为：" + queue.headQueue());
				break;
			default:
				break;
			}
		}
		scanner.close();
		System.out.println("退出程序");
		
	}
	
}

//使用数组模拟队列
class ArrayQueue{
	private int maxSize;//表示数组的最大容量
	private int front;//队列头
	private int rear;//队列尾
	private int[] arr;//该数组用于存放数据，模拟队列
	
	//创建队列构造器
	public ArrayQueue(int arrMaxSize) {
		maxSize = arrMaxSize;
		arr = new int[maxSize];
		front = -1;//指向队列头部的前一个位置
		rear = -1;//指向队列尾部数据的位置
	}
	
	//判断队列是否满
	public boolean isFull() {
		return rear == maxSize - 1;
	}
	
	//判断队列是否为空
	public boolean isEmpty() {
		return rear == front;
	}
	//添加数据到队列
	public void addQueue(int data) {
		if(isFull()) {
			System.out.println("数据已满，无法添加");
			return;
		} 
		//rear++;
		arr[++rear] = data;
	}
	//获取队列的数据
	public int getQuene() {
		if(isEmpty()) {
			throw new RuntimeException("队列为空，不能取数据");//throw会直接return
		}
		//front++;
		return arr[++front];
	}
	//遍历队列的数据
	public void showQueue() {
		if(isEmpty()) {
			System.out.println("队列为空，没有数据");
			return;
		}
		for (int i = front + 1; i <= rear; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
	public int headQueue() {
		if(isEmpty()) {
			throw new RuntimeException("队列为空，不能取数据");
		}
		return arr[front + 1];
	}	
}
