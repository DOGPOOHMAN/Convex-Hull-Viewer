package convexAlgorithm;

/**
 * Created by rick-lee on 2017/4/29.
 */
public class Point {

    private int xAxle;
    private int yAxle;

    public Point(int x, int y){

        this.xAxle = x;
        this.yAxle = y;
    }


    public Point isFarToWhichPoint(Point pointA, Point pointB){

        int dsvA = this.getDistanceSquareValue(pointA);
        int dsvB = this.getDistanceSquareValue(pointB);

        if (dsvA > dsvB)
            return pointA;
        else
            return pointB;

    }

    public int getDistanceSquareValue(Point target){

        if(this.equals(target)) return 0;

        int xAxleDelta = target.xAxle - this.xAxle;
        int yAxleDelta = target.yAxle - this.yAxle;

        return (xAxleDelta * xAxleDelta) + (yAxleDelta * yAxleDelta);
    }


    public boolean isItLeftMostThen(Point target){

        if(this.equals(target)) return false;

        if(this.xAxle < target.xAxle)
            return true;
        else if((this.xAxle == target.xAxle) && (this.yAxle < target.yAxle))
            return true;
        else
            return false;


    }

    public static int getCrossProductOfAcrossB(Point origin, Point a, Point b){

        int vectorA_x = a.xAxle - origin.xAxle;
        int vectorA_y = a.yAxle - origin.yAxle;
        int vectorB_x = b.xAxle - origin.xAxle;
        int vectorB_y = b.yAxle - origin.yAxle;

        return (vectorA_x * vectorB_y) - (vectorA_y * vectorB_x);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (!(obj instanceof Point)) return false;

        Point other = (Point)obj;
        return (this.xAxle == other.xAxle) && (this.yAxle == other.yAxle);
    }
}
