package com.ncu.test;

import java.util.PriorityQueue;

public class Test {

	public static void main(String[] args) {
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		queue.add(1);
		queue.add(5);
		queue.add(3);
		queue.add(4);
		queue.add(-1);
		for (Integer integer : queue) {
			System.out.println(integer);
		}
	}
}
