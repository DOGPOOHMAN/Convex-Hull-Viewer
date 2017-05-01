package userInterface;

import convexAlgorithm.ConvexHullAlgorithm;
import convexAlgorithm.GiftWrappingAlgo;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by rick-lee on 2017/4/30.
 */
public class Main {

    public static void main(String[] args){

        String[] algoName
                = {"Gift Wrapping", "Graham's Scan"};

        ConvexHullAlgorithm[] algorithms
                = {new GiftWrappingAlgo(), new GiftWrappingAlgo()};

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {

                    ConvexHullFrame.getConvexHullFrameInstance(algoName, algorithms);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
