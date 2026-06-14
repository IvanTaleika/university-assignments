package cpp.lab3.gui.client.main;


import cpp.lab3.gui.utils.GBC;
import cpp.lab3.logic.Shop;

import javax.swing.*;
import java.awt.*;

/**
 * @author xefza
 * @version 1.0
 * @created 03-Apr-2018 17:46:47
 */
public class ClientGUI {

    private View clientInfoView;
    private Controller controller;
    private JFrame frame;
    public static final int DEFAULT_INSETS = 5;
    public static final int MINIMUM_WIDTH = 600;
    public static final int MINIMUM_HEIGHT = 500;

    public ClientGUI(Shop shop) {
        frame = new JFrame("Client page");

        frame.setMinimumSize(new Dimension(MINIMUM_WIDTH, MINIMUM_HEIGHT));

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

    public JFrame getFrame() {
        return frame;
    }


}