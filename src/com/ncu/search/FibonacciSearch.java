package com.ncu.search;

import java.util.Arrays;

public class FibonacciSearch {

	public static void main(String[] args) {

		int[] arr = {1,2,2,3,4,5,6,7,8,8,9,10};
		System.out.println(search(arr, 10));
		
	}

	//斐波那契数列构造方法
	public static int[] fibonacci(int size) {
		int[] f = new int[size];
		f[0] = 1;
		f[1] = 1;
		for (int i = 2; i < size; i++) {
			f[i] = f[i - 1] + f[i - 2];
		}
		return f;
	}
	
	//斐波那契查找算法
	public static int search(int[] arr, int findVal) {
		int low = 0;
		int high = arr.length - 1;
		int k = 0;//表示斐波那契数列的下标
		int mid = 0;
		int[] f = fibonacci(100);
		//获得当arr的长度小于对应的斐波那契数列的下标值
		//目的就是让arr满足可以构成一个斐波那契数列的长度
		while(high > f[k] - 1) {
			k++;
		}
		//要使得的arr的长度等于f[k],构成一个可以满足斐波那契数列的数组
		int[] temp = Arrays.copyOf(arr, f[k]);
		for (int i = high + 1; i < f[k]; i++) {
			temp[i] = arr[high];
		}
		
		while(low <= high) {
			mid = low + f[k - 1] - 1;
			if(findVal < temp[mid]) {
				high = mid - 1;
				k--;
			} else if(findVal > temp[mid]) {
				low = mid + 1;
				k -= 2;
			} else {
				if(mid <= high) {
					return mid;
				} else {
					return high;
				}
			}
		}
		return -1;
		
	}
}
