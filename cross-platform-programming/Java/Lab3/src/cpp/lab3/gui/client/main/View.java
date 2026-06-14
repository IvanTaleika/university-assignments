package cpp.lab3.gui.client.main;

import cpp.lab3.Subscriber;
import cpp.lab3.gui.utils.GBC;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Arrays;
import java.util.Vector;

/**
 * @author xefza
 * @version 1.0
 * @created 03-Apr-2018 17:46:47
 */
public class View implements Subscriber {

    private JPanel infoPanel;
    private JLabel moneyLabel;
    private JLabel nameLabel;
    private JLabel typeLabel;

    private JPanel ordersTablePanel;
    private JTable ordersTable;
    private DefaultTableModel ordersTableModel;
    private JScrollPane tableScrollPane;

    public static final int DEFAULT_INSETS = 5;
    public final Vector<String> columnIdentifiers = new Vector<>
            (Arrays.asList("id", "name", "amount", "cost"));

    public View() {
        buildInfoPanel();
        buildOrdersTablePanel();
    }

    private void buildInfoPanel(){
        infoPanel = new JPanel();
        BoxLayout infoPanelLayout = new BoxLayout(infoPanel, BoxLayout.LINE_AXIS);
        infoPanel.setLayout(infoPanelLayout);

        infoPanel.add(new JLabel("Client:"));
        infoPanel.add(Box.createRigidArea(new Dimension(DEFAULT_INSETS, 0)));

        typeLabel = new JLabel("");
        infoPanel.add(typeLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(DEFAULT_INSETS, 0)));

        nameLabel = new JLabel("");
        infoPanel.add(nameLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(DEFAULT_INSETS, 0)));

        moneyLabel = new JLabel("");
        infoPanel.add(moneyLabel);
        infoPanel.add(Box.createHorizontalGlue());
    }

    private void buildOrdersTablePanel(){
        ordersTablePanel = new JPanel();
        GridBagLayout ordersTablePanelLayout = new GridBagLayout();
        ordersTablePanel.setLayout(ordersTablePanelLayout);

        ordersTablePanel.add(new JLabel("Your orders"), new GBC(0,0)
                .setInsets(DEFAULT_INSETS));



        ordersTable = new JTable() {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };
        ordersTable.setFillsViewportHeight(true);
        tableScrollPane = new JScrollPane(ordersTable);
        ordersTablePanel.add(tableScrollPane, new GBC(0,1).setFill(GBC.BOTH)
                .setWeight(100,100));

        ordersTableModel = (DefaultTableModel) ordersTable.getModel();
        ordersTableModel.setColumnIdentifiers(columnIdentifiers);
    }


    public JPanel getInfoPanel() {
        return infoPanel;
    }

    public JPanel getOrdersTablePanel() {
        return ordersTablePanel;
    }


    @Override
    public void updateClientOrders(Vector<Vector<Object>> orders) {
        ordersTableModel.setDataVector(orders, columnIdentifiers);
        ordersTableModel.fireTableDataChanged();
    }

    @Override
    public void addClientOrders(Vector<Object> orderInfo) {
        ordersTableModel.fireTableDataChanged();
    }

    @Override
    public void updateClientMoney(double money) {
        moneyLabel.setText(Double.toString(money) + "$");

    }

    @Override
    public void updateClientName(String name) {
        nameLabel.setText(name + ",");
    }

    @Override
    public void updateClientType(String clientType) {
        typeLabel.setText(clientType + ",");
    }

}