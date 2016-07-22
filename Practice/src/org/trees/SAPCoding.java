package org.trees;

import java.util.Calendar;

public class SAPCoding {
	public static final SAPCoding INSTANCE = new SAPCoding();
	private final int year;
	static int CURRENT_YEAR = Calendar.getInstance().get(Calendar.YEAR);

	private SAPCoding() {
		year = CURRENT_YEAR;

	}

	public int getYear() {
		return year;
	}

	public static void main(String[] args) {
		// SAPCoding s = new SAPCoding();
		System.out.println(Calendar.getInstance());
		System.out.println("Current Year: " + INSTANCE.getYear());
	}
}