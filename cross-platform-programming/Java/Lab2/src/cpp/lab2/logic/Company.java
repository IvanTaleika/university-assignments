package cpp.lab2.logic;

/**
 * @author xefza
 * @version 1.0
 * @created 03-Apr-2018 17:46:47
 */
public class Company {

    public static final double LETTER_COST = 2.5;
    private int idMemory;

    public Company() {
        idMemory = 0;
    }

    /**
     * @param name
     * @param amount
     */
    public double countPrice(String name, int amount) {
        double price = name.length() * amount * LETTER_COST;
        return price;
    }

    /**
     * @param order
     */
    public void performOrder(Order order) {
        order.setReady();
    }

    /**
     * @param name
     * @param amount
     */
    public Order receiveOrder(String name, int amount) {
        Order order = new Order(name, amount, idMemory, countPrice(name, amount));
        idMemory++;
        performOrder(order);
        return order;
    }
}