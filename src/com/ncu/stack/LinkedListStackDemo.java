package com.ncu.stack;
/*
 * 用单项列表模拟栈：
 * 逆序打印单链表总共有三种方法
 * 	1、将单链表的数据添加在一个数组中，然后逆序遍历数组
 * 	2、将单链表的数据放入栈中，然后遍历栈
 * 	3、将单链表的数据存放的位置颠倒
 */
public class LinkedListStackDemo {
	public static void main(String[] args) {
		Node n1 = new Node(1);
		Node n2 = new Node(2);
		Node n3 = new Node(3);
		Node n4 = new Node(4);
		Node n5 = new Node(5);
		
		LinkedListStack stack = new LinkedListStack();
		stack.push(n1);
		stack.push(n2);
		stack.push(n3);
		stack.push(n4);
		stack.push(n5);

		
	}
}

class LinkedListStack {

	private Node head = new Node(0);//头指针
	
	public void push(Node node) {
		Node temp = head;
		while(true) {
			//找到最后一个节点
			if(temp.getNext() == null) {
				break;
			}
			temp = temp.getNext();
		}
		temp.setNext(node);
	}

	public Node pop() {
		Node temp = head.getNext();
		if(temp == null) {
			throw new RuntimeException("链表为空");
		}
		//找到倒数第二个节点
		while(temp != null) {
			if(temp.getNext().getNext() == null) {
				break;
			}
			temp = temp.getNext();
		}
		Node receive = temp.getNext();//用receive去接受最后的一个节点
		temp.setNext(null);//将最后一个节点从链表中去除
		return receive;
	}

	
}

class Node {
	private int id;
	private Node next;

	public Node(int id) {
		super();
		this.id = id;
	}

	public Node() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	@Override
	public String toString() {
		return "Node [id=" + id + "]";
	}

}