import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static Rectangle2D rect;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s = in.readLine()) != null) {
			System.out.println(s);
			String[] st = s.split(" ");
			int x1 = Integer.parseInt(st[0]);
			int y1 = Integer.parseInt(st[1]);
			int wd1 = Integer.parseInt(st[2]);
			int ht1 = Integer.parseInt(st[3]);

			int x2 = Integer.parseInt(st[4]);
			int y2 = Integer.parseInt(st[5]);
			int wd2 = Integer.parseInt(st[6]);
			int ht2 = Integer.parseInt(st[7]);

			Rectangle rect1 = new Rectangle(x1, y1, wd1, ht1);
			Rectangle rect2 = new Rectangle(x2, y2, wd2, ht2);

			Rectangle intersection = rect1.intersection(rect2);
			boolean b = (x1 < x2 + y1 && x1 + wd1 > x2 && y1 < y2 + ht2 && y1 + ht1 > y2);
			if (intersection.isEmpty()) {
				System.out.println(false);
			} else {
				System.out.println(true);
			}
		}

	}
}
