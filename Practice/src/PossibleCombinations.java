import java.awt.Graphics2D;
import java.util.Scanner;

import javax.swing.JApplet;

public class PossibleCombinations extends JApplet {
	public static void main(String[] args) {
		combination("", "1234");
		// fibonacci();
		// Graphics2D g = null;
		// drawCenteredCircle(g, 7, 8, 5);

	}

	public static void drawCenteredCircle(Graphics2D g, int x, int y, int r) {
		x = x - (r / 2);
		y = y - (r / 2);
		g.fillOval(x, y, r, r);
	}

	void DrawCircle(int r, int xCenter, int yCenter) {
		float pi = (float) 3.14;
		for (float f = 0; f < pi * 2; f += 0.1) {
			// SetPixel(xCenter + cos(f) * r, yCenter + sin(f) * r);
		}
	}

	private static void fibonacci() {
		int fibcnt = 15;
		int[] fib = new int[fibcnt];
		fib[0] = 0;
		fib[1] = 1;
		for (int i = 2; i < fibcnt; i++) {
			fib[i] = fib[i - 1] + fib[i - 2];
		}
		for (int i = 0; i < fibcnt; i++) {
			System.out.println(fib[i]);
		}

	}

	private static void combination(String prefix, String str) {
		int n = str.length();

		if (n == 0)
			System.out.println(prefix);
		else {

			for (int i = 0; i < n; i++) {
				// System.out.println("hi");
				// System.out.println( prefix + " " + str.charAt(i) + "," +
				// str.substring(0, i) + " " + " " + str.substring(i + 1, n));
				combination(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n));
			}
		}

	}

	Scanner s = new Scanner(System.in);

	// factorial
	public static void factorial(int n) {
		// factorial
		int num = 5;
		int sum = 1;
		while (num > 1) {
			sum *= num;
			num--;

		}
		System.out.println(sum);
	}

}
