package com.ncu.hash;

import java.util.Scanner;

public class HashTable {

	public static void main(String[] args) {
		HashTableDemo hashTableDemo = new HashTableDemo(7);
		
		char key = ' ';
		Scanner scanner = new Scanner(System.in);
		boolean flag = false;
		while(true) {
			System.out.println("add(a)");
			System.out.println("delete(d)");
			System.out.println("find(f)");
			System.out.println("show(s)");
			System.out.println("exit(e)");
			
			
			key = scanner.next().charAt(0);
			switch (key) {
			case 'a':
				System.out.println("输入id：");
				int id = scanner.nextInt();
				System.out.println("输入name：");
				String name = scanner.next();
				Employeed emp = new Employeed(id,name);
				hashTableDemo.addById(emp);
				break;
			case 's':
				System.out.println("雇员信息为：");
				hashTableDemo.showList();
				break;
			case 'd':
				System.out.println("请输入要删除的id");
				int deleteId = scanner.nextInt();
				hashTableDemo.delete(deleteId);
				break;
			case 'e':
				flag = true;
				break;
			case 'f' :
				System.out.println("请输入要查找的id");
				int num = scanner.nextInt();
				hashTableDemo.findEmpById(num);
			default:
				break;
			}
			if(flag == true) {
				break;
			}
		}
		System.out.println("退出程序成功");
		scanner.close();
	}
}

class HashTableDemo{
	private LinkedList[] empList;
	private int size;
	public HashTableDemo(int size) {
		this.size = size;
		empList = new LinkedList[size];
		for (int i = 0; i < size; i++) {
			empList[i] = new LinkedList();
		}
	}
	
	public void add(Employeed emp) {
		//根据员工的id得到该员工应该加入哪一条链表
		int empListNumber = hashFun(emp.getId());
		empList[empListNumber].add(emp);
	}
	//遍历所有链表
	public void showList() {
		for (int i = 0; i < size; i++) {
			System.out.print("第" + (i + 1) + "条链表信息为：");
			empList[i].showList(i);
		}
	}
	//根据id有序的添加
	public void addById(Employeed emp) {
		int num = hashFun(emp.getId());
		empList[num].addById(emp);
	}
	
	//根据id删除雇员
	public void delete(int id) {
		int num = hashFun(id);
		empList[num].delete(id);
	}
	//根据输入的id查找雇员
	public void findEmpById(int id) {
		int number = hashFun(id);
		Employeed emp = empList[number].findEmpById(id);
		if(emp == null) {
			System.out.println("不存在该雇员");
			
		}else {
			System.out.println(emp);
		}
	}

	//散列函数
	public int hashFun(int id) {
		return id % size;
	}
	
}
//单条链表
class LinkedList{
	//头指针
	private Employeed head;
	//假设每id是自增的，每添加一个员工id加1
	public void add(Employeed emp) {
		if(head == null) {
			head = emp;
			return;
		}
		Employeed temp = head;
		while (temp.next != null) {
			temp = temp.next;
		}
		temp.next = emp;
	}
	
	//根据id有序添加
	public void addById(Employeed emp) {
		if(head == null) {
			head = emp;
			System.out.println("添加成功");
			return;
		}
		Employeed temp;//用于交换链表头
		//插再头指针的前面
		if(head.getId() > emp.getId()) {
			temp = head;
			head = emp;
			head.next = temp;
			return;
		}
		
		Employeed cur = head;
		while(true) {
			if(emp.getId() < cur.next.getId()) {
				emp.next = cur.next;
				cur.next = emp;
				System.out.println("添加成功");
				return;
			}
			//已经到达最后一个节点
			if(cur.next == null) {
				cur.next = emp;
				System.out.println("添加成功");
				return;
			}
			cur = cur.next;
		}
	}
	//根据id删除雇员
	public void delete(int id) {
		if(head == null) {
			System.out.println("链表为空");
			return;
		}
		if(head.getId() == id) {
			head = head.next;
			System.out.println("删除成功");
			return;
		}
		Employeed temp = head;
		while(true) {
			if(temp.next.getId() == id) {
				temp.next = temp.next.next;
				System.out.println("删除成功");
				return;
			}
			if(temp.next == null) {
				System.out.println("不存在该雇员");
				return;
			}
			temp = temp.next;
		}
	}
	
	//根据id查找雇员信息
	public Employeed findEmpById(int id) {
		if(head == null) {
			return null;
		}
		Employeed temp = head;
		while (true) {
			if(temp.getId() == id) {
				return temp;
			}
			if(temp.next == null) {
				return null;
			}
			temp = temp.next;
		}
		
	}
	
	//遍历链表的雇员信息
	public void showList(int id) {
		if(head == null) {
			System.out.println("链表为空");
			return;
		}
		Employeed temp = head;
		while(true) {
			System.out.print("=>" + temp);
			if(temp.next == null) {
				break;
			}
			temp = temp.next;
		}
		System.out.println();
	}
}

class Employeed{
	private int id;
	private String name;
	Employeed next;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Employeed() {
		super();
	}

	public Employeed(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() {
		return "Employeed [id=" + id + ", name=" + name + "]";
	}
	
	
}