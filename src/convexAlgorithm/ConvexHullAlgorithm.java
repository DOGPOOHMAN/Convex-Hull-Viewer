package convexAlgorithm;

import java.util.List;

/**
 * Created by rick-lee on 2017/4/30.
 */

public interface ConvexHullAlgorithm {

    //Input: all the points.  Output: convex hull points.
    List<Point> runAlgorithm(List<Point> overallPoints);
}
