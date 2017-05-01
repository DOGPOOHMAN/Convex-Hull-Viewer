package implementAlgorithm;

import convexAlgorithm.Point;

/**
 * Created by rick-lee on 2017/5/1.
 */
public class GiftWrapPoint extends Point {

    public GiftWrapPoint(int x, int y) {
        super(x, y);
    }


    public Point distanceToWhichPointIsLonger(Point pointA, Point pointB){

        int dsvA = this.getDistanceSquareValue(pointA);
        int dsvB = this.getDistanceSquareValue(pointB);

        if (dsvA > dsvB)
            return pointA;
        else
            return pointB;

    }

    public int getDistanceSquareValue(Point target){

        if(this.equals(target)) return 0;

        int xAxleDelta = target.getxAxle() - this.xAxle;
        int yAxleDelta = target.getyAxle() - this.yAxle;

        return (xAxleDelta * xAxleDelta) + (yAxleDelta * yAxleDelta);
    }


    public boolean isItLeftMostThen(Point target){

        if(this.equals(target)) return false;

        if(this.xAxle < target.getxAxle())
            return true;
        else if((this.xAxle == target.getxAxle()) && (this.yAxle < target.getyAxle()))
            return true;
        else
            return false;


    }

    public int getCrossProductOfAcrossB( Point a, Point b){

        int vectorA_x = a.getxAxle() - this.xAxle;
        int vectorA_y = a.getyAxle() - this.yAxle;
        int vectorB_x = b.getxAxle() - this.xAxle;
        int vectorB_y = b.getyAxle() - this.yAxle;

        return (vectorA_x * vectorB_y) - (vectorA_y * vectorB_x);
    }

}
