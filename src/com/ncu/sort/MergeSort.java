package com.ncu.sort;

public class MergeSort {

	public static void main(String[] args) {

		int[] arr = new int[8000000];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int)(Math.random() * 80000000);
		}
		int[] temp = new int[arr.length];
		long start = System.currentTimeMillis();
		sort(arr, 0, arr.length -1,temp);
		long end = System.currentTimeMillis();
		System.out.println(end - start);//800000 177ms
		
//		int[] arr = {6,1,2,7,9,3,4,5,10,8};
//		sort(arr,0,9);
//		print(arr);
	}
	
	public static void sort(int[] arr, int start, int end, int[] temp) {
		if (start < end) {
			int mid = (start + end) / 2;
			//从左边开始递归找到最小的分组
			sort(arr, start, mid, temp);
			sort(arr, mid + 1, end, temp);
			merge(arr, start, mid, end,temp);
		}
	}
	
	public static void merge(int[] arr, int start, int mid, int end, int[] temp) {
		//设置两个指针，方便对start -> mid 和 mid+1 -> end进行遍历
		int i = start;
		int j = mid + 1;
		//设置一个用于遍历temp数组的指针
		int t = 0;
		//进行循环遍历
		while (i <= mid && j <= end ) {
			if (arr[i] <= arr[j]) {
				temp[t++] = arr[i++];
			} else {
				temp[t++] = arr[j++];
			}
		}
		//如果退出循环，说明有一段的数据已经遍历完
		//如果是start -> mid遍历完
		while (j <= end) {
			temp[t++] = arr[j++];
		}
		//如果是mid+1 -> end遍历完
		while (i <= mid) {
			temp[t++] = arr[i++];
		}
		
		//排序完之后，把temp的值复制到arr
		t = 0;
		//从分组后的每一组的开头给start开始复制，要记住递归是一种思想，需要抽象的思考逻辑
		int tempStart = start;
		while(tempStart <= end) {
			arr[tempStart++] = temp[t++];
		}
		
	}

	public static void print(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
	}
}
