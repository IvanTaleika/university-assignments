package cpp.lab2.logic;


/**
 * @author xefza
 * @version 1.0
 * @created 03-Apr-2018 17:46:47
 */
abstract public class Client {

    private double money;
    private String name;
    private Company company;

    public Client(String name, double money, Company company) {
        this.name = name;
        this.money = money;
        this.company = company;
    }


    public double getMoney() {
        return money;
    }

    public String getName() {
        return name;
    }

    /**
     * @param name
     * @param amount
     */
    public Order makeOrder(String name, int amount) {
        Order order = company.receiveOrder(name, amount);
        pay(order.getPrice());
        return order;
    }

    /**
     * @param cost
     */
    public void pay(double cost) {
        money -= cost;
    }

    /**
     * @param money
     */
    public void setMoney(double money) {
        this.money = money;
    }

    public abstract String getType();

}