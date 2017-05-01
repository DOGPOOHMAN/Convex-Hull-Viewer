package userInterface;

import panels.PointViewerAdapter;
import panels.PointViewerPanel;

import javax.swing.*;

import java.awt.*;

/**
 * Created by rick-lee on 2017/4/30.
 */
public class ConvexHullFrame extends JFrame {

    private JPanel pointViewerPanel;
    private JPanel controlPanel;
    private int width = 650;
    private int height = 550;

    public ConvexHullFrame(){
        initFrame();
        initPointViewerPanel();
        initControlPanel();
    }

    private void initFrame(){
        setBounds(100, 100, width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        setTitle("Convex Hull Viewer");
        setLayout(null);
    }


    private void initPointViewerPanel() {

        pointViewerPanel = new PointViewerPanel();
        pointViewerPanel.setLayout(new BorderLayout(5, 5));
        pointViewerPanel.setBounds(10, 70, width - 20, height - 100);

        getContentPane().add(pointViewerPanel);
    }

    private void initControlPanel(){

        controlPanel = new PointViewerAdapter((PointViewerPanel) pointViewerPanel);
        controlPanel.setBounds(0, 0,width, 60);
        controlPanel.setBackground(new Color(0x005AB5));

        getContentPane().add(controlPanel);

    }

}
