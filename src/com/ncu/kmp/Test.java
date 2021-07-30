package com.ncu.kmp;

public class Test {
	public static void main(String[] args) {
		KMP kmp = new KMP("ABC1");
		int search = kmp.search("ASBCABC2");
		System.out.println(search);
	}
}
