package implementAlgorithm;

import convexAlgorithm.ConvexHullAlgorithm;
import convexAlgorithm.Point;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by rick-lee on 2017/4/30.
 */
public class GiftWrappingAlgo implements ConvexHullAlgorithm {

    private GiftWrapPoint leftMostPoint;
    private List<Point> convexHullPoints;
    private List<GiftWrapPoint> overallGWPoint;

    public GiftWrappingAlgo(){

        this.overallGWPoint = new ArrayList<GiftWrapPoint>();
        this.convexHullPoints = new ArrayList<Point>();
    }

    private void convertPointToGitWrapPoint(List<Point> list){

        GiftWrapPoint gwPoint;
        for (Point point : list) {

            gwPoint = new GiftWrapPoint(point.getxAxle(), point.getyAxle());
            overallGWPoint.add(gwPoint);
        }
    }

    @Override
    public List<Point> runAlgorithm(List<Point> overallPoints) {

        convertPointToGitWrapPoint(overallPoints);

        this.leftMostPoint = findLeftMostPoint(overallGWPoint.iterator());
        convexHullPoints.add(leftMostPoint);

        boolean flag = true;
        Iterator<GiftWrapPoint> overallIte;
        GiftWrapPoint candidate, other, lastCH;
        lastCH = this.leftMostPoint;

        while (flag){

            overallIte = overallGWPoint.iterator();
            candidate = overallIte.next();

            while (overallIte.hasNext()){

                other = overallIte.next();
                int crossProduct = lastCH.getCrossProductOfAcrossB(candidate, other);
                if (crossProduct > 0)
                    candidate = other;
                else if (crossProduct == 0){//candidate and other was on same line

                    GiftWrapPoint farPoint = (GiftWrapPoint) lastCH.distanceToWhichPointIsLonger(candidate, other);

                    //choose longer distance as candidate, and use == is correct
                    if (farPoint == other){
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

    private GiftWrapPoint findLeftMostPoint(Iterator<GiftWrapPoint> pointsIte){

        GiftWrapPoint candidate = pointsIte.next();
        GiftWrapPoint point;

        while (pointsIte.hasNext()) {

            point = pointsIte.next();
            if (candidate.isItLeftMostThen(point))
                candidate = point;
        }

        return candidate;
    }


}
