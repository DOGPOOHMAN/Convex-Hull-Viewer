package convexAlgorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rick-lee on 2017/4/30.
 */
public class ConvexHull {

    private ConvexHullAlgorithm algorithm;
    private List<Point> overallPoints;
    private List<Point> convexHullPoints;


    public ConvexHull(ConvexHullAlgorithm algo){

        this.algorithm = algo;
    }

    public void setOverallPoints(List<Point> list) {

        this.overallPoints = list;
    }

    public List<Point> getConvexHullPoints(){

        return this.convexHullPoints;
    }

    public List<Point> findConvexHullPoints(){

        if (overallPoints == null) return null;
        if (overallPoints.isEmpty()) return null;

        convexHullPoints = algorithm.runAlgorithm(this.overallPoints);

        return convexHullPoints;
    }
}
