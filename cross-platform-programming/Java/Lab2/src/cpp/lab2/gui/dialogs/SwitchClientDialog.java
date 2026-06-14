package cpp.lab2.gui.dialogs;

import cpp.lab2.gui.GBC;
import cpp.lab2.logic.Shop;

import javax.swing.*;
import java.awt.*;

public class SwitchClientDialog {
    private Shop shop;
    private JDialog dialog;
    private JTextField nameField;
    private JTextField moneyField;
    private JComboBox<String> typeComboBox;
    public static final int DEFAULT_INSETS = 5;
    public static final int MINIMUM_WIDTH = 500;
    public static final int MINIMUM_HEIGHT = 200;
    public static final String ClientTypes[] = {"Individual", "Entity"};

    public SwitchClientDialog(JFrame owner, Shop shop) {
        this.shop = shop;
        dialog = new JDialog(owner);
        dialog.setModal(true);
        dialog.setTitle("New client");
        dialog.setMinimumSize(new Dimension(MINIMUM_WIDTH, MINIMUM_HEIGHT));
        GridBagLayout layout = new GridBagLayout();
        dialog.setLayout(layout);

        dialog.add(new JLabel("New client:"), new GBC(0, 0,
                2, 1).setWeight(100, 0)
                     .setAnchor(GBC.WEST).setInsets(DEFAULT_INSETS));

        dialog.add(new JLabel("Name:"), new GBC(0, 1));

        nameField = new JTextField();
        dialog.add(nameField, new GBC(1, 1, 1, 1)
                .setWeight(100, 0).setFill(GBC.BOTH)
                .setInsets(DEFAULT_INSETS));

        dialog.add(new JLabel("Money:"), new GBC(0, 2));

        moneyField = new JTextField();
        dialog.add(moneyField, new GBC(1, 2, 1, 1)
                .setWeight(100, 0).setFill(GBC.BOTH)
                .setInsets(DEFAULT_INSETS));

        dialog.add(new JLabel("Type:"), new GBC(0, 3));

        typeComboBox = new JComboBox<>(ClientTypes);
        dialog.add(typeComboBox, new GBC(1, 3, 1, 1)
                .setWeight(100, 0).setFill(GBC.BOTH)
                .setInsets(DEFAULT_INSETS));

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dialog.setVisible(false));

        dialog.add(cancelButton, new GBC(0, 4).setInsets(5));
        JButton okButton = new JButton("Buy");
        okButton.addActionListener(e -> okButtonListener());
        dialog.add(okButton, new GBC(1, 4).setAnchor(GBC.EAST)
                                          .setInsets(DEFAULT_INSETS));
    }

    private void okButtonListener() {
        String name = nameField.getText();
        if(name.isEmpty()) {
            JOptionPane.showConfirmDialog(dialog, "Invalid name!", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
            return;
        }
        String moneyStringValue = moneyField.getText();
        double moneyDoubleValue;
        try {
            moneyDoubleValue = Double.parseDouble(moneyStringValue);
        } catch(NumberFormatException e1) {
            JOptionPane.showConfirmDialog(dialog, "Invalid amount of money!", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
            return;
        }
        String type = (String) typeComboBox.getSelectedItem();
        shop.newClient(type, name, moneyDoubleValue);
        nameField.setText("");
        moneyField.setText("");
        dialog.setVisible(false);
    }

    public void setVisible(boolean b) {
        dialog.setVisible(b);
    }
}
