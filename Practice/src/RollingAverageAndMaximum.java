import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RollingAverageAndMaximum {
	static void calculateRollingAvgAndMax(int[] a, int windowSize) {

		if (windowSize > a.length) {
			System.out.print("window size should be less than the size of all the values");
			return;
		}
		System.out.print("ROlling Averages for window size " + windowSize + " :");
		for (int i = 0; i <= a.length - windowSize; i++) {
			float sum = 0;
			int count = 0;
			for (int j = i; count < windowSize; j++, count++) {
				sum = sum + a[j];
			}
			float avg = sum / windowSize;

			System.out.print(avg + " ");

		}
		System.out.println();
		calculateRollingMax(a, windowSize);
	}

	static void calculateRollingMax(int[] a, int windowSize) {
		if (windowSize > a.length) {
			System.out.println("window size should be less than the size of all the values");
			return;
		}
		System.out.print("Rolling Maximum for window size " + windowSize + " :");
		for (int i = 0; i <= a.length - windowSize; i++) {
			int max = Integer.MIN_VALUE;
			int count = 0;
			for (int j = i; count < windowSize; j++, count++) {
				if (max < a[j]) {
					max = a[j];
				}
				// sum = sum + a[j];
			}
			// float avg = sum / windowSize;
			System.out.print(max + " ");

		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		int[] a = { 1, 2, 3, 4, 5, 6 };
		Scanner sc = new Scanner(System.in);
		System.out.println("enter the file path where the data is availble");
		// read the file
		String file = sc.next();
		Scanner sc1 = new Scanner(new File(file));

		// while (sc1.hasNextLine()) {
		String line = sc1.nextLine();
		String[] inputValues = line.split(",");
		int[] intValues = new int[inputValues.length];
		// line = sc1.nextLine();
		for (int i = 0; i < inputValues.length; i++) {
			intValues[i] = Integer.parseInt(inputValues[i]);
		}

		do {
			System.out.println("Enter the window size");
			int windowSize = Integer.parseInt(sc.next());

			RollingAverageAndMaximum.calculateRollingAvgAndMax(intValues, windowSize);
			// SimpleMovingAverage.calculateRollingMax(a, 3);
			System.out.println();
		} while (true);

	}
}
