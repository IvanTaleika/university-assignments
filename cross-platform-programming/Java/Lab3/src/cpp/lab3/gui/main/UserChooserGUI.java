package cpp.lab3.gui.main;

import cpp.lab3.gui.client.dialogs.SwitchClientDialog;
import cpp.lab3.gui.client.main.ClientGUI;
import cpp.lab3.gui.manager.ManagerGUI;
import cpp.lab3.gui.utils.GBC;
import cpp.lab3.logic.Shop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class UserChooserGUI {
    public static final int MINIMUM_WIDTH = 250;
    public static final int MINIMUM_HEIGHT = 100;
    public static final int DEFAULT_INSETS = 5;

    private SwitchClientDialog switchClientDialog;

    private ManagerGUI managerGUI;
    private ClientGUI clientGUI;
    private JLabel messageLabel;
    private JButton managerButton;
    private JButton clientButton;
    private JFrame frame;

    public UserChooserGUI(Shop shop) {

        frame = new JFrame("Lab3");
        frame.setMinimumSize(new Dimension(MINIMUM_WIDTH, MINIMUM_HEIGHT));


        frame.setLayout(new GridBagLayout());
        messageLabel = new JLabel("Enter as:");
        frame.add(messageLabel, new GBC(0, 0, 2, 1)
                .setAnchor(GBC.WEST).setInsets(DEFAULT_INSETS));

        buildClientButton(shop);

        managerButton = new JButton("Manager");
        managerButton.addActionListener(e -> {
            if(managerGUI == null) {
                managerGUI = new ManagerGUI(shop.getShopOwnerCompany().getManager());
                managerGUI.getFrame().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                managerGUI.getFrame().addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        super.windowClosed(e);
                        frame.setVisible(true);
                    }
                });
            }
            frame.setVisible(false);
            managerGUI.getFrame().setVisible(true);
        });
        frame.add(managerButton, new GBC(1, 1).setInsets(DEFAULT_INSETS)
                                              .setAnchor(GBC.CENTER));
    }

    private void buildClientButton(Shop shop) {
        clientButton = new JButton("Client");



        clientButton.addActionListener(e -> {
            if(clientGUI == null) {
                clientGUI = new ClientGUI(shop);
                clientGUI.getFrame().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                clientGUI.getFrame().addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        super.windowClosed(e);
                        frame.setVisible(true);
                    }
                });
            }

            frame.setVisible(false);
            clientGUI.getFrame().setVisible(true);
        });
        frame.add(clientButton, new GBC(0, 1).setAnchor(GBC.CENTER)
                                             .setInsets(DEFAULT_INSETS));
    }


    public JFrame getFrame(){
        return frame;
    }

}
