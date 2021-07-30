package com.ncu.sort;

/*
 * 
 */
public class InsertionSort {

	public static void main(String[] args) {
		
		int[] arr = new int[80000];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int)(Math.random() * 80000000);
		}
		
		long start = System.currentTimeMillis();
		sort(arr);
		long end = System.currentTimeMillis();
		System.out.println(end - start);//2477ms
		
//		int[] arr = {1,-4,5,2,10,1,7,2,4,5};
//		sort(arr);
//		print(arr);
	}
	
	public static void sort(int[] arr) {
		//待插入数据的前一个位置
		int insertIndex;//
		//记录需要插入位置的值
		int insertVal;
		for (int i = 1; i < arr.length; i++) {
			insertIndex = i - 1;
			insertVal = arr[i];
			//先找到需要插入的前一个位置(insertIndex)，最后得到的insertIndex为我们找到位置的前一个
			while(insertIndex >= 0 && insertVal < arr[insertIndex]) {
				arr[insertIndex + 1] = arr[insertIndex];
				insertIndex--;// insertIndex减一，
			}
			//此处对代码进行了优化，如果arr[i]本来就是最大的，就不用插入了
			if (insertIndex + 1 != i) {
				//退出循环的时候insertIndex找到，为insertIndex + 1;
				arr[insertIndex + 1] = insertVal;
				
			}
			
		}
	


	}
	
	public static void print(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
	}
}
