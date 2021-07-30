package com.ncu.recursion;

public class TheEightQueens {

	public static void main(String[] args) {
	TheEightQueens queens = new TheEightQueens();
	queens.check(0);

	}
	//定义一个max表示有多少个皇后
	int max = 8;
	//定义一个数组表示摆放后的结果
	int[] result = new int[max];
	//打印结果的方法
	int count = 0;//解法的个数
	public void print() {
		count++;
		for (int i = 0; i < result.length; i++) {
			System.out.print(result[i] + " ");
		}
		System.out.println("解法：" + count);
	}
	
	//当我们摆放完第n个皇后，判断第n+1个皇后与之前的那些皇后是否发送冲突
	public boolean judge(int n) {
		for (int i = 0; i < n; i++) {
			if (result[i] == result[n] || Math.abs(n - i) == Math.abs(result[n] - result[i])) {
				return false;
			}
		}
		return true;
	}
	//放置第n给皇后
	public void check(int n) {
		if(n == max) {
			print();
			return;
		}
		
		//依次放入皇后，并判断是否冲突
		for (int i = 0; i < max; i++) {
			//先把当前这个皇后n放在第一列
			result[n] = i;
			if (judge(n)) {//如果下一个皇后摆上去之后不冲突
				//接着放后面的皇后
				check(n + 1);
			}
			//如果冲突，就继续执行result[n] = i;
		}
	}
}
