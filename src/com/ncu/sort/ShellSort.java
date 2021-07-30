package com.ncu.sort;
/*
 * 希尔排序是对插入排序算法的一种改进，是将需要排序的数据分组进行排序
 * 基本原理还是用到了插入排序
 */
public class ShellSort {

	public static void main(String[] args) {
		int[] arr = new int[8000000];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int)(Math.random() * 80000000);
		}
		
		long start = System.currentTimeMillis();
		sort(arr);
		long end = System.currentTimeMillis();
		System.out.println(end - start);//19ms
		
//		int[] arr = {8,9,1,7,2,3,5,4,6,0};
//		sort(arr);
//		print(arr);
		
	}
	
	public static void sort(int[] arr) {
		//设定一个分组的间隔
		int gap = arr.length;
		//设置一共要插入的临时变量
		int temp;
		//设置一个待插入的位置
		int index;
		while(true) {
			//每次分组的增量除以2
			gap /= 2;
			//对每个组进行插入排序
			for (int i = 0; i < gap; i++) {
				/*
				 * 接下来就是一个插入排序，从每一组的第二个数据开始进行插入
				 * 除了每组数据的头，从第gap个数据开始插入，arr[gap]就是第一组的第一个
				 * 要注意是每一组进行排序，所以temp在接下来的一个循环中都是一个组中的数据
				 * 就要主要每次循环的增量就是gap
				 */
				for (int j = i + gap; j < arr.length; j += gap) {
					//为临时变量赋值
					temp = arr[j];
					/*
					 * 为待插入位置赋值,就是在插入排序算法中，先拿要插入的值和
					 * 和已经排好序的队伍的最后一个进行比较，如果小于这个值，那么
					 * 这个值以及这个值后面位置的都要向后移动一位
					 */
					
					index = j - gap;
					//寻找要插入的那个位置
					while (index >= 0 && arr[index] > temp) {
						//从arr[index]开始一直向后移动，移动时要注意分组
						//所以后一个位置是index + gap；
						arr[index + gap] = arr[index];
						index -= gap;
					}
					//已经待插入的位置了，是 index + gap；
					//如果待插入的数据大于
					arr[index + gap] = temp;
					
				}
			}
			if (gap == 1) {
				break;
			}
		}
	}
	
	public static void print(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
	}
}
