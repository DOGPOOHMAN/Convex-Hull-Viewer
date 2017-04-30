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

        boolean flag = true;
        Iterator<Point> overallIte;
        Point candidate, other, lastCH;
        lastCH = this.leftMostPoint;

        while (flag){

            overallIte = overallPoints.iterator();
            candidate = overallIte.next();

            while (overallIte.hasNext()){

                other = overallIte.next();
                int crossProduct = lastCH.getCrossProductOfAcrossB(candidate, other);
                if (crossProduct > 0)
                    candidate = other;
                else if (crossProduct == 0){//candidate and other was on same line

                    Point point = lastCH.distanceToWhichPointIsLonger(candidate, other);
                    if (point == other){//choose longer distance as candidate
                        candidate = other;
                    }
                }
            }

            if (candidate != this.leftMostPoint){
                this.convexHullPoints.add(candidate);
                lastCH = candidate;
            }
            else
                flag = false;

        }

        return this.convexHullPoints;
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
