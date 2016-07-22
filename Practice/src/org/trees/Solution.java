package org.trees;

import java.util.ArrayList;
import java.util.Arrays;

public class Solution {

	public int solution(int X) {
		// write your code in Java SE 8
		String s = X + "";
		ArrayList<Integer> al = new ArrayList<Integer>();
		char[] ch = s.toCharArray();

		for (int i = 0; i < ch.length - 1; i++) {
			char[] temp = new char[ch.length - 1];
			if (ch[i] > ch[i + 1]) {
				for (int j = 0; j < ch.length - 1; j++) {
					if (i == j) {
						temp[j] = ch[j];
						// j++;
					} else {
						temp[j] = ch[j];
					}
				}

			} else {
				for (int j = 0; j < ch.length - 1; j++) {

					if (i == j) {
						temp[j] = ch[j + 1];

						// j++;
					} else {
						if (j == temp.length - 1) {
							temp[j] = ch[j + 1];
						} else {
							temp[j] = ch[j];
						}

					}
				}
			}
			String st = Arrays.toString(temp).replace(",", "").replace(" ", "").replace("[", "").replace("]", "");
			System.out.println(st);
			al.add(Integer.parseInt(st));
		}
		int res = 0;
		for (Integer in : al) {
			if (res < in) {
				res = in;
			}
		}
		return res;

	}

	public static void main(String[] args) {
		Solution sol = new Solution();
		System.out.println(sol.solution(123));
	}
}
