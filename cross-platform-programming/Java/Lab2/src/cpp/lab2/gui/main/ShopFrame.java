package cpp.lab2.gui.main;


import cpp.lab2.gui.GBC;
import cpp.lab2.logic.Shop;

import javax.swing.*;
import java.awt.*;

/**
 * @author xefza
 * @version 1.0
 * @created 03-Apr-2018 17:46:47
 */
public class ShopFrame {

    private View clientInfoView;
    private Controller controller;
    private JFrame frame;
    public static final int DEFAULT_INSETS = 5;
    public static final int MINIMUM_WIDTH = 600;
    public static final int MINIMUM_HEIGHT = 500;

    public ShopFrame(Shop shop) {
        frame = new JFrame("Lab2");
        frame.setMinimumSize(new Dimension(MINIMUM_WIDTH,MINIMUM_HEIGHT));

        clientInfoView = new View();
        shop.getClientBase().subscribe(clientInfoView);
        controller = new Controller(frame, shop);

        GridBagLayout mainLayout = new GridBagLayout();
        frame.setLayout(mainLayout);

        frame.add(clientInfoView.getInfoPanel(),new GBC(0,0,
                2,1).setWeight(100,0)
                    .setInsets(DEFAULT_INSETS));
        frame.add(clientInfoView.getOrdersTablePanel(),new GBC(0,1,
                1,1).setWeight(100,100).setFill(GBC.BOTH));
        frame.add(controller.getButtonsPanel(),new GBC(1,1,
                1,1).setWeight(0,100)
                    .setInsets(DEFAULT_INSETS));
    }


    public void setDefaultCloseOperation(int operation) {
        frame.setDefaultCloseOperation(operation);
    }

    public void setVisible(boolean b) {
        frame.setVisible(b);
    }

}