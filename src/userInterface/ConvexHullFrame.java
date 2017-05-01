package userInterface;

import convexAlgorithm.ConvexHullAlgorithm;
import panels.PointViewerAdapter;
import panels.PointViewerPanel;

import javax.swing.*;

import java.awt.*;

/**
 * Created by rick-lee on 2017/4/30.
 */
public class ConvexHullFrame extends JFrame {

    private JPanel viewerPanel;
    private JPanel controlPanel;
    private int width = 650;
    private int height = 550;

    private String[] algorithmsName;
    private ConvexHullAlgorithm[] algorithms;

    private ConvexHullFrame(String[] algoName, ConvexHullAlgorithm[] algo){

        this.algorithmsName = algoName;
        this.algorithms = algo;

        initFrame();
        initPointViewerPanel();
        initControlPanel();
        setVisible(true);
    }

    public static JFrame getConvexHullFrameInstance(String[] algoName, ConvexHullAlgorithm[] algo){

        if (algoName == null || algo == null)
            return null;

        else if (algoName.length == 0 || algo.length == 0)
            return null;

        else if (algoName.length != algo.length)
            return null;
        else
            return new ConvexHullFrame(algoName, algo);
    }

    private void initFrame(){
        setBounds(100, 100, width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Convex Hull Viewer");
        setLayout(null);
    }


    private void initPointViewerPanel() {

        viewerPanel = new PointViewerPanel();
        viewerPanel.setLayout(new BorderLayout(5, 5));
        viewerPanel.setBounds(10, 70, width - 20, height - 100);

        getContentPane().add(viewerPanel);
    }

    private void initControlPanel(){

        controlPanel = new PointViewerAdapter((PointViewerPanel) viewerPanel,
                algorithmsName,
                algorithms);

        controlPanel.setBounds(0, 0,width, 60);
        controlPanel.setBackground(new Color(0x005AB5));

        getContentPane().add(controlPanel);

    }

}
