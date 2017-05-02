package implementAlgorithm;

import convexAlgorithm.ConvexHullAlgorithm;
import convexAlgorithm.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rick-lee on 2017/5/2.
 */
public class GrahamScanAlgo implements ConvexHullAlgorithm {

    @Override
    public List<Point> runAlgorithm(List<Point> overallPoints) {

        GrahamScanBody grahamScan;
        grahamScan = getAGrahamScanBodyInstance(overallPoints);
        grahamScan.sort();//find convex hull points

        List<Point>points;
        points = getConvexHullPointList(grahamScan);

        return points;

    }


    private GrahamScanBody getAGrahamScanBodyInstance(List<Point> overallPoints){

        GrahamScanBody body;
        body = new GrahamScanBody(overallPoints.size());

        for (Point point : overallPoints) {

            body.addpoint(point.getxAxle(), point.getyAxle());
        }

        return body;
    }

    private List<Point> getConvexHullPointList(GrahamScanBody body){

        int topindex = body.extreme.topindex;
        int[] convexHullIndexs = body.extreme.index;
        int[] pointsX = body.xpoints;
        int[] pointsY = body.ypoints;
        List<Point> convexHullPoints = new ArrayList<>();

        //從pointsX[] pointsY[]座標陣列當中取出xy座標值
        //依照xy建立Point實體，並且加入convexHull凸包點
        int index;
        Point point;
        for (int i = 0; i <= topindex; i++)
        {
            index = convexHullIndexs[i];
            point = new Point(pointsX[index], pointsY[index]);
            convexHullPoints.add(point);
        }

        return convexHullPoints;
    }
}
