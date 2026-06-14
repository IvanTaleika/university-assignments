package cpp.lab3.gui.manager;

import cpp.lab3.gui.utils.GBC;
import cpp.lab3.logic.Manager;

import javax.swing.*;
import java.awt.*;

public class ManagerGUI {
    public static final int MINIMUM_WIDTH = 500;
    public static final int MINIMUM_HEIGHT = 350;

    private Manager manager;
    private JFrame frame;
    private JLabel managerNameLabel;
    private JTextField managerNameField;
    private JLabel companyNameLabel;
    private JTextField companyNameField;
    private JLabel companyPlanLabel;
    private JTextArea companyPlanArea;
    private JButton okButton;
    private JButton signOutButton;
    private String currentName;
    private String currentCompanyName;
    private String currentCompanyPlan;

    public ManagerGUI(Manager manager) {
        this.manager = manager;
        frame = new JFrame("Manager page");
        frame.setMinimumSize(new Dimension(MINIMUM_WIDTH, MINIMUM_HEIGHT));
        frame.setLayout(new GridBagLayout());
        managerNameLabel = new JLabel("Your name:");
        frame.add(managerNameLabel, new GBC(0,0).setInsets(GBC.DEFAULT_INSETS)
                .setAnchor(GBC.NORTHWEST));
        managerNameField = new JTextField();
        currentName = manager.getName();
        managerNameField.setText(currentName);
        frame.add(managerNameField, new GBC(1,0,2,1)
                .setInsets(GBC.DEFAULT_INSETS).setFill(GBC.HORIZONTAL));

        companyNameLabel = new JLabel("Company name:");
        frame.add(companyNameLabel, new GBC(0,1).setInsets(GBC.DEFAULT_INSETS));
        companyNameField = new JTextField();
        currentCompanyName = manager.getCompany().getName();
        companyNameField.setText(currentCompanyName);
        companyNameField.getDocument().addUndoableEditListener(e -> checkFields());
        frame.add(companyNameField, new GBC(1,1,2,1)
                .setInsets(GBC.DEFAULT_INSETS)
                .setFill(GBC.HORIZONTAL).setWeight(100,0));

        companyPlanLabel = new JLabel("Company plan:");

        frame.add(companyPlanLabel, new GBC(0,2).setInsets(GBC.DEFAULT_INSETS)
                .setAnchor(GBC.NORTHWEST));
        currentCompanyPlan = manager.getCompany().getPlan();
        companyPlanArea = new JTextArea(currentCompanyPlan);
        companyPlanArea.getDocument().addUndoableEditListener(e -> checkFields());
        frame.add(companyPlanArea, new GBC(1,2,2,1)
                .setInsets(GBC.DEFAULT_INSETS)
                .setFill(GBC.BOTH).setWeight(100,100));

        okButton = new JButton("Ok");
        okButton.addActionListener(e -> {
            currentName = managerNameField.getText();
            manager.setName(currentName);
            currentCompanyName = companyNameField.getText();
            manager.changeCompanyName(currentCompanyName);
            currentCompanyPlan = companyPlanArea.getText();
            manager.changeCompanyPlan(currentCompanyPlan);
            frame.dispose();
        });
        frame.add(okButton, new GBC(1,3).setInsets(GBC.DEFAULT_INSETS)
                                        .setAnchor(GBC.EAST).setWeight(100,0));

        signOutButton = new JButton("Cancel");
        signOutButton.addActionListener(e -> {
            managerNameField.setText(currentName);
            companyNameField.setText(currentCompanyName);
            companyPlanArea.setText(currentCompanyPlan);
            frame.dispose();
        });
        frame.add(signOutButton, new GBC(2,3).setInsets(GBC.DEFAULT_INSETS));
    }

    private void checkFields() {
        if(companyNameField.getText().trim().isEmpty() ||
                companyPlanArea.getText().trim().isEmpty()) {
            okButton.setEnabled(false);
        } else {
            okButton.setEnabled(true);
        }
    }

    public JFrame getFrame(){
        return frame;
    }
}
