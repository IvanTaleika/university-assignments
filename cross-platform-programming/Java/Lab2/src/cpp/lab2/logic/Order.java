package cpp.lab2.logic;

/**
 * @author xefza
 * @version 1.0
 * @created 03-Apr-2018 17:46:47
 */
public class Order {


    public enum State {
        IN_PROGRESS,
        READY
    }

    private int amount;
    private int id;
    private String name;
    private double price;
    private State state;

    public Order(String name, int amount, int id, double price) {
        this.name = name;
        this.amount = amount;
        this.id = id;
        this.price = price;
        state = State.IN_PROGRESS;
    }

    public int getAmount() {
        return amount;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setReady() {
        state = State.READY;
    }
}