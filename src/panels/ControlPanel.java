package userInterface;


import convexAlgorithm.Point;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by rick-lee on 2017/4/30.
 */
public class ControlPanel extends JPanel {

    private List<Point>pointsOnPanel;

    private JLabel pointAmountText;
    private JComboBox algoList;
    private JButton autoAddBtn;
    private JButton runBtn;
    private String[] algoNameList = {"GiftWrapping", "Graham's Scan"};

    public ControlPanel(List<Point> list){

        this.pointsOnPanel = list;
        setLayout(new BorderLayout());
        initComponentOrder();
        initListener();
    }

    private void initComponentOrder(){

        pointAmountText = new JLabel("0");
        algoList = new JComboBox(algoNameList);
        algoList.setSelectedIndex(0);
        autoAddBtn = new JButton("Auto Add 50 Points");
        runBtn = new JButton("Run");


        JPanel top = new JPanel(new FlowLayout());
        top.add(autoAddBtn);
        top.add(new JLabel("   Total Points:"));
        top.add(pointAmountText);

        JPanel down = new JPanel(new FlowLayout());
        down.add(new JLabel("  演算法"));
        down.add(algoList);
        down.add(runBtn);


        JLabel controlText = new JLabel("Control Panel");
        controlText.setForeground(Color.white);
        add(BorderLayout.NORTH, controlText);
        add(BorderLayout.CENTER, top);
        add(BorderLayout.EAST, down);
    }

    private void initListener(){

    }

    class autoAddListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
        }
    }

}
