package userInterface;

import convexAlgorithm.Point;

import java.util.List;
import java.util.Random;

/**
 * Created by rick-lee on 2017/4/30.
 */
public class InterfaceTool {

    public static int autoAddPoint(List<Point>pointList, int addAmount, int xLength, int yLength){

        if (addAmount <= 0) return 0;
        int maxPoint = (xLength - Point.RADIUS * 2) * (yLength - Point.RADIUS * 2);
        int maxAddAmount = maxPoint - pointList.size();//還能加入的數量

        if (addAmount > maxAddAmount) return -1;//無法加入那麼多數量的點

        int xAxel, yAxel, genAmount;

        genAmount = 0;

        while(genAmount != addAmount){

            //random() return [0,1)
            int xRandom = (int)(Math.random() * 100000);
            int yRandom = (int)(Math.random() * 100000);

            //座標從零開始，所以不用加一
            xAxel = xRandom % xLength;
            yAxel = yRandom % yLength;

            Point temp = new Point(xAxel, yAxel);

            //如果新的點不位於邊界區域，以及不存在於原本的List當中，就加入他
            //因為檢查不位於邊界區域耗時短，所以優先檢查這個條件
            if(isPointNotAtBorderArea(temp, xLength, yLength)){

                boolean exist = pointList.contains(temp);
                if (!exist){
                    pointList.add(temp);
                    genAmount++;
                }
            }

        }

        return addAmount;
    }

    public static boolean isPointNotAtBorderArea(Point p, int xLength, int yLength){

        int x_RightBound = xLength - 1 - Point.RADIUS;
        int x_LeftBound = Point.RADIUS;
        int y_RightBound = yLength - 1 - Point.RADIUS;
        int y_LeftBound = Point.RADIUS;

        return (x_LeftBound <= p.getxAxle()) &&
                (p.getxAxle() <= x_RightBound) &&
                (y_LeftBound <= p.getyAxle()) &&
                (p.getyAxle() <= y_RightBound);
    }
}
