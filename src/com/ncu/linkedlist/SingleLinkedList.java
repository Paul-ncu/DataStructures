package com.ncu.linkedlist;

import java.util.Stack;

/*
 * 实现链表的增删改查功能的要点是：
 * 1.找到需要插入的位置（通常找到需要插入位置的前一个位置）
 * 2.把链表拆开再连起来
 *
 */
public class SingleLinkedList {
	public static void main(String[] args) {
		HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
		HeroNode hero2 = new HeroNode(2, "吴用", "智多星");
		HeroNode hero3 = new HeroNode(3, "卢俊义", "玉麒麟");
		HeroNode hero4 = new HeroNode(4, "公孙胜", "入云龙");
		HeroNode hero5 = new HeroNode(5, "林冲", "豹子头");
		HeroNode hero6 = new HeroNode(6, "李逵", "黑旋风");
		HeroNode hero7 = new HeroNode(7, "张顺", "浪里白条");
		HeroNode hero8 = new HeroNode(8, "秦明", "霹雳火");
		HeroNode hero9 = new HeroNode(9, "王英", "矮脚虎");
		
		SingleLinkedListDemo list1 = new SingleLinkedListDemo();
		SingleLinkedListDemo list2 = new SingleLinkedListDemo();
		
		list1.addByNo(hero2);
		list2.addByNo(hero1);
		list1.addByNo(hero3);
		list2.addByNo(hero4);
		list1.addByNo(hero5);
		list2.addByNo(hero6);
		list1.addByNo(hero7);
		list2.addByNo(hero8);
		list1.addByNo(hero9);
	
		
		System.out.println("list1");
		list1.showList();
		System.out.println("list2");
		list2.showList();
		System.out.println("marge");
		HeroNode node = marge(list1.getHead(), list2.getHead());
		
//		System.out.println(node);
		while(node.next != null) {
			System.out.println(node.next);
			node = node.next;
		}
		
		
		//System.out.println(getk(-11, list.getHead()));
		
//		getListOfReverse(list.getHead());
//		System.out.println();
//		list.showList();
//		System.out.println();
//		reversePrint(list.getHead());
		
		
		
//		System.out.println();
		//更新no = 4的节点
//		list.update(new HeroNode(4,"林冲","豹子头"));
//		
//		list.showList();
		//删除节点
//		list.delete(3);
//		list.delete(2);
//		list.showList();
//		System.out.println();
//		System.out.println(getLength(list.getHead()));
//		list.delete(1);
//		list.delete(4);
//		System.out.println();
//		list.showList();
	}
	//获取链表的有效节点个数
	public static int getLength(HeroNode head) {
		if(head.next == null) {
			return 0;
		}
		int length = 0;
		HeroNode temp = head.next;
		while(true) {
			length++;
			if((temp = temp.next) == null) {
				return length;
			}
			
		}
	}
	//获得单链表中的倒数第k个节点
	//倒数第k个就是顺数第length + 1 - k个
	public static HeroNode getk(int k,HeroNode head) {
		if(head.next == null) {
			return null;
		}
		int len = getLength(head);
		if(k > len || k <= 0) {
			return null;
		}
		HeroNode temp = head;
		for (int i = 0; i < len + 1 - k; i++) {
			temp = temp.next;
		}
		return temp;
		
	}
	//单链表的反转
	//
	public static void getListOfReverse(HeroNode head) {
		if(head.next == null || head.next.next == null) {
			return ;
		}
		HeroNode reverseHead = new HeroNode(0, "", "");
		HeroNode cur = head.next;
		HeroNode next = null;
		//每遍历一个原来链表的节点，就添加到reverse的前面
		while(cur != null) {
			next = cur.next;//先保存当前节点的下一个节点
			cur.next = reverseHead.next;//将temp的下一个节点指向新链表的最前端
			reverseHead.next = cur;
			cur = next;
			//记住万事万物皆对象
		}
		head.next = reverseHead.next;
	}

	//逆序打印链表
	public static void reversePrint(HeroNode head) {
		if(head.next == null) {
			return;
		}
		
		Stack<HeroNode> stack = new Stack<HeroNode>();
		HeroNode cur = head.next;
		//将链表的节点压入栈中
		while(cur != null) {
			stack.push(cur);
			cur = cur.next;
		}
		//出栈，打印
		while(stack.size() > 0) {
			System.out.println(stack.pop());
		}
	}
	
	public static HeroNode marge(HeroNode head1, HeroNode head2) {
		if(head1.next == null && head2.next == null) {
			return null;
		}
		if(head1.next == null) {
			return head2;
		}
		if(head2.next == null) {
			return head1;
		}
		
		//如果两个都不为空，从一个表中遍历所有节点，向另一个链表有序插入
		//1.获取head1链表中的节点
		
		HeroNode cur2 = head2;
		HeroNode node = head1.next;
		
		boolean flag;
		while(node != null) {
			HeroNode cur1 = new HeroNode();
			cur1.name = node.name;
			cur1.no = node.no;
			cur1.nickName = node.nickName;
			cur1.next = null;
			//获取head2中的节点和cur1进行no的对比
			flag = false;
			while(true) {
				if(cur1.no == cur2.no) {
					break;
				}
				//插入到head2的最后
				if(cur2.next == null) {
					cur2.next =cur1;
					break;
					
				}
				//cur1将会插入到cur2的后面
				if(cur1.no < cur2.next.no) {
					flag = true;
					break;
				}	
				
				
				
				cur2 = cur2.next;
			}
			//向head2中插入cur1
			if(flag) {
				cur1.next = cur2.next;
				cur2.next = cur1;
			}
			node = node.next;
			cur2 = head2;
		}
		return head2;
	}
	
	
}
class  SingleLinkedListDemo {
	
	//初始化一个头节点
	private  HeroNode head = new HeroNode(0,"","");
	
	
	//添加节点到单项列表
	/*
	 * 思路，当不考虑编号顺序时
	 * 1.找到当前节点链表的最后一个节点
	 * 2.将最后这个节点的next指向新的节点
	 */
	/**
	 * 
	 * @description 在链表最后添加节点
	 * @author xiaohao
	 * @date 2020年4月21日下午2:54:51
	 * @param heroNode
	 */
	public  void add(HeroNode heroNode) {
		
		HeroNode temp = head;
		//遍历链表，找到链表的最后
		while(true) {
			if (temp.next == null) {
				break;
			}
			//temp后移
			 temp = temp.next;
		}
		//添加节点
		temp.next = heroNode;
	}

	public HeroNode getHead() {
		return head;
	}
	/**
	 * 
	 * @description 根据No进行排序地添加节点
	 * @author xiaohao
	 * @date 2020年4月21日下午3:36:26
	 * @param heroNode
	 */
	public void addByNo(HeroNode heroNode) {
		HeroNode temp = head;
		boolean flag = false;
		while(true) {
			if(temp.next == null) {
				break;
			}
			//要插入的位置就在temp后面
			if(temp.next.no > heroNode.no) {
				break;
			} else if (temp.next.no == heroNode.no){
				flag = true;
				break;
			}
			temp = temp.next;
		}
		if(flag) {
			System.out.println(heroNode + "\t该英雄已经存在链表中");
		} else {
			//把表连起来，heroNode就在temp的后面
			heroNode.next = temp.next;
			temp.next = heroNode;
		}
	}
	/*
	 * 从单链表中删除一个节点的思路
	 * 1.我们先找到需要删除的这个节点的前一个节点temp
	 * 2.temp.next = temp.next.next
	 * 3.被删除的节点，将不会有其他引用指向，会被垃圾回收机制回收
	 */
	public void delete(int no) {
		HeroNode temp = head;
		boolean flag = false;
		while(true) {
			if (temp.next == null) {
				break;
			}
			if(temp.next.no == no) {
				flag = true;
				break;
			}
			temp = temp.next;
		}
		if(flag) {
			temp.next = temp.next.next;
		}else {
			System.out.println("链表中没有该英雄的编号");
		}
		
	}
	//显示链表[遍历]
	public void showList() {
		if(head.next == null) {
			System.out.println("链表为空");
			return;
		}
		
		HeroNode temp = head.next;
		while (true) {
			System.out.println(temp);
			if((temp = temp.next) == null) {
				break;
			}
		}
		
	}
	public void update(HeroNode newHeroNode) {
		//判断链表是否为空
		if (head.next == null) {
			System.out.println("链表为空");
			return;
		}
		HeroNode temp = head.next;
		boolean flag = false;
		while(true) {
			if(temp == null) {
				break;
			}
			if(temp.no == newHeroNode.no) {
				flag = true;
				break;
			}
			temp = temp.next;
		}
		if(flag) {
			temp.name = newHeroNode.name;
			temp.nickName = newHeroNode.nickName;
		} else {
			System.out.println("链表中没有该英雄信息无法修改");
		}
	}
//	//获取到单链表的节点个数(如果带头节点，需要去除头节点)
//	public int getLength() {
//		if(head.next == null) {
//			return 0;
//		}
//		int length = 0;
//		HeroNode temp = head.next;
//		while(true) {
//			length++;
//			if((temp = temp.next) == null) {
//				return length;
//			}
//			
//		}
//	}
}
class HeroNode {
	public int no;//编号
	public String name;
	public String nickName;
	public HeroNode next;

	
	public HeroNode() {
		super();
	}
	public HeroNode(int no, String name, String nickName) {
		this.no = no;
		this.name = name;
		this.nickName = nickName;
	}
	@Override
	public String toString() {
		return "HeroNode [no=" + no + ", name=" + name + ", nickName=" + nickName + "]";
	}
	
}
