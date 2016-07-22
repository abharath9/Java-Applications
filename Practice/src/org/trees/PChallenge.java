package org.trees;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeMap;

public class PChallenge {

	public void Operation(String path) {
		TreeMap<Integer, String> tm = new TreeMap<Integer, String>();
		TreeMap<String, String> n = new TreeMap<String, String>();
		// String[] reader;
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line;
			while ((line = br.readLine()) != null) {
				String[] reader = line.split(":");

				/*
				 * for (int i = 0; i < reader.length; i++) {
				 * System.out.print(reader[i]); }
				 */

				if (n.containsKey(reader[1])) {

					String[] tmp = n.get(reader[1]).split(",");
					if (Integer.parseInt(tmp[0]) > Integer.parseInt(reader[0])) {
						n.put(reader[1], reader[0] + "," + reader[2]);
					}
				} else {
					n.put(reader[1], reader[0] + "," + reader[2]);
				}

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (String s : n.keySet()) {
			System.out.println(s + ": " + n.get(s).split(",")[1]);
		}

	}

	public static void main(String args[]) {
		PChallenge pc = new PChallenge();
		Scanner sn = new Scanner(System.in);
		System.out.println("Please enter the Location of the File");
		String loc = sn.nextLine();
		pc.Operation(loc);
		System.out.println("abc".contains("ab"));

	}
}