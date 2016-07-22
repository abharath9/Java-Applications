
public class XPowerY {
	static int xPowery(int x, int y) {
		int result = 1;
		if (y == 0) {
			return 1;
		}
		for (int i = 1; i <= y; i++) {
			result = result * x;
		}
		return result;
	}

	static int xPowyRecursive(int x, int y) {
		int result = 1;
		if (y == 0) {
			return 1;
		}

		return x * xPowyRecursive(x, y - 1);
	}

	float power(float x, int y) {
		float temp;
		if (y == 0)
			return 1;
		temp = power(x, y / 2);
		if (y % 2 == 0)
			return temp * temp;
		else {
			if (y > 0)
				return x * temp * temp;
			else
				return (temp * temp) / x;
		}
	}

	public static void main(String[] args) {
		int val = XPowerY.xPowery(3, 3);
		int v = XPowerY.xPowyRecursive(2, 10);
		System.out.print(v);
	}

}
