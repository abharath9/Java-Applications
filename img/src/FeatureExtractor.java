import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Size;

//import org.op

public class FeatureExtractor {
	// private static final int CV_LOAD_IMAGE_GRAYSCALE = null;
	private int erosion_size;
	private int deltax;
	private int deltay;
	private int mindist;
	private int hist90;
	private int hist50;
	private float hgcut;
	private Mat grayscale = new Mat();
	ArrayList<Integer> mark_ref = new ArrayList<Integer>();
	TreeMap<Integer, Integer> index_ref = new TreeMap<Integer, Integer>();
	LinkedList<Integer> q_ref = new LinkedList<Integer>();

	private void bfs(Mat in, int v, ArrayList<Point> points, ArrayList<Integer> mark, TreeMap<Integer, Integer> index,
			int id, LinkedList<Integer> q) {

		mark_ref.set(v, id);
		q_ref.offer(v);
		while (!q_ref.isEmpty()) {
			int k = q_ref.peek();
			q_ref.poll();

			int x = (int) points.get(k).x;
			int y = (int) points.get(k).y;
			// 8 neighbors
			if (x - 1 >= 0) {
				if (y - 1 >= 0) {

					update(mark_ref, index, q_ref, in.cols(), x - 1, y - 1, id);
				}
				update(mark_ref, index, q_ref, in.cols(), x - 1, y, id);
				if (y + 1 < in.rows()) {
					update(mark_ref, index, q_ref, in.cols(), x - 1, y + 1, id);
				}
			}
			if (y - 1 >= 0) {
				update(mark_ref, index, q_ref, in.cols(), x, y - 1, id);
			}
			if (y + 1 < in.rows()) {
				update(mark_ref, index, q_ref, in.cols(), x, y + 1, id);
			}

			if (x + 1 < in.cols()) {
				if (y - 1 >= 0) {
					update(mark_ref, index, q_ref, in.cols(), x + 1, y - 1, id);
				}
				update(mark_ref, index, q_ref, in.cols(), x + 1, y, id);
				if (y + 1 < in.rows()) {
					update(mark_ref, index, q_ref, in.cols(), x + 1, y + 1, id);
				}
			}
		}
	}

	private Mat filter(String fn, Mat out) {
		Mat dst = new Mat();
		grayscale = org.opencv.highgui.Highgui.imread(fn, org.opencv.highgui.Highgui.CV_LOAD_IMAGE_GRAYSCALE);
		int hbins = 256;
		// int[] histSize = {hbins};
		float[] hranges = { 0, 256 };

		// float[][] ranges = {hranges};

		MatOfFloat hist = new MatOfFloat();

		// we compute the histogram from the 0-th and 1-st channels
		org.opencv.core.MatOfInt channels = new org.opencv.core.MatOfInt(0);
		org.opencv.core.MatOfFloat ranges = new org.opencv.core.MatOfFloat(hranges);
		org.opencv.core.MatOfInt histsize = new org.opencv.core.MatOfInt(hbins);
		// int[] channels = new int[]{0};

		// org.opencv.imgproc.Imgproc.calcHist(images, channels, mask, hist,
		// histSize, ranges, accumulate);

		ArrayList<Mat> grayscale1 = new ArrayList<Mat>();
		grayscale1.add(grayscale);
		org.opencv.imgproc.Imgproc.calcHist(grayscale1, channels, new Mat(), hist, histsize, ranges, true);

		// the histogram is uniform - do not use mask

		// org.opencv.imgproc.Imgproc.calcHist(images, channels, mask, hist,
		// histSize, ranges);

		float s = 0F;
		float s0;
		float t = grayscale.rows() * grayscale.cols() * hgcut;
		float t50 = (float) (grayscale.rows() * grayscale.cols() * .5);
		float t90 = (float) (grayscale.rows() * grayscale.cols() * hgcut * .9);
		int th = -1;

		for (int i = 0; i < 256; i++) {
			s0 = s;
			s = s + hist.toList().get(i);

			// get(i,0);
			if (s >= t && s0 < t) {
				th = i + 1;
			}
			if (s >= t90 && s0 < t90) {
				hist90 = i;
			}
			if (s >= t50 && s0 < t50) {
				hist50 = i;
			}
		}

		// cerr<<"threshold"<<th<<endl;
		Mat bw = new Mat();
		org.opencv.imgproc.Imgproc.threshold(grayscale, bw, th, 255, org.opencv.imgproc.Imgproc.THRESH_BINARY);
		// Create a structuring element
		Mat element = org.opencv.imgproc.Imgproc.getStructuringElement(org.opencv.imgproc.Imgproc.MORPH_ELLIPSE,
				new Size(2 * erosion_size + 1, 2 * erosion_size + 1), new Point(erosion_size, erosion_size));

		// Apply erosion or dilation on the image
		org.opencv.imgproc.Imgproc.erode(bw, dst, element);
		/*
		 * erosion_size /=1.5; element = getStructuringElement(
		 * cv::MORPH_ELLIPSE, Size( 2*erosion_size + 1, 2*erosion_size+1 ),
		 * Point( erosion_size, erosion_size ) );
		 */
		org.opencv.imgproc.Imgproc.dilate(dst, out, element);
		return out;

	}

	private ArrayList<Rect> group(Mat in, ArrayList<Rect> res) {
		// Vector<Point> points = new Vector<Point>();
		// Vector<Integer> mark=new Vector<Integer>();
		ArrayList<Integer> mark = new ArrayList<Integer>();
		// ArrayList<Integer> mark1 = new ArrayList<Integer>();
		ArrayList<Point> points = new ArrayList<Point>();
		TreeMap<Integer, Integer> index = new TreeMap<Integer, Integer>();
		MatOfPoint locs = new MatOfPoint();
		Core.findNonZero(in, locs);
		// points = (ArrayList<Point>) locs.toList();

		for (int i = 0; i < locs.total(); i++) {
			System.out.println("hi");
			// System.out.println(locs.toArray().toString());
			Point[] pt = new Point[locs.toArray().length];
			pt = locs.toArray();
			System.out.println(pt[i]);
			points.add(pt[i]);

			int j = (int) (pt[i].y * in.cols() + pt[i].x);

			// int j = locs.<Point>at(i).y * in.cols + locs.<Point>at(i).x;

			index.put(j, i);
			mark.add(-1);
			// System.out.println("hiij" + mark.get(i) + ':' + i);
		}
		mark_ref = mark;
		int c = 0;
		for (int i = 0; i < locs.total(); i++) {
			if (mark.get(i) == -1) {

				// cerr<<"bfs"<<i<<points[i]<<endl;
				bfs(in, i, points, mark, index, c, q_ref);
				// System.out.println("hii2" + mark.get(0) + "in" + i);
				c++;
			}
		}
		// System.out.println("out:" + mark.get(0));

		// cerr<<"bfs done\n";
		// ArrayList<Integer>[] clusters = (ArrayList<Integer>[])new
		// ArrayList[c];
		// ArrayList<Integer>[] clusters =new ArrayList[c];
		// ArrayList<ArrayList<Integer>> clusters=new
		// ArrayList<ArrayList<Integer>>(c);
		// Integer[][] clusters=new Integer[c][];
		// Vector<Integer>[] clusters=(Vector<Integer>[]) new
		// Vector<Integer>[c];
		@SuppressWarnings("unchecked")
		// Vector<Integer>[] anArray = (Vector<Integer>[]) new
		// Vector<Integer>[100];
		Vector<Integer>[] clusters = new Vector[c];
		mark = mark_ref;

		for (int i = 0; i < locs.total(); i++) {
			System.out.println(mark.size());
			System.out.println(i + ":" + c);
			System.out.println(mark.size() + "out" + mark.get(i).toString());
			System.out.println(clusters[mark.get(i)]);
			// System.out.println("hi");
			// clusters.get(mark.get(i)).add(i);
			clusters[mark.get(i)] = new Vector<Integer>(i);
			System.out.println(i);
		}

		for (int i = 0; i < c; i++) {
			int maxx = 0;
			int maxy = 0;
			int minx = 100000;
			int miny = 1000000;
			for (int j = 0; j < clusters[i].size(); j++) {
				// System.out.println("hi" + clusters[i].get(j));
				int x = (int) points.get(clusters[i].get(j)).x;
				int y = (int) points.get(clusters[i].get(j)).y;
				if (x > maxx) {
					maxx = x;
				}
				if (x < minx) {
					minx = x;
				}
				if (y > maxy) {
					maxy = y;
				}
				if (y < miny) {
					miny = y;
				}
			}
			// cout<<i<<" x["<<minx<<","<<maxx<<"], y["<<miny<<","<<maxy<<"]\n";
			Rect r = new Rect(minx, miny, maxx - minx, maxy - miny);
			res.add(r);
			// cout<<r<<endl;
		}
		return res;
	}

	private void update(ArrayList<Integer> mark, Map<Integer, Integer> index, LinkedList<Integer> q, int ncols, int x,
			int y, int id) {

		Integer key = y * ncols + x;
		Integer value;
		for (Map.Entry<Integer, Integer> entry : index.entrySet()) {

			if (entry.getKey().equals(key) && mark.get(index.get(key)) == -1) {
				value = index.get(key);
				mark.set(value, id);
				q.offer(value);
			}

		}
		mark_ref = mark;
		q_ref = q;

	}

	public FeatureExtractor(int es, float hg, int dx, int dy, int md) {
		erosion_size = es;
		hgcut = hg;
		deltax = dx;
		deltay = dy;
		mindist = md;
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}

	public final String extract(String args) {
		Mat out = new Mat();
		// filter the image to leave only the object-related pixels
		out = filter(args, out);
		// imwrite("out.jpg", out);
		// group the object pixels
		ArrayList<Rect> r = new ArrayList<Rect>();
		ArrayList<Rect> rs = new ArrayList<Rect>();
		r = group(out, r);
		// for (int i=0; i<r.size(); i++){
		// cout<<r[i]<<endl;
		// }
		// cout<<"after clustering\n";

		// cluster the objects: merge the close object to form a cluster
		String s = new String(new char[10000]);

		if (r.size() > 0) {
			Clustering c = new Clustering();
			rs = c.Clustering(r, rs, deltax, deltay, mindist);

			// for (int i=0; i<rs.size(); i++){
			// cout<<rs[i]<<endl;
			// }
			String objs = c.json(rs, grayscale);
			s = String.format("{\"histogram\":{\"hist50\":%d,\"hist90\":%d},%s}", hist50, hist90, objs);
		} else {
			s = String.format("{\"histogram\":{\"hist50\":%d,\"hist90\":%d},\"objects\":[]}", hist50, hist90);
		}
		return s;
	}

}
/*
 * 
 * int main(int argc, char ** argv) { Mat out; filter(argv[1], out, 5, 0.9f);
 * imwrite(argv[2], out); vector<Rect> r, rs; group(out, r); for (int i=0;
 * i<r.size(); i++){ cout<<r[i]<<endl; } cout<<"after clustering\n"; Clustering
 * c(r, rs, 20, 50, 10); for (int i=0; i<rs.size(); i++){ cout<<rs[i]<<endl; } }
 */
