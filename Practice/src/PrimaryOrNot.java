import java.util.Scanner;

public class PrimaryOrNot {
	public static boolean primary(int num) {
		if (num == 1) {
			return false;
		}
		if (num == 2) {
			return true;
		}
		// int tmp = (num/2)+1;
		int cnt = 0;
		for (int i = 2; i <= (num / 2); i++) {
			if ((num % i) == 0) {
				cnt++;
			}
		}
		if (cnt == 0) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		System.out.println("Enter a number for check:");

		// int num=scan.nextInt();

		System.out.println("the number is " + primary(3));
	}
}
