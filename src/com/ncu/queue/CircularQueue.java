package com.ncu.queue;

import java.util.Scanner;

/*
 * 环形队列相对于数组队列做出的调整：
 * 1.front变量的含义调整：front初始值为0，front就指向队列的第一个元素，也就是说arr[front]就是队列的第一个元素
 * 2.rear变量的含义调整：rear初始值为0，rear指向队列的最后一个元素的后一个位置，因为希望空出一个空间做为约定
 * 3.当队列满时，条件是(rear + 1) % maxSize = front
 * 4.对队列为空的条件，rear == front
 * 5.有效的数据的个数为：(rear + maxSize - front) % maxSize
 * 
 */
public class CircularQueue {

	public static void main(String[] args) {
		CircularQueueDemo queue = new CircularQueueDemo(3);
		Scanner scanner = new Scanner(System.in);
		char key;
		boolean loop = true;
		while(loop) {
			System.out.println("队列数据信息");
			queue.showQueue();
//			System.out.println("显示队列：s");
			System.out.println("退出程序：e");
			System.out.println("添加数据到队列：a");
			System.out.println("从队列取出数据：g");
			System.out.println("查看队列头的数据：h");
			key = scanner.next().charAt(0);
			switch (key) {
//			case 's':
//				queue.showQueue();
//				break;
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

class CircularQueueDemo{
	private int maxSize;
	private int front;
	private int rear;
	private int[] arr;
	
	public CircularQueueDemo(int max) {
		maxSize = max + 1;
		front = 0;
		rear = 0;
		arr = new int[maxSize];
	}
	
	public boolean isFull() {
		return (rear + 1) % maxSize == front;//这里是我们设定的一个条件，就是在尾部空出一个位置
	}
	
	public boolean isEmpty() {
		return rear == front;
	}
	
	public void addQueue(int data) {
		if(isFull()) {
			System.out.println("数据已满，不能添加");
			return;
		}
		arr[rear] = data;
		rear = (rear + 1) % maxSize;
	}
	
	public int getQuene() {
		if(isEmpty()) {
			System.out.println("队列为空，不能取数据");
		}
		 int data = arr[front];
		 front = (front + 1) % maxSize;
		return data;
	}
	
	public void showQueue() {
		if (isEmpty()) {
			System.out.println("队列为空，没有数据");
			return;
		}
//		if (front < rear) {
//			for (int i = front; i < rear; i++) {
//				System.out.print(arr[i] + " ");
//			}
//			System.out.println();
//		} else {
//			for (int i = front; i < maxSize; i++) {
//				System.out.print(arr[i] + " ");
//			}
//			for (int i = 0; i < rear; i++) {
//				System.out.print(arr[i] + " ");
//			}
//			System.out.println();
//		}
		int j = 0;
		for (int i = front; i < front + (rear + maxSize - front) % maxSize; i++) {
			System.out.println("arr[" + (++j) + "] = " + arr[i % maxSize]);
			
		}
		j = 0;
	}

	public int headQueue() {
		if(isEmpty()) {
			System.out.println("队列为空，不能取数据");
		}
		return arr[front];
	}
}