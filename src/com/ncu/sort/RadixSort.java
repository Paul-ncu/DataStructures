package com.ncu.sort;

/*
 * 基数排序算法
 * 1.速度快
 * 2.时间换空间的方式，当数据量较大时，会造成内存不足
 * 3.是一种稳定的排序：稳定性，如果两个值相等，在排序的过程中，如果两个值的位置依然不发生改变，那么就是稳定的排序
 * 4.负数的时候，要对代码进行修改
 */
public class RadixSort {
	
	public static void main(String[] args) {
		int[] arr = new int[8000000];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int)(Math.random() * 80000000);
		}
		
		long start = System.currentTimeMillis();
		sort(arr);
		long end = System.currentTimeMillis();
		System.out.println(end - start);//31ms
		
//		int[] arr = {6,1,2,7,10,55555,9,3,12,4,0,111,5,8};
//		sort(arr);
//		print(arr);
	}
	
	public static void sort(int[] arr) {
		
		//定义一个二维数组,表示10个桶，每个桶就是一个一维数组
		//为了防止在放入数据的是时候，角标越界，每一个桶的容量为arr.lenght
		//使用空间换时间的算法
		int[][] bucket = new int[10][arr.length];
		//定义索引桶中数据的指针的一个数组
		int[] pointers = new int[10];
		//定义一个temp，用于表示每次比较不同为上的数
		int temp;
		//定义一个arr的指针用于从桶中将数据放入数组
		int index = 0;
		//定义一个桶里面的指针，用于把数据放入数组arr中
		int bucketIndex = 0;
		//先找到最大值，就能确定要几次循环
		int max = arr[0];
		for (int k = 1; k < arr.length; k++) {
			if(arr[k] >= max) {
				max = arr[k];
			}
		}
		String maxStr = "" + max;
		//计算出最大数字有多少位，就有多少次循环次数
		int count = maxStr.length();
		//num是用来计算指定位上数字的辅助值
		int num = 1;
		for (int m = 0; m < count; m++) {
			//将数组的的数据按要求放入桶中
			for (int j = 0; j < arr.length; j++) {
				//temp是找到每次要比较位上的数字
				temp = arr[j] / num % 10;
				//每一个数据都放入要比较的temp位的桶里面
				bucket[temp][pointers[temp]] = arr[j];
				//第temp个桶的指针下移
				pointers[temp]++;
				
			}
			//每一次把所有数据放入桶后，都需要将num*10来计算出下一位比较的数字
			num = num * 10;
			//所有数据已经放入桶中，接下来要把桶中数据取出来
			for (int i = 0; i < 10; i++) {
				while (bucketIndex < pointers[i]) {
					arr[index++] = bucket[i][bucketIndex++];
				}
				//取完后要对一下两个数清零，对下次循环进行重置
				bucketIndex = 0;
				pointers[i] = 0;
			}
			//arr[]的索引重新置零，为下一次从桶中取出数据做准备
			index =0;
		}
		
	}
	public static void print(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
	}
	
}
