package ImageId;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfDMatch;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.features2d.DescriptorExtractor;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.FeatureDetector;
import org.opencv.features2d.Features2d;
import org.opencv.imgcodecs.Imgcodecs;

public class ImageCompare {

	public static void main(String[] args) {
	      System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
	      
	      //String img1Location = "c:\\stick-figure1.jpg";
	      //String img2Location = "c:\\stick-figure2.jpg";
	      String compareImage = "c:\\chair1.jpg";
	      
	      IdentifyImage imageID = new IdentifyImage();
	      String imageDesc = imageID.IdImage(compareImage);
	      System.out.println("Image " + compareImage + " was identified as a " + imageDesc);
	      
	      /*
	      Mat img1 = Imgcodecs.imread(img1Location);
	      Mat img2 = Imgcodecs.imread(img2Location);
	      Mat outImg = new Mat();
	      
	      //TODO check to ensure the images loaded
	      
	      MatOfKeyPoint img1KP = new MatOfKeyPoint();
	      MatOfKeyPoint img2KP = new MatOfKeyPoint();
	      FeatureDetector detector = FeatureDetector.create(FeatureDetector.ORB);
	      detector.detect(img1, img1KP);
	      detector.detect(img2, img2KP);
	      
	      System.out.println("IMG1 size: " + img1.size());
	      System.out.println("IMG2 size: " + img2.size());
	      
	      //Features2d.drawKeypoints(img1, img1KP, outImg);
	      
	      DescriptorExtractor de = DescriptorExtractor.create(DescriptorExtractor.ORB);
	      Mat descr1 = new Mat();
	      Mat descr2 = new Mat();
	      
	      de.compute(img1, img1KP, descr1);
	      de.compute(img2, img2KP, descr2);
	      System.out.println("Computation1 dump ("+descr1.size()+"): ");// + descr1.dump().toString());
	      System.out.println("Computation2 dump ("+descr2.size()+"): ");// + descr2.dump().toString());
	      
	      DescriptorMatcher dm = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE);// .FLANNBASED);
	      MatOfDMatch matches = new MatOfDMatch();
	      Mat combinedDescr = new Mat();
	      combinedDescr.push_back(descr1);
	      combinedDescr.push_back(descr2);
	      System.out.println("Combined descriptors: " + combinedDescr.size() + "(" + descr1.size() + " x " + descr2.size() + ")");
	      dm.match(combinedDescr, matches);
	      System.out.println("Matches (" + matches.size() + ")"); // dump: " + matches.dump());
	      
	      List<MatOfDMatch> radiusMatches = new ArrayList<MatOfDMatch>();
	      float maxDistance = (float)5.0;
	      dm.radiusMatch(combinedDescr, radiusMatches, maxDistance);
	      System.out.println("Radius matches: " + radiusMatches.size());
	      */

	}

}
