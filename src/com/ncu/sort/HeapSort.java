package com.ncu.sort;

import java.util.Arrays;

/*
 * 堆排序的基本思路
 * 1) 将待排序序列构成一个大顶堆
 * 2) 此时，整个序列的最大值就是堆顶的根节点
 * 3) 将其与末尾元素进行交换，此时末尾就是最大值
 * 4) 然后将剩余的n-1个元素重新构成一个堆，这样会得到n个元素的
 *    次小值，如此反复执行，使能得到一个有序序列了
 * 
 */

public class HeapSort {

	public static void main(String[] args) {

		
		int[] arr = {1, -2, 33, 1, 100, 34, 2};
		
		heapSort(arr);
		System.out.println(Arrays.toString(arr));

	}

	// 编写一个堆排序的方法
	public static void heapSort(int arr[]) {
		// 用于堆顶和堆低交换的临时变量
		int temp = 0;
		// 将待排序序列构成一个大顶堆
		for (int i = arr.length / 2 - 1; i >= 0; i--) {
			adjustToHeap(arr, i, arr.length);
		}

		// 将其与末尾元素进行交换，此时末尾就是最大值
		for (int i = arr.length - 1; i > 0; i--) {
			temp = arr[i];
			arr[i] = arr[0];
			arr[0] = temp;
			adjustToHeap(arr, 0, i);
		}

	}

	// 将一个数组(二叉树)，调整成一个大顶堆。
	/**
	 * 
	 * @description 对 i 节点进行一个局部的大顶堆调整
	 * @author xiaohao
	 * @date 2020年5月26日下午3:29:14
	 * @param arr    待调整的数组
	 * @param i      表示非叶子节点在数组中的索引
	 * @param length 表示对多少个元素继续调整， length 是在逐渐的减少
	 */
	public static void adjustToHeap(int arr[], int i, int length) {

		// 先去除当前元素的值，保存在临时变量
		int temp = arr[i];

		// 开始调整
		// k = i * 2 + 1 是 i 结点的左子节点
		for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
			// 说明左子节点小于右子节点
			if (k + 1 < length && arr[k] < arr[k + 1]) {
				k++; // k指向右子节点
			}
			// 如果子节点大于父节点
			if (arr[k] > temp) {
				// 让较大的子节点赋值给父节点
				arr[i] = arr[k];
				// i 指向 k，继续循环比较
				i = k;
			} else {
				break;
			}
		}
		// 此时i的位置是以i为父节点的树中，最大值的那个位置
		arr[i] = temp;
	}

}
