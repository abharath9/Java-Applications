
public class CatDeepPit {

	public static int calculate(int deep, int climbDay, int slipsNight) {
		int current = 0;
		int dayCount = 0;
		while (current <= deep) {
			dayCount++;
			current += climbDay;
			if (current == deep) {
				return dayCount;
			}
			current -= slipsNight;

		}
		return dayCount;

	}

	public static void main(String[] args) {
		// CatDeepPit.calculate(20, 5, 4);
		System.out.println(CatDeepPit.calculate(20, 5, 4));
	}
}
