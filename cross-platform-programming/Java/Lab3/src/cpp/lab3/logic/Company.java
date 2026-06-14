package cpp.lab3.logic;

/**
 * @author xefza
 * @version 1.0
 * @created 03-Apr-2018 17:46:47
 */
public class Company {

    public static final double LETTER_COST = 2.5;
    private double money;
    private String name;
    private String plan;
    private Manager manager;
    private int idMemory;

    public Company() {
        money = 0;
        idMemory = 0;
        name = "Cool Company";
        plan = "Cool Plan";
        money = 0;
    }


    public Order receiveOrder(String name, int amount) {
        Order order = new Order(name, amount, idMemory, countPrice(name, amount));
        idMemory++;
        performOrder(order);
        return order;
    }

    public double countPrice(String name, int amount) {
        double price = name.length() * amount * LETTER_COST;
        return price;
    }

    public void receivePayment(double payment) {
        money += payment;
    }

    private void performOrder(Order order) {
        order.setReady();
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
        manager.setCompany(this);
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}