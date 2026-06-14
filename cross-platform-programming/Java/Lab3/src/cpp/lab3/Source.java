package cpp.lab3;

import cpp.lab3.gui.client.main.ClientGUI;
import cpp.lab3.gui.main.UserChooserGUI;
import cpp.lab3.logic.Company;
import cpp.lab3.logic.Manager;
import cpp.lab3.logic.Shop;

import javax.swing.*;
import java.awt.*;

public class Source {
    public static void main(String[] args) {
        Company company = new Company();
        company.setManager(new Manager("Arsen Venger"));
        Shop shop = new Shop(company);
        EventQueue.invokeLater(() -> {
            UserChooserGUI frame = new UserChooserGUI(shop);
            frame.getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getFrame().setVisible(true);
        });
    }
}
