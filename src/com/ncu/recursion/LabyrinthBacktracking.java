package com.ncu.recursion;

public class LabyrinthBacktracking {

	public static void main(String[] args) {
		//先创建一个迷宫
		int[][] map = new int[8][7];
		//使用1表示墙
		//迷宫的最上面和最下面置1
		for (int i = 0; i < 7; i++) {
			map[0][i] = 1;
			map[7][i] = 1;
		}
		//迷宫的最左和最右置1
		for (int i = 0; i < 7; i++) {
			map[i][0] = 1;
			map[i][6] = 1;
		}
		//再设置一些墙
		map[1][2] = 1;
		map[2][2] = 1;
		map[4][2] = 1;
		map[5][2] = 1;
		map[6][2] = 1;
		
		
		System.out.println("地图情况如下：");
		for (int[] row : map) {
			for (int i = 0; i < row.length; i++) {
				System.out.print(row[i] + " ");
			}
			System.out.println();
		}
		boolean isWay = findWay(map, 1, 1);
		if (isWay) {
			System.out.println("找到路线了：");
			for (int[] row : map) {
				for (int i = 0; i < row.length; i++) {
					System.out.print(row[i] + " ");
				}
				System.out.println();
			}
		} else {
			System.out.println("没找到出口");
		}
		
		
	}
	//使用递归来给小球找路
	/*
	 * 思路：
	 * 当map[i][j]为0表示该点没有走过，1表示为墙，
	 * 2表示通路可以走，3表示该点已经走过，但是走不通
	 */
	/**
	 * 
	 * @description 
	 * @author xiaohao
	 * @date 2020年4月24日下午3:39:21
	 * @param map 表示地图
	 * @param i 表示开始的位置的横坐标
	 * @param j 表示开始的位置的纵坐标
	 * @return
	 */
	public static boolean findWay(int[][] map, int i, int j) {
		if(map[6][5] == 2) {//表示已经找到通路了
			return true;
		} else {
			if(map[i][j] == 0) {//表示这个点还没有走过
				map[i][j] = 2;
				if(findWay(map, i + 1, j)) {//向下走
					return true;
				} else if (findWay(map, i, j + 1)){//向右走
					return true;
				} else if (findWay(map, i - 1, j)) {//向上走
					return true;
				} else if (findWay(map, i, j - 1)){//向左走
					return true;
				} else {
					//表示该点走不通
					map[i][j] = 3;
					return false;
				}
			} else {
				return false;
			}
		}
	}
}
