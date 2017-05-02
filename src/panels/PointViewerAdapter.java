package panels;

import convexAlgorithm.ConvexHull;
import convexAlgorithm.ConvexHullAlgorithm;
import convexAlgorithm.Point;
import userInterface.InterfaceTool;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rick-lee on 2017/5/1.
 */
public class PointViewerAdapter extends ControlPanel {

    private PointViewerPanel mViewerPanel;
    private List<Point> mPointsOnPanel;
    private ConvexHullAlgorithm[] mAlgorithms;
    private boolean mRunMode = true;
    private boolean mEarlyHadClean = false;
    private static final String mAPP_SAY = "Application say: ";

    public PointViewerAdapter(PointViewerPanel panel, String[] algoName, ConvexHullAlgorithm[] algo){
        super(algoName);
        mAlgorithms = algo;
        mViewerPanel = panel;
        mPointsOnPanel = new ArrayList<>();
        initListener();
    }

    private void initListener(){

        autoAddBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                InterfaceTool.autoAddPoint(mPointsOnPanel,
                        50,
                        mViewerPanel.getWidth(),
                        mViewerPanel.getHeight());

                //update views
                int amount = mPointsOnPanel.size();
                pointAmountText.setText(Integer.toString(amount));
                mViewerPanel.paintCircle(mPointsOnPanel);
            }
        });

        runOrCleanBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mRunMode == true)
                    handleRunMode();
                else
                    handleCleanMode();
            }
        });

        mViewerPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if(mRunMode == false)return;

                super.mouseClicked(e);

                int xAxel = e.getX();
                int yAxel = e.getY();
                Point point = new Point(xAxel, yAxel);

                //如果新的點不位於邊界區域，以及不存在於原本的List當中，就加入他
                //因為檢查不位於邊界區域耗時短，所以優先檢查這個條件
                if(InterfaceTool.isPointNotAtBorderArea(
                        point,
                        mViewerPanel.getWidth(),
                        mViewerPanel.getHeight())
                        ){

                   boolean exist = mPointsOnPanel.contains(point);
                   if (!exist){
                       mPointsOnPanel.add(point);
                       int size = mPointsOnPanel.size();
                       pointAmountText.setText(Integer.toString(size));
                       mViewerPanel.paintCircle(mPointsOnPanel);
                   }
                }
            }
        });
    }


    private void handleRunMode(){
        //according to ComboBox to new ConvexHull();
        if (mPointsOnPanel.size() >= 3) {

            List<Point>convexHullPoint = handleAlgorithmRunning();

            //update Button
            mRunMode = false;
            runOrCleanBtn.setText(CLEAN);
            //close component function
            autoAddBtn.setEnabled(false);
            mViewerPanel.setEnabled(false);
            //repaint PointViewerPanel
            mViewerPanel.paintCircleAndLine(mPointsOnPanel, convexHullPoint);
        }
        else {
            inforText.setText(mAPP_SAY + "At least 3 points on panel to run algorithm.");
        }
    }


    private void handleCleanMode(){
        mEarlyHadClean = true;

        mRunMode = true;//轉換Listener Mode
        runOrCleanBtn.setText(RUN);

        mPointsOnPanel.clear();
        pointAmountText.setText("0");

        inforText.setText(ControlPanel.CONTROL_PANEL);
        autoAddBtn.setEnabled(true);
        mViewerPanel.setEnabled(true);
        mViewerPanel.repaint();
    }


    private List<Point> handleAlgorithmRunning(){

        //依照使用者在ComboBox選擇的演算法，建立相對應的演算法實體
        int selected = algoComboBox.getSelectedIndex();
        ConvexHull convexHull;
        convexHull = new ConvexHull(mAlgorithms[selected]);
        convexHull.setOverallPoints(mPointsOnPanel);

        //執行演算法取得ConvexHull-Points，並且記錄執行時間
        long startTime, runTime;
        List<Point>chPoints;

        startTime = System.nanoTime();
        chPoints = convexHull.findConvexHullPoints();
        runTime   = System.nanoTime() - startTime;

        //顯示實行結果於畫面上
        double runTimeSec = runTime * 1.0E-9d;
        String infor;
        infor = String.format("%s *Convex-Hull Points:%d     *Run Time:%f sec.",
                mAPP_SAY,
                chPoints.size(),
                runTimeSec);

        inforText.setText(infor);
        return chPoints;
    }

}
