package convexAlgorithm;

/**
 * Created by rick-lee on 2017/4/29.
 */
public class Point {

    protected int xAxle;
    protected int yAxle;

    public Point(int x, int y){

        this.xAxle = x;
        this.yAxle = y;
    }


    public int getxAxle() {
        return xAxle;
    }

    public int getyAxle() {
        return yAxle;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (!(obj instanceof Point)) return false;

        Point other = (Point)obj;
        return (this.xAxle == other.xAxle) && (this.yAxle == other.yAxle);
    }

    @Override
    public String toString() {
        return "(" + xAxle + ", " + yAxle + ")";
    }
}
