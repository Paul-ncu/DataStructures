package com.ncu.sparse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class SparseArray {

	public static void main(String[] args) {
		//创建一个原始的二维数组11*11
		//0表示没有棋子，1表示黑子，2表示蓝子
		int[][] chessArr1= new int[11][11];
		chessArr1[1][2] = 1;
		chessArr1[2][3] = 2;
		int row = 0;
		int column = 0;
		int rowxcol = 0;
		//遍历原始数组
		System.out.println("原始数组：");
		for (int[] is : chessArr1) {
			for (int date : is) {
				System.out.print(date + " ");
			}
			System.out.println();
		}
		//得到原始数组的行和列
		for (int[] rows : chessArr1) {
			for ( int i: rows) {
				rowxcol++;
			}
			column++;
		}
		row = rowxcol / column;
		
		//System.out.println("row = " + row + "column = " +column);
		//将二维数组 转 稀疏数组
		//1.遍历数组的到非零数据的个数
		int sum = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				if(chessArr1[i][j] != 0) {
					sum++;
					
				}
			} 
		}
		//System.out.println(sum);
		//2.创建稀疏数组
		int[][] sparseArr = new int[sum + 1][3];
		//数组第一行的值
		sparseArr[0][0] = row;
		sparseArr[0][1] = column;
		sparseArr[0][2] = sum;
		int count = 0;//记录每一个非零数据在第几个位置
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				if(chessArr1[i][j] != 0) {
					count++;
					sparseArr[count][0] = i;
					sparseArr[count][1] = j;
					sparseArr[count][2] = chessArr1[i][j];
				}
			} 
		}
		//遍历出稀疏数组
		System.out.println();
		System.out.println("稀疏数组");
		for (int[] is : sparseArr) {
			for(int date : is) {
				System.out.print(date + "\t");
			}
			System.out.println();
		}
		//将稀疏数组写入文档
		FileWriter out = null;
		try {
			File file = new File("sparseArray.data");
			out = new FileWriter(file);
			for (int[] is : sparseArr) {
				for (int i : is) {
					out.write(i + "\t");
				}
				out.write("\r\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(out != null)
					out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
		//将稀疏数组从文档中读出
		int[][] sparseArr1 = new int[sum + 1][3];
		BufferedReader in = null;
		try {
			//1.造流
			in = new BufferedReader(new FileReader("sparseArray.data"));
			//2.对流进行操作
			String line;
			int r = 0;
			while ((line = in.readLine()) != null) {
				String[] temp = line.split("\t");
				for (int i = 0; i < temp.length; i++) {
					sparseArr1[r][i] = Integer.parseInt(temp[i]);
				}
				r++;
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(in != null)
					in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println();
		//遍历从文档中读出的稀疏数组
		System.out.println("从文档中读出来的稀疏数组");
		for (int[] is : sparseArr1) {
			for (int date : is) {
				System.out.print(date + "\t");
			}
			System.out.println();
		}
		
		//将稀疏数组 转为 二维数组
		/*
		 * 1.先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组
		 * 2.在读取稀疏数组后几行的数据，并赋值给原始的二维数组
		 */
		
		//1.创建二维数组
		int[][] chessArr2 = new int[sparseArr1[0][0]][sparseArr1[0][1]];
		
		
		//将稀疏数组中的值赋到原始二维数组中
		for (int i = 1; i < sum + 1; i++) {
			chessArr2[sparseArr1[i][0]][sparseArr1[i][1]] = sparseArr1[i][2];
		}
		//遍历恢复后的二维数组
		System.out.println("恢复后的二维数组");
		for (int[] is : chessArr2) {
			for (int is2 : is) {
				System.out.print(is2 + " ");
			}
			System.out.println();
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
