package cpp.lab2.gui.main;


import cpp.lab2.gui.dialogs.MoneyInputDialog;
import cpp.lab2.gui.dialogs.OrderDialog;
import cpp.lab2.gui.dialogs.SwitchClientDialog;
import cpp.lab2.logic.Shop;

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
    private SwitchClientDialog switchClientDialog;
    private OrderDialog orderDialog;
    private MoneyInputDialog moneyInputDialog;

    public Controller(JFrame owner, Shop shop) {
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.PAGE_AXIS));

        buttonsPanel.add(Box.createVerticalGlue());
        newOrderButton = new JButton("Make order");
        newOrderButton.addActionListener(e -> {
            if(orderDialog == null){
                orderDialog = new OrderDialog(owner, shop);
            }
            orderDialog.setVisible(true);
        });
        buttonsPanel.add(newOrderButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        setMoneyButton = new JButton("Add money");
        setMoneyButton.addActionListener(e -> {
            if(moneyInputDialog == null){
                moneyInputDialog = new MoneyInputDialog(owner, shop);
            }
            moneyInputDialog.setVisible(true);
        });
        buttonsPanel.add(setMoneyButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        switchClientButton = new JButton("New client");
        switchClientButton.addActionListener(e -> {
            if(switchClientDialog == null){
                switchClientDialog = new SwitchClientDialog(owner, shop);
            }
            switchClientDialog.setVisible(true);
        });
        buttonsPanel.add(switchClientButton);
        buttonsPanel.add(Box.createVerticalGlue());

        setMoneyButton.setMaximumSize(newOrderButton.getMaximumSize());
        switchClientButton.setMaximumSize(newOrderButton.getMaximumSize());
    }


    public JPanel getButtonsPanel() {
        return buttonsPanel;
    }

}