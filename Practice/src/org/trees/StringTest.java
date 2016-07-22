package org.trees;

import java.util.Scanner;

public class StringTest {
	public static void stringAppend() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter number of string to concatenate");
		int n = sc.nextInt();
		String[] strArray = new String[n];
		int i = 0;
		StringBuilder sb = new StringBuilder();
		while (i < n) {
			strArray[i] = sc.next();
			sb.append(strArray[i]);
			i++;
		}
		System.out.println(sb.toString());
	}

	public static void main(String[] args) {
		// stringAppend();
		String t = "110";
		int tmp = Integer.parseInt(t, 2);
		System.out.println(Integer.parseInt(t, 2) + Integer.parseInt(t, 2));
		String x = "hello";
		String y = new String("hello");

		System.out.println(x == y); // false
		System.out.println(x.equals(y)); // true

	}

}
