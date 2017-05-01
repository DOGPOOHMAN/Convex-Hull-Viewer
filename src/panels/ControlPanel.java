package panels;

import javax.swing.*;
import java.awt.*;

/**
 * Created by rick-lee on 2017/4/30.
 */
class ControlPanel extends JPanel {

    JLabel inforlText;
    JLabel pointAmountText;
    JComboBox algoList;
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
        algoList = new JComboBox(itemsName);
        algoList.setSelectedIndex(0);
        autoAddBtn = new JButton("Auto Add 50 Points");
        runOrCleanBtn = new JButton(RUN);

        JPanel leftSide = new JPanel(new FlowLayout());
        leftSide.add(autoAddBtn);
        leftSide.add(new JLabel("   Total Points:"));
        leftSide.add(pointAmountText);

        JPanel rightSide = new JPanel(new FlowLayout());
        rightSide.add(new JLabel("  演算法"));
        rightSide.add(algoList);
        rightSide.add(runOrCleanBtn);


        inforlText = new JLabel(CONTROL_PANEL);
        inforlText.setForeground(Color.white);
        add(BorderLayout.NORTH, inforlText);
        add(BorderLayout.CENTER, leftSide);
        add(BorderLayout.EAST, rightSide);
    }

}
