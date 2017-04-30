package userInterface;

import convexAlgorithm.ConvexHull;
import convexAlgorithm.GiftWrappingAlgo;
import convexAlgorithm.Point;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rick-lee on 2017/4/30.
 */
public class Main {

    public static void main(String[] args){

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ConvexHullFrame();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

//    public static void main(String[] args){
//
//        List<Point> chPoints;
//        ConvexHull convexHull = new ConvexHull(new GiftWrappingAlgo());
//        List<Point> pointsOnPanel = new ArrayList<Point>();
//        pointsOnPanel.add(new Point(1, 1));
//        pointsOnPanel.add(new Point(4, 1));
//        pointsOnPanel.add(new Point(1, 8));
////        pointsOnPanel.add(new Point(4, 8));
////
//        pointsOnPanel.add(new Point(2, 6));
////        pointsOnPanel.add(new Point(2, 7));
//        pointsOnPanel.add(new Point(3, 6));
////        pointsOnPanel.add(new Point(3, 7));
////
////        pointsOnPanel.add(new Point(4, 9));
//
//        convexHull.setOverallPoints(pointsOnPanel);
//        chPoints = convexHull.findConvexHullPoints();
//        System.out.println(chPoints);
//    }
}
