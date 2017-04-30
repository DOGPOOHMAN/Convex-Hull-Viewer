package convexAlgorithm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by rick-lee on 2017/4/30.
 */
public class GiftWrappingAlgo implements ConvexHullAlgorithm{

    private Point leftMostPoint;
    private List<Point> convexHullPoints;

    public GiftWrappingAlgo(){

        this.convexHullPoints = new ArrayList<Point>();
    }

    @Override
    public List<Point> runAlgorithm(List<Point> overallPoints) {

        this.leftMostPoint = findLeftMostPoint(overallPoints.iterator());
        convexHullPoints.add(leftMostPoint);


        return null;
    }

    private Point findLeftMostPoint(Iterator<Point> pointsIte){

        Point candidate = pointsIte.next();
        Point point;

        while (pointsIte.hasNext()) {

            point = pointsIte.next();
            if (candidate.isItLeftMostThen(point))
                candidate = point;
        }

        return candidate;
    }


}
