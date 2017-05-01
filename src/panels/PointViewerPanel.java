package panels;


import convexAlgorithm.Point;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.List;

/**
 * Created by rick-lee on 2017/4/30.
 */
public class PointViewerPanel extends JPanel {

    public static final int RADIUS = 2;
    private List<Point> pointsToDrawCircle;
    private List<Point> pointsToDrawLine;
    private boolean drawLineMode = false;
    private boolean drawCircleMode = false;

    public PointViewerPanel(){

        setBackground(Color.ORANGE);
        setLayout(new BorderLayout());
    }

    public void paintCircle(List<Point> circle){

        if (circle == null) return;

        this.pointsToDrawCircle = circle;
        this.drawCircleMode = true;
        repaint();
    }

    public void paintCircleAndLine(List<Point> circle, List<Point> line){

        if (circle == null || line == null) return;

        pointsToDrawCircle = circle;
        pointsToDrawLine = line;

        drawCircleMode = true;
        drawLineMode = true;
        repaint();

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.drawString("Click to add new point.", getWidth()/2 - 80, getHeight()/2);
        if (drawCircleMode)
            drawCircle(g);
        if (drawLineMode)
            drawLine(g);

    }

    private void drawCircle(Graphics g){

        for (Iterator<Point> iterator = pointsToDrawCircle.iterator();
             iterator.hasNext(); ) {

            Point point =  iterator.next();
            g.fillOval(point.getxAxle() - RADIUS,
                    point.getyAxle() - RADIUS ,
                    RADIUS * 2,
                    RADIUS * 2);
        }
        drawCircleMode = false;

    }

    private void drawLine(Graphics g){

        for (int i = 0; i < pointsToDrawLine.size(); i++) {
            Point start, end;
            if (i != pointsToDrawLine.size() - 1){
                start = pointsToDrawLine.get(i);
                end = pointsToDrawLine.get(i+1);
            }
            else
            {
                start = pointsToDrawLine.get(i);
                end = pointsToDrawLine.get(0);
            }
            g.drawLine(start.getxAxle(), start.getyAxle(), end.getxAxle(), end.getyAxle());
        }
        drawLineMode = false;
    }

}
