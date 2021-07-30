package com.ncu.linkedlist;

public class DoubleLinkedList {
	public static void main(String[] args) {
		HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
		HeroNode2 hero2 = new HeroNode2(2, "吴用", "智多星");
		HeroNode2 hero3 = new HeroNode2(3, "卢俊义", "玉麒麟");
		HeroNode2 hero4 = new HeroNode2(4, "公孙胜", "入云龙");
		HeroNode2 hero5 = new HeroNode2(5, "林冲", "豹子头");
		HeroNode2 hero6 = new HeroNode2(6, "李逵", "黑旋风");
		HeroNode2 hero7 = new HeroNode2(7, "张顺", "浪里白条");
		HeroNode2 hero8 = new HeroNode2(8, "秦明", "霹雳火");
		HeroNode2 hero9 = new HeroNode2(9, "王英", "矮脚虎");
		
		
		DoubleLinkedListDmeo list = new DoubleLinkedListDmeo();
		
		list.addById(hero4);
		list.addById(hero5);
		list.addById(hero3);
		list.addById(hero1);
		list.addById(hero8);
		list.addById(hero2);
		list.addById(hero7);
		
		//list.update(new HeroNode2(7, "朱仝" , "美鬓公"));
		list.showList();
	}

}
class DoubleLinkedListDmeo{
	private HeroNode2 head = new HeroNode2(0, "" , "");
	
	public void addById(HeroNode2 heroNode) {
		HeroNode2 temp = head;
		boolean flag = false;
		while (true) {
			if (temp.next == null) {
				break;
			}
			// 要插入的位置就在temp后面
			if (temp.next.no > heroNode.no) {
				break;
			} else if (temp.next.no == heroNode.no) {
				flag = true;
				break;
			}
			temp = temp.next;
		}
		if (flag) {
			System.out.println(heroNode + "\t该英雄已经存在链表中");
		} else {
			// 把表连起来，heroNode就在temp的后面
			if (temp.next != null) {
				heroNode.next = temp.next;
				temp.next.pre = heroNode;
			}
			temp.next = heroNode;
			heroNode.pre = temp;
			

		}
	}
	
	/**
	 * 
	 * @description 根据编号删除节点
	 * @author xiaohao
	 * @date 2020年4月22日上午9:48:13
	 * @param no
	 */
	public void delete(int no) {
		if(head.next == null) {
			System.out.println("链表为空");
			return;
					
		}
		HeroNode2 temp = head.next;
		boolean flag = false;
		while(true) {
			if (temp == null) {
				break;
			}
			if(temp.no == no) {
				flag = true;
				break;
			}
			temp = temp.next;//节点后移
		}
		if(flag) {
			temp.pre.next = temp.next;
			if(temp.next != null)
				temp.next.pre = temp.pre;
			
		}else {
			System.out.println("链表中没有该英雄的编号");
		}
		
	}
	/**
	 * 
	 * @description 双向链表的修改，和单项链表方法一样
	 * @author xiaohao
	 * @date 2020年4月22日上午9:30:51
	 * @param newHeroNode
	 */
	public void update(HeroNode2 newHeroNode) {
		//判断链表是否为空
		if (head.next == null) {
			System.out.println("链表为空");
			return;
		}
		HeroNode2 temp = head.next;
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
	
	/**
	 * 
	 * @description 在链表尾部添加节点
	 * @author xiaohao
	 * @date 2020年4月22日上午9:29:31
	 * @param heroNode
	 */
	public void add(HeroNode2 heroNode) {

		HeroNode2 temp = head;
		// 遍历链表，找到链表的最后
		while (true) {
			if (temp.next == null) {
				break;
			}
			// temp后移
			temp = temp.next;
		}
		// 添加节点
		temp.next = heroNode;
		heroNode.pre = temp;
		
	}
	
	
	
	/**
	 * 
	 * @description 双向链表的遍历
	 * @author xiaohao
	 * @date 2020年4月22日上午9:26:16
	 */
	public void showList() {
		if(head.next == null) {
			System.out.println("链表为空");
			return;
		}
		
		HeroNode2 temp = head.next;
		while (true) {
			System.out.println(temp);
			if((temp = temp.next) == null) {
				break;
			}
		}
		
	}
	
}
class HeroNode2 {
	public int no;//编号
	public String name;
	public String nickName;
	public HeroNode2 next;//指向后一个节点
	public HeroNode2 pre;//指向前一个节点
	

	
	public HeroNode2() {
		super();
	}
	public HeroNode2(int no, String name, String nickName) {
		this.no = no;
		this.name = name;
		this.nickName = nickName;
	}
	@Override
	public String toString() {
		return "HeroNode [no=" + no + ", name=" + name + ", nickName=" + nickName + "]";
	}
	
}