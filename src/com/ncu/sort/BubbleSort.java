package com.ncu.sort;



/*
 * 冒泡排序算法的思路
 * 	  1.一共要 进行args.length - 1次排序,因为每一次进
 * 行排序都是把未排序的数据中最大的往后挪，最后一个要排
 * 序的数据其实就是第一个
 *    2.在每一次相邻的元素的比较中，如果逆序就交换
 *
 */
public class BubbleSort {

	public static void main(String[] args) {
//		
		//创建一个int[80000]
		int[] arr = new int[80000];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int)(Math.random() * 80000000);
		}
		
		long start = System.currentTimeMillis();
		sort(arr);
		long end = System.currentTimeMillis();
		System.out.println(end - start);//9998ms
		
	}
	
	public static void sort(int[] arr) {
		//临时变量
		int temp = 0;
		//标识符
		boolean flag = false;
		//第一趟排序
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = 0; j < arr.length - 1 - i; j++) {
				//如果前面的数比后面的大,交换顺序
				if (arr[j] > arr[j + 1]) {
					flag = true;
					temp = arr[j + 1];
					arr[j + 1] = arr[j];
					arr[j] = temp;
				}
			}
			//对程序进行优化，如果在一趟排序过程中没有进行交换，那么说明排序已经完成了
			
			if (!flag) {
				break;
			} else {
				flag = false;
			}
		}

	}
	public static void print(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
	}
}
