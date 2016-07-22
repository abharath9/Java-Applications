
public class IntegerToBinary {
	public static String intToBinary(int num) {
		String s = "";
		int tmp;
		if (num == 0) {
			return "0";
		}
		while (num > 1) {
			tmp = num % 2;
			s = tmp + s;
			num = num / 2;
		}
		s = 1 + s;
		return s;
	}

	public static int countOnes(int num) {
		int count = 0;
		if (num == 0) {
			return 0;
		}
		while (num > 1) {
			if (num % 2 == 1) {
				count++;
			}
			num = num / 2;
		}
		count++;
		return count;
	}

	public static void main(String[] args) {
		System.out.println(intToBinary(4));
		System.out.println(countOnes(4));
	}

}
