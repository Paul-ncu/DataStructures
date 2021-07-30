package com.ncu.linkedlist;

import java.util.Stack;

public class TestStacj {

	public static void main(String[] args) {
		Stack<String> stack = new Stack<String>();
		stack.add("jack");
		stack.add("mike");
		stack.add("smith");
		while(stack.size() > 0) {
			System.out.println(stack.pop());
		}
	}
}
