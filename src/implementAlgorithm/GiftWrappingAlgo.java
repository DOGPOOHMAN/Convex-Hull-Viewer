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

    private GiftWrapPoint leftMostPoint;//最左下角的點
    private List<GiftWrapPoint> overallPointsAsGWP;//平面上所有的點
    private List<Point> convexHullPoints;//所有的凸包頂點


    @Override
    public List<Point> runAlgorithm(List<Point> overallPoints) {

        List<Point> tempResult;
        initMemberVariables();
        convertPointObjToGiftWrapPointObj(overallPoints);
        getConvexHullPoints();

        tempResult = new ArrayList<>(convexHullPoints);
        //GC Member-Variables and elements which collected by List
        initMemberVariables();
        return tempResult;
    }

    public GiftWrappingAlgo(){

        this.overallPointsAsGWP = new ArrayList<GiftWrapPoint>();
        this.convexHullPoints = new ArrayList<Point>();
    }


    private void initMemberVariables(){

        leftMostPoint = null;
        overallPointsAsGWP.clear();
        convexHullPoints.clear();
    }

    private void convertPointObjToGiftWrapPointObj(List<Point> list){

        GiftWrapPoint gwPoint;
        for (Point point : list) {

            gwPoint = new GiftWrapPoint(point.getxAxle(), point.getyAxle());
            overallPointsAsGWP.add(gwPoint);
        }
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


    private void getConvexHullPoints() {

        this.leftMostPoint = findLeftMostPoint(overallPointsAsGWP.iterator());
        convexHullPoints.add(leftMostPoint);

        boolean flag = true;
        Iterator<GiftWrapPoint> overallIte;
        GiftWrapPoint candidate, other, lastCH;

        //從leftMostPoint開始尋找所有的凸包頂點，找到就加入convexHullPoints之中
        lastCH = this.leftMostPoint;
        while (flag){

            overallIte = overallPointsAsGWP.iterator();
            candidate = overallIte.next();
            //把最後加入凸包頂點，與平面上的某兩點做外積，篩選外機比較小的那個點，作為候選者
            //與平面上所有的點做上述的演算法後，即可求得新的一個凸包頂點
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
            //當新找到的凸包頂點等於leftMostPoint結束演算法
            if (candidate != this.leftMostPoint){
                this.convexHullPoints.add(candidate);
                lastCH = candidate;
            }
            else
                flag = false;

        }//end of while (flag)
    }

}
