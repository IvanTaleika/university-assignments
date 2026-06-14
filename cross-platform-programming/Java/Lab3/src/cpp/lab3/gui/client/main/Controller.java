package cpp.lab3.gui.client.main;


import cpp.lab3.gui.client.dialogs.MoneyInputDialog;
import cpp.lab3.gui.client.dialogs.OrderDialog;
import cpp.lab3.gui.client.dialogs.SwitchClientDialog;
import cpp.lab3.gui.utils.GBC;
import cpp.lab3.logic.Shop;

import javax.swing.*;
import java.awt.*;

/**
 * @author xefza
 * @version 1.0
 * @created 03-Apr-2018 17:46:47
 */
public class Controller {

    private JPanel buttonsPanel;
    private JButton switchClientButton;
    private JButton newOrderButton;
    private JButton setMoneyButton;
    private JButton signOutButton;
    private SwitchClientDialog switchClientDialog;
    private OrderDialog orderDialog;
    private MoneyInputDialog moneyInputDialog;


    public Controller(JFrame owner, Shop shop) {
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridBagLayout());

        newOrderButton = new JButton("Make order");
        newOrderButton.addActionListener(e -> {
            if(orderDialog == null){
                orderDialog = new OrderDialog(owner, shop);
            }
            orderDialog.setVisible(true);
        });
        buttonsPanel.add(newOrderButton, new GBC(0,0)
                .setFill(GBC.HORIZONTAL).setInsets(GBC.DEFAULT_INSETS));

        setMoneyButton = new JButton("Add money");
        setMoneyButton.addActionListener(e -> {
            if(moneyInputDialog == null){
                moneyInputDialog = new MoneyInputDialog(owner, shop);
            }
            moneyInputDialog.setVisible(true);
        });
        buttonsPanel.add(setMoneyButton, new GBC(0,1)
                .setFill(GBC.HORIZONTAL).setInsets(GBC.DEFAULT_INSETS));

        switchClientButton = new JButton("New client");
        switchClientButton.addActionListener(e -> {
            if(switchClientDialog == null){
                switchClientDialog = new SwitchClientDialog(owner, shop);
            }
            switchClientDialog.getDialog().setVisible(true);
        });
        buttonsPanel.add(switchClientButton, new GBC(0,2)
                .setFill(GBC.HORIZONTAL).setInsets(GBC.DEFAULT_INSETS));

        signOutButton = new JButton("Cancel");
        signOutButton.addActionListener(e -> owner.dispose());
        buttonsPanel.add(signOutButton, new GBC(0,3)
                .setFill(GBC.HORIZONTAL).setInsets(GBC.DEFAULT_INSETS));

    }


    public JPanel getButtonsPanel() {
        return buttonsPanel;
    }

}