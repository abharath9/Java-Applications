import java.util.ArrayList;

import org.opencv.core.Mat;
import org.opencv.core.Rect;

/*
class Rect{
public:
    int x, y, width, height;
    Rect(int a, int b, int c, int d):x(a),y(b),width(c),height(d){};
    Rect(const Rect & r){x=r.x, y=r.y, width=r.width, height=r.height;};
};
*/

public class Clustering {
	// vector<Rect> data;
	private ArrayList<Integer> parent = new ArrayList<Integer>();
	int r1 = 0;
	int r2 = 0;
	int d1 = 0;
	int d2 = 0;
	int x;
	int y;
	int w;
	int h;
	// ArrayList<Rect> r = new ArrayList<Rect>();

	private void sfind(int i, int r, int d) {
		d = 0;
		int p = parent.get(i);
		r = i;
		r1 = r;
		while (p != -1) {
			r = p;
			p = parent.get(p);
			d++;
			d1 = d;
			// cout<<"find "<<r<<"->"<<p<<endl;
		}

	}

	private void sunion(int r1, int d1, int r2, int d2) {
		if (r1 == r2)
			return;
		// cout<<"union before: "<<r1<<" "<<d1<<" "<<r2<<" "<<d2<<endl;
		if (d1 > d2) { // merge r2 to r1
			parent.set(r2, r1);
			r2 = r1;
			d2 = d1;
		} else {
			if (d1 == d2) {
				parent.set(r2, r1);
				r2 = r1;
				d1++;
				d2 = d1;
			} else {
				parent.set(r1, r2);
				r1 = r2;
				d1 = d2;
			}
		}
		// cout<<"union after: "<<r1<<" "<<d1<<" "<<r2<<" "<<d2<<endl;
	}

	private int rect_dist(Rect r1, Rect r2) {
		int r1maxx = r1.x + r1.width;
		int r2maxx = r2.x + r2.width;
		int r1minx = r1.x;
		int r2minx = r2.x;
		int r1maxy = r1.y + r1.height;
		int r2maxy = r2.y + r2.height;
		int r1miny = r1.y;
		int r2miny = r2.y;
		System.out.println(r1.size() + "		" + r2.size());

		// if (((r1minx <=r2maxx && r1minx>=r2minx) || (r1maxx<=r2minx &&
		// r1maxx>-r2minx) )
		// && ((r1miny <=r2maxy && r1miny>=r2miny) || (r1maxy<=r2miny &&
		// r1maxx>-r2miny) )
		// return 0;
		int dx = 0;
		int dy = 0;
		if (r1maxx < r2minx) {
			dx = r2minx - r1maxx;
		} else {
			if (r1minx > r2maxx) {
				dx = r1minx - r2maxx;
			}
		}

		if (r1maxy < r2miny) {
			dy = r2miny - r1maxy;
		} else {
			if (r1miny > r2maxy) {
				dy = r1miny - r2maxy;
			}
		}

		return dx * dx + dy * dy;
	}

	private int avgDensity(Mat grayscale, Rect r) {
		double c = 0;
		for (int i = r.x; i < r.x + r.width; i++) {
			for (int j = r.y; j < r.y + r.height; j++) {
				c = grayscale.get(j, i)[i];
				// <uchar>at(j, i);
			}
		}
		return (int) (c / (r.width * r.height));
	}

	public ArrayList<Rect> Clustering(ArrayList<Rect> r, ArrayList<Rect> ret, int deltax, int deltay, int dmin) {
		for (int i = 0; i < r.size(); i++) {
			parent.add(-1);
		}

		int dmin2 = dmin * dmin; // square it
		for (int i = 0; i < r.size() - 1; i++) {
			sfind(i, r1, d1);
			x = r.get(i).x;
			y = r.get(i).y;
			w = r.get(i).width;
			h = r.get(i).height;
			// cout<<"check "<<i<<endl;
			// cout<<r[i]<<"find->"<<r1<<","<<d1<<endl;
			for (int j = i + 1; j < r.size(); j++) {
				// cout<<"check "<<i<<","<<j<<endl;
				int dx = x - r.get(j).x;
				int dy = y - r.get(j).y;
				sfind(j, r2, d2);
				int d = rect_dist(r.get(i), r.get(j));
				System.out.println(d);
				// cout<<"dist"<<d<<endl;
				// cout<<r[j]<<"find->"<<r2<<","<<d2<<endl;
				if (d < dmin2) {
					// merge close objects
					// cout<<"merge close objects\n";
					sunion(r1, d1, r2, d2);
				} else if (Math.abs(dx) < deltax) {
					int d11;
					// cout<<"dy"<<dy<<",r[j]"<<r[j]<<"y="<<y<<"h="<<h<<endl;
					if (dy > 0) {
						d11 = y - (r.get(j).y + r.get(j).height);
					} else {
						d11 = y + h - r.get(j).y;
					}
					if (Math.abs(d11) < deltay) {
						// cout<<"merge close dy\n";
						sunion(r1, d1, r2, d2);
					}
				}
			}
		}
		// cout<<"done clustering\n";

		ArrayList<Integer> cid = new ArrayList<Integer>();
		ArrayList<Integer> cm = new ArrayList<Integer>();
		for (int i = 0; i < parent.size(); i++) {
			sfind(i, r1, d1);
			cm.add(r1);
			if (parent.get(i) == -1) {
				cid.add(i);
			}
		}

		for (int i = 0; i < cid.size(); i++) {
			int id = cid.get(i);
			int minx = 10000;
			int maxx = 0;
			int miny = 10000;
			int maxy = 0;
			for (int j = 0; j < cm.size(); j++) {
				if (cm.get(j) == id) {
					if (r.get(j).x < minx) {
						minx = r.get(j).x;
					}
					if (r.get(j).y < miny) {
						miny = r.get(j).y;
					}
					int d = r.get(j).x + r.get(j).width;
					if (d > maxx) {
						maxx = d;
					}
					d = r.get(j).y + r.get(j).height;
					if (d > maxy) {
						maxy = d;
					}
				}
			}
			Rect s = new Rect(minx, miny, maxx - minx, maxy - miny);
			ret.add(s);
		}
		// r = ret;
		System.out.println(ret.size() + "json");
		return ret;
		// System.out.println(r.size() + "json");

	}

	public final String json(ArrayList<Rect> r, Mat grayscale) {
		String s = "\"objects\":[";

		String s1 = new String(new char[10000]);
		for (int i = 0; i < r.size(); i++) {
			// System.out.println(r.size() + "json");
			// cerr<<"w:"<<r[i].width<<"h:"<<r[i].height<<endl;
			if ((Math.abs(r.get(i).width - r.get(i).height) / (float) Math.max(r.get(i).width, r.get(i).height) < 0.3
					|| Math.abs(r.get(i).width - r.get(i).height) < 20) && r.get(i).width * r.get(i).height < 2500) {
				System.out.println(r.size() + "yo");
				s.concat("{\"type\":1,"); // dots
				System.out.println(s);
			} else {
				s.concat("{\"type\":2,"); // lines
			}
			int d = avgDensity(grayscale, r.get(i));
			System.out.println(d + "bharath");
			s1 = String.format("\"x\":%d,\"y\":%d,\"width\":%d,\"height\":%d,\"density\":%d}", r.get(i).x, r.get(i).y,
					r.get(i).width, r.get(i).height, d);
			s.concat(s1);
			if (i < r.size() - 1) {
				s.concat(",");
			}
		}
		s.concat("]");

		return s;
	}

}