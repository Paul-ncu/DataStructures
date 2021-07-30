package com.ncu.sort;

/*
 * 快速排序算法的思路分析
 * 1.先确定一个基准数字
 * 2.用两个指针分别从左边和右边开始遍历
 * 3.左边的指针要找到比基准大的数字，右边的指针要找到比基准数字的小地数字
 * 4.再找到这些数字后，交换他们的位置
 * 5.使基准数据左边的数要小于它，右边的数据要大于它
 * 6.最后确定好基准数据的位置
 * 7.终止递归的的条件要根据这两个指针的位置来判断
 * 
 * 快速排序在数据量较大的时候效率很高
 * 
 */
public class QuicklySort {

	public static void main(String[] args) {
		int[] arr = {2,0,2,1,1,0};
		
		long start = System.currentTimeMillis();
		sort(arr, 0, arr.length -1);
		long end = System.currentTimeMillis();
		System.out.println(end - start);//62ms
		
//		int[] arr = {6,1,2,7,11,9,3,4,5,10,8};
//		sort(arr, 0, arr.length -1);
//		print(arr);
	}
	
	public static void sort(int[] arr, int left, int right) {
		
		
		//定义两个指针，分别从左和右开遍历
		int i = left;
		int j = right;
		
		if (left > right) {
			return;
		}
		//取每一个arr的第一个作为基准数字
		int flag = arr[left];
		//设置一个用于替换的临时变量
		int temp;
		while(i < j) {
			
			//从右到左找到小于flag的数字，下标为right
			while (arr[j] >= flag && i < j) {
				j--;
			}
			//从左到右找到大于flag的数字，下标为left + 1
			while(arr[i] <= flag && i < j) {
				i++;
			}
			//交换这些找到的值
			if (i < j) {
				temp = arr[j];
				arr[j] = arr[i];
				arr[i] = temp;
			}
			
		}
		//再将基准移位
		arr[left] = arr[i];
		arr[i] = flag;
		//再对左边的递归
		sort(arr, left, j -1);
		//再对右边递归
		sort(arr, j + 1, right);
		
	}
	public static void print(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
	}
}
