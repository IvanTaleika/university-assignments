package cpp.lab2;


import java.util.Vector;

/**
 * @author xefza
 * @version 1.0
 * @created 03-Apr-2018 17:46:47
 */
public interface Subscriber {

    /**
     * @param orders
     */
    public void updateClientOrders(Vector<Vector<Object>> orders);
    public void addClientOrders(Vector<Object> orderInfo);

    public void updateClientMoney(double money);
    public void updateClientName(String name);
    public void updateClientType(String clientType);

}