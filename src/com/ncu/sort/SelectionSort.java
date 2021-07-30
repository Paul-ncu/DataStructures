package com.ncu.sort;

/*
 * 选择排序
 * 1.每一次循环过程中，在未排序的数据中找出最小值，
 * 分别和数组的arr[0],arr[1]...进行交换。
 */

public abstract class SelectionSort {

	public static void main(String[] args) {
		int[] arr = new int[80000];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int)(Math.random() * 80000000);
		}
		
		long start = System.currentTimeMillis();
		sort(arr);
		long end = System.currentTimeMillis();
		System.out.println(end - start);//1015ms
//		int[] arr = {1,-4,5,2,10,1};
//		sort(arr);
//		print(arr);
	}
	
	//写一个选择排序的方法
	public static void sort(int[] arr) {
		//用于记录最小值的下标
		int minIndex = 0;
		
		
		for (int j = 0; j < arr.length - 1; j++) {
			//假设一个最小值
			int min = arr[j];
			//循环找到最小的数据
			for (int i = j + 1; i < arr.length; i++) {
				
				if (arr[i] <= min) {
					min = arr[i];//更新每次比较和更新min
					minIndex = i;//更最小值的下标
				}
			}
			//交换min 和 arr[minIndex]
			if(min != arr[j]) {
				arr[minIndex] = arr[j];
				arr[j] = min;
			}
		}
		
	}
	
	public static void print(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
	}
}
