package cpp.lab2.gui.dialogs;

import cpp.lab2.gui.GBC;
import cpp.lab2.logic.Shop;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class OrderDialog {
    private JDialog dialog;
    private Shop shop;
    private JTextField nameField;
    private JTextField amountField;
    private JButton okButton;
    private JButton cancelButton;
    private JButton calculatePriceButton;
    private JLabel priceLabel;
    public static final int DEFAULT_INSETS = 5;
    public static final int MINIMUM_WIDTH = 500;
    public static final int MINIMUM_HEIGHT = 200;

    public OrderDialog(JFrame owner, Shop shop) {
        this.shop = shop;
        dialog = new JDialog(owner, true);
        dialog.setTitle("New order");
        dialog.setMinimumSize(new Dimension(MINIMUM_WIDTH, MINIMUM_HEIGHT));
        GridBagLayout layout = new GridBagLayout();
        dialog.setLayout(layout);

        dialog.add(new JLabel("New order"), new GBC(0, 0, 3, 1)
                .setWeight(100, 0)
                .setAnchor(GBC.CENTER).setInsets(DEFAULT_INSETS));

        dialog.add(new JLabel("Name:"), new GBC(0, 1));

        nameField = new JTextField();
        nameField.getDocument().addDocumentListener(new textFieldChangeListener());
        dialog.add(nameField, new GBC(1, 1, 2, 1)
                .setWeight(100, 0)
                .setFill(GBC.BOTH).setInsets(DEFAULT_INSETS));

        dialog.add(new JLabel("Amount:"), new GBC(0, 2));

        amountField = new JTextField();
        amountField.getDocument().addDocumentListener(new textFieldChangeListener());
        dialog.add(amountField, new GBC(1, 2, 2, 1)
                .setWeight(100, 0)
                .setFill(GBC.BOTH).setInsets(DEFAULT_INSETS));

        dialog.add(new JLabel("Price:"), new GBC(0, 3)
                .setInsets(DEFAULT_INSETS));

        priceLabel = new JLabel();
        dialog.add(priceLabel, new GBC(1,3).setAnchor(GBC.WEST)
                                           .setInsets(DEFAULT_INSETS));

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dialog.setVisible(false));
        dialog.add(cancelButton, new GBC(0, 4).setInsets(5));

        calculatePriceButton = new JButton("Calculate price");
        calculatePriceButton.addActionListener(e -> cancelButtonListener());
        dialog.add(calculatePriceButton, new GBC(1, 4)
                .setAnchor(GBC.EAST).setInsets(DEFAULT_INSETS));

        okButton = new JButton("Add");
        okButton.addActionListener(e -> okButtonListener());
        okButton.setEnabled(false);
        dialog.add(okButton, new GBC(2, 4).setAnchor(GBC.EAST)
                                          .setInsets(DEFAULT_INSETS));
    }

    private boolean checkFields(){
        if(nameField.getText().isEmpty()) {
            JOptionPane.showConfirmDialog(dialog, "Invalid name!",
                    "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
            return false;
        }
        int amount;
        try {
            amount = Integer.parseInt(amountField.getText());
        } catch (NumberFormatException e1) {
            JOptionPane.showConfirmDialog(dialog, "Invalid amount!", "Error",
                    JOptionPane.DEFAULT_OPTION ,JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if(amount <= 0) {
            JOptionPane.showConfirmDialog(dialog, "Invalid amount!", "Error",
                    JOptionPane.DEFAULT_OPTION ,JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    private void okButtonListener() {
        if(!checkFields()) {
            return;
        }
        shop.makeOrder(nameField.getText(),
                Integer.parseInt(amountField.getText()));
        nameField.setText("");
        amountField.setText("");
        okButton.setEnabled(false);
        priceLabel.setText("");
        dialog.setVisible(false);
    }

    private void cancelButtonListener() {
        if(!checkFields()) {
            return;
        }
        double price = shop.getShopOwnerCompany().countPrice(nameField.getText(),
                Integer.parseInt(amountField.getText()));
        priceLabel.setText(Double.toString(price));
        if (price < shop.getClientBase().getCurrentClient().getMoney()) {
            okButton.setEnabled(true);
        } else {
            okButton.setEnabled(false);
        }
    }

    private class textFieldChangeListener implements DocumentListener{

        @Override
        public void insertUpdate(DocumentEvent e) {
            setDefaultStage();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            setDefaultStage();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            setDefaultStage();

        }

        public void setDefaultStage(){
            okButton.setEnabled(false);
            priceLabel.setText("");
        }
    }

    public void setVisible(boolean b) {
        dialog.setVisible(b);
    }
}
