package userInterface;


import convexAlgorithm.Point;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.List;

/**
 * Created by rick-lee on 2017/4/30.
 */
public class PointViewerPanel extends JPanel {

    List<Point>pointsOnPanel;
    List<Point> points2DrawLine;
    boolean drawLineMode = false;

    public PointViewerPanel(List<Point> overallPoints){

        setBackground(Color.ORANGE);
        setLayout(new BorderLayout());

        pointsOnPanel = overallPoints;

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.drawString("Click to add new point.", getWidth()/2 - 80, getHeight()/2);
        drawCircle(g);
        if (drawLineMode)
            drawLine(g);

    }

    public void drawCircle(Graphics g){

        if (pointsOnPanel == null) return;

        for (Iterator<Point> iterator = pointsOnPanel.iterator();
             iterator.hasNext(); ) {

            Point point =  iterator.next();
            g.fillOval(point.getxAxle() - 2, point.getyAxle() - 2 , 4, 4);
        }

    }

    public void drawLine(Graphics g){

        drawLineMode = false;
        if (points2DrawLine == null) return;

        for (int i = 0; i < points2DrawLine.size(); i++) {
            Point start, end;
            if (i != points2DrawLine.size() - 1){
                start = points2DrawLine.get(i);
                end = points2DrawLine.get(i+1);
            }
            else
            {
                start = points2DrawLine.get(i);
                end = points2DrawLine.get(0);
            }
            g.drawLine(start.getxAxle(), start.getyAxle(), end.getxAxle(), end.getyAxle());
        }
    }

    public void setPoints2DrawLine(List<Point> points2DrawLine) {
        this.points2DrawLine = points2DrawLine;
    }

    public void enableDrawLineMode(){

        this.drawLineMode = true;
    }




}
