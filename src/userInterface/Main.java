package userInterface;

import convexAlgorithm.ConvexHullAlgorithm;
import implementAlgorithm.GiftWrappingAlgo;
import implementAlgorithm.GrahamScanAlgo;

import java.awt.*;

/**
 * Created by rick-lee on 2017/4/30.
 */
public class Main {

    public static void main(String[] args){

        final String[] algoName
                = {"Gift Wrapping", "Graham's Scan"};

        final ConvexHullAlgorithm[] algorithms
                = {new GiftWrappingAlgo(), new GrahamScanAlgo()};

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
