package cpp.lab3.logic;

/**
 * @author xefza
 * @version 1.0
 * @created 03-Apr-2018 17:46:47
 */
public class Shop {
    private ClientBase clientBase;
    private Company shopOwnerCompany;

    public Shop(Company company) {
        shopOwnerCompany = company;
        clientBase = new ClientBase();
    }

    /**
     * @param name
     * @param money
     */
    public void newClient(String type, String name, double money) {
        Client client = null;
        switch(type) {
            case "Individual":
                client = new IndividualClient(name, money, shopOwnerCompany);
                break;
            case "Entity":
                client = new EntityClient(name, money, shopOwnerCompany);
                break;
            case "VIP":
                client = new VipClient(name, money, shopOwnerCompany);
                break;
        }
        clientBase.addNewClient(client);
        clientBase.switchClient(client);
    }

    /**
     * @param orderName
     * @param amount
     */
    public void makeOrder(String orderName, int amount) {
        Order order = clientBase.getCurrentClient().makeOrder(orderName, amount);
        clientBase.addOrderInfo(order.getId(), order.getName(), order.getAmount(),
                order.getPrice());
        clientBase.updateCurrentClientMoney(clientBase.getCurrentClient().getMoney());
    }

    public ClientBase getClientBase() {
        return clientBase;
    }

    public Company getShopOwnerCompany() {
        return shopOwnerCompany;
    }
}