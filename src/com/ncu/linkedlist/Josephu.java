package com.ncu.linkedlist;

import java.util.Stack;

public class Josephu {
	public static void main(String[] args) {
//		CircleSingleLinkedList list = new CircleSingleLinkedList();
//		list.addBoy(45);
//		list.showBoy();
//		System.out.println();
//		list.countBoy(1, 2, 45);
		
		Stack<Integer> stack = new Stack<Integer>();
	}
}

class CircleSingleLinkedList {
	//创建一个first节点
	private Boy first = new Boy(0);
	//添加nums个小孩节点构建成一个环形的链表
	public void addBoy(int nums) {
		if(nums < 1) {
			System.out.println("id不正确");
			return;
		}
		Boy lastBoy = null;//辅助指针，尾节点
		for(int i = 1; i <= nums;i++) {
			//根据编号添加节点
			Boy boy = new Boy(i);
			if(i == 1) {
				first = boy;
				first.setNext(first);//构成一个循环
				lastBoy = first;
			} else {
				lastBoy.setNext(boy);//在尾节点后添加新boy节点
				boy.setNext(first);//boy节点next连到first
				lastBoy = boy;//移动尾节点cur的位置到最后的节点（新添加的节点boy）
			}
		}
	}
	
	//遍历当前的环形链表
	public void showBoy() {
		//判断链表是否为空
		if(first == null) {
			System.out.println("没有节点");
			return;
		}
		//
		Boy curBoy = first;//first不能动，所以构建一个辅助指针
		while(true) {
			System.out.println("节点编号为" + curBoy.getId());
			if(curBoy.getNext() == first) {
				break;
			} else {
				curBoy = curBoy.getNext();//节点往后移动
			}
		}
	}
	
	//根据用户的输入，计算出节点出圈的顺序
	/**
	 * 
	 * @description 约瑟夫环形圈问题,
	 * @author xiaohao
	 * @date 2020年4月22日上午11:46:58
	 * @param startNum 表示从第几个节点开始数数
	 * @param countNum 表示数几下
	 * @param nums 表示链表中的节点个数
	 */
	public void countBoy(int startNum, int countNum, int nums) {
		//对链表进行校验
		if(first == null || startNum < 1 || startNum > nums) {
			System.out.println("输入数据有误，重新输入");
			return;
		}
		//创建辅助指针，尾指针
		Boy helper = first;
		//让helper指向最后一个节点
		while(true) {
			if(helper.getNext() == first) {//说明helper已经指向最后的节点
				break;
			}
			//实现指针下移
			helper = helper.getNext();
		}
		//报数前，先让first和helper移动startNum - 1次
		for (int i = 0; i < startNum - 1; i++) {
			first = first.getNext();
			helper = helper.getNext();
		}
		//当小孩报数是，让first和helper指针同时移动
		//
		while(true) {
			//判断圈中是不是只剩下一个节点
			if(helper == first) {
				break;
			}
			//让first和helper同时移动countNum - 1，使得first指向要出圈的节点
			for(int i = 0;i < countNum - 1;i++){
				first = first.getNext();
				helper = helper.getNext();
			}
			//first指向的节点就是要出圈的节点
			System.out.println("出圈节点为" + first.getId());
			first = first.getNext();
			helper.setNext(first);
		}
		System.out.println("最后留在圈中的节点为" + first.getId());
	}
}
class Boy {
	private int id;
	private Boy next;

	public Boy(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Boy getNext() {
		return next;
	}

	public void setNext(Boy next) {
		this.next = next;
	}

	
}