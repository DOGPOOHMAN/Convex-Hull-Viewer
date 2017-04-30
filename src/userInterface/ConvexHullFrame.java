package userInterface;

import convexAlgorithm.Point;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rick-lee on 2017/4/30.
 */
public class ConvexHullFrame extends JFrame {

    private JPanel pointViewerPanel;
    private JPanel controlPanel;
    private int width = 650;
    private int height = 550;
    private List<Point> overallPoints;

    public ConvexHullFrame(){
        overallPoints = new ArrayList<>();
        overallPoints.add(new Point(0,0));
        initFrame();
        initControlPanel();
        initPointViewerPanel();
    }

    private void initFrame(){
        setBounds(100, 100, width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        setTitle("Convex Hull Viewer");
        setLayout(null);
    }

    private void initControlPanel(){

        controlPanel = new ControlPanel(overallPoints);
        controlPanel.setBounds(0, 0,width, 60);
        controlPanel.setBackground(new Color(0x005AB5));

        getContentPane().add(controlPanel);

    }

    private void initPointViewerPanel() {

        pointViewerPanel = new PointViewerPanel(overallPoints);
        pointViewerPanel.setLayout(new BorderLayout(5, 5));
        pointViewerPanel.setBounds(10, 70, width - 20, height - 100);

        getContentPane().add(pointViewerPanel);
    }

}
