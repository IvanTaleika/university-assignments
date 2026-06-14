package cpp.lab2;

import cpp.lab2.gui.main.ShopFrame;
import cpp.lab2.logic.Company;
import cpp.lab2.logic.Shop;

import javax.swing.*;
import java.awt.*;

public class Source {
    public static void main(String[] args) {
        Company company = new Company();
        Shop shop = new Shop(company);
        EventQueue.invokeLater(() -> {
            ShopFrame frame = new ShopFrame(shop);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
