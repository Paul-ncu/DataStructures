package com.ncu.search;

import java.util.ArrayList;
import java.util.List;


public class LinearSearch {

	public static void main(String[] args) {
		int[] arr = new int[800000];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int)(Math.random() * 800);
		}
		//ShellSort.sort(arr);
		long start = System.currentTimeMillis();
		List<Integer> list = search(arr, 434);
		long end = System.currentTimeMillis();
		System.out.println(end - start);
				
		
	}
	public static <E> List<Integer> search(int[] arr, int value) {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < arr.length; i++) {
			if(arr[i] == value) {
				list.add(i + 1);
			}
		}
		return list;
	}
}
