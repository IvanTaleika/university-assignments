package cpp.lab2.logic;

public class IndividualClient extends Client {
    public static final String type = "Individual";

    public IndividualClient(String name, double money, Company company) {
        super(name, money, company);
    }

    @Override
    public String getType() {
        return type;
    }
}
