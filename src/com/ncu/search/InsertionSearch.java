package com.ncu.search;

import java.util.ArrayList;
import java.util.List;

import com.ncu.sort.MergeSort;

/*
 * 使用插值查找要注意数组中的数据要分布均匀
 * 否则可能会导致mid再数组中越界
 */
public class InsertionSearch {

	public static void main(String[] args) {
//		int[] arr = {1,2,2,3,4,5,6,7,8,8,9};
//		List<Integer> list = search(arr, 0, arr.length - 1, 1);
//		System.out.println(list);
		
		int[] arr = new int[8000000];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int)(Math.random() * 800000000);
		}
		int[] temp = new int[arr.length];
		MergeSort.sort(arr, 0, arr.length - 1, temp);
		long start = System.currentTimeMillis();
		search(arr, 0, arr.length - 1, 434);
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}
	
	public static <E> List<Integer> search(int[] arr, int left, int right, int findVal) {
		//防止mid越界了
		if(left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
			return new ArrayList<Integer>();
		}
		int mid = left + (findVal - arr[left]) * (right - left) / (arr[right] - arr[left]);
		int midVal = arr[mid];
		
		if(findVal > midVal) {
			return search(arr, mid + 1, right, findVal);
		} else if(findVal < midVal){
			return search(arr, left, mid - 1, findVal);
		} else {
			//定义一个装结果的list
			List<Integer> list = new ArrayList<Integer>();
			//定义一个对mid左右两边扫面的指针
			int temp = mid - 1;
			while(true) {
				if (temp < 0 || arr[temp] != findVal) {
					break;
				}
				list.add(temp);
				temp -= 1;
			}
			list.add(mid);
			temp = mid + 1;
			while(true) {
				if (temp > arr.length - 1 || arr[temp] != findVal) {
					break;
				}
				list.add(temp);
				temp += 1;
			}
			return list;
//			return mid;
		}
	}
}
