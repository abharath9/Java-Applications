import org.opencv.core.Core;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.TreeMap;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Size;

public class GlobalMembersMain
{
	public static void main(String[] args)
	{
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		//System.loadLibrary(org.opencv);
		System.out.println(System.getProperty("java.library.path"));
		int erosion_size = 3;
		float hgcut = 0.9f;
		int deltax = 20;
		int deltay = 50;
		int mindist = 20;
		//Mat grayscale = new Mat();
		FeatureExtractor fe = new FeatureExtractor(erosion_size, hgcut, deltax, deltay, mindist);
		//String filename = args[1];
		String filename = "C:/Users/SONY/Documents/jpg/20131028/batch1/20131028_1_088B.jpg";
		final int last_slash_idx = filename.lastIndexOf("\\/");
		if (-1 != last_slash_idx)
		{
			filename.replace(filename.substring(0, last_slash_idx + 1),"");
			
		}
		System.out.print(filename);
		System.out.print('\t');
		//System.out.print(fe.extract(args[1]));
		System.out.print(fe.extract("C:/Users/SONY/Documents/jpg/20131028/batch1/20131028_1_088B.jpg"));
		System.out.print("\n");
	}
}