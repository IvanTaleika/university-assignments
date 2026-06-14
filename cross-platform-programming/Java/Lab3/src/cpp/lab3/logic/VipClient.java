package cpp.lab3.logic;

//TODO make only fast orders

public class VipClient extends Client {

    public static final String type = "VIP";

    public VipClient(String name, double money, Company company) {
        super(name, money, company);
    }

    @Override
    public String getType() {
        return type;
    }
}
