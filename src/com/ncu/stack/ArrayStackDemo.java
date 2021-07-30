package com.ncu.stack;

public class ArrayStackDemo {
	public static void main(String[] args) {
		ArrayStack stack = new ArrayStack(10);
		stack.push(4);
		stack.push(5);
		stack.push(2);
		stack.push(7);
		stack.show();
		
	}

}

class ArrayStack{
	private int maxSize;
	private int[] stack;
	private int top = -1;//表示栈底
	
	public ArrayStack(int maxSize) {
		this.maxSize = maxSize;
		stack =new int[this.maxSize];
	}
	
	public boolean isFull() {
		return top == maxSize - 1;
	}
	
	public boolean isEmpty() {
		return top == -1;
	}
	
	//入栈push
	public void push(int value) {
		if(isFull()) {
			System.out.println("栈满");
			return;
		}
		top++;
		stack[top] = value;
	}
	//出栈
	public int pop() {
		if(isEmpty()) {
			throw new RuntimeException("栈空");
		}
		int value = stack[top];
		top--;
		return value;
	}
	
	//遍历栈
	public void show() {
		if(isEmpty()) {
			System.out.println("栈空");
			return;
		}
		for (int i = top; i > -1 ; i--) {
			System.out.println("stack[" + i + "] = " + stack[i]);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}