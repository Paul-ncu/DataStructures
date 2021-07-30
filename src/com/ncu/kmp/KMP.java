package com.ncu.kmp;

public class KMP {
	private int[][] dp;
	private String pattern;
	
	public KMP(String pattern) {
		this.pattern = pattern;
		int length = pattern.length();
		dp = new int[length][256];
		
		dp[0][pattern.charAt(0)] = 1;
		
		int x = 0;
		for (int j = 1; j < length; j++) {
			for (int c = 0; c < 256; c++) {
				if (c == pattern.charAt(j)) {
					dp[j][c] = j + 1;
				} else {
					dp[j][c] = dp[x][c];
				}
			}
			x = dp[x][pattern.charAt(j)];
		}
	}
	
	public int search(String target) {
		// 初始状态
		int state = 0;
		int length = pattern.length();
		
		for (int i = 0; i < target.length(); i++) {
			state = dp[state][target.charAt(i)];
			if (state == length) return i - length + 1;
		}
		return -1;
	}
}
