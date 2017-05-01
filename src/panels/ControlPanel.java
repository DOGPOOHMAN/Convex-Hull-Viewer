package panels;

import javax.swing.*;
import java.awt.*;

/**
 * Created by rick-lee on 2017/4/30.
 */
class ControlPanel extends JPanel {

    JLabel inforText;
    JLabel pointAmountText;
    JComboBox algoComboBox;
    JButton autoAddBtn;
    JButton runOrCleanBtn;
    final static String CONTROL_PANEL = " Control Panel";
    final static String RUN = "Run";
    final static String CLEAN = "Clean";

    //只有PointViewAdapter 才能建立實體
    ControlPanel(String[] comboItemName){

        setLayout(new BorderLayout());
        initComponentWithOrder(comboItemName);
    }

    private void initComponentWithOrder(String[] itemsName){

        pointAmountText = new JLabel("0");
        algoComboBox = new JComboBox(itemsName);
        algoComboBox.setSelectedIndex(0);
        autoAddBtn = new JButton("Auto Add 50 Points");
        runOrCleanBtn = new JButton(RUN);

        JPanel leftSide = new JPanel(new FlowLayout());
        leftSide.add(autoAddBtn);
        leftSide.add(new JLabel("   Total Points:"));
        leftSide.add(pointAmountText);

        JPanel rightSide = new JPanel(new FlowLayout());
        rightSide.add(new JLabel("  演算法"));
        rightSide.add(algoComboBox);
        rightSide.add(runOrCleanBtn);


        inforText = new JLabel(CONTROL_PANEL);
        inforText.setForeground(Color.white);
        add(BorderLayout.NORTH, inforText);
        add(BorderLayout.CENTER, leftSide);
        add(BorderLayout.EAST, rightSide);
    }

}
