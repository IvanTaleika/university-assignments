package cpp.lab3.logic;

public class EntityClient extends Client {
    public static final String type = "Entity";

    public EntityClient(String name, double money, Company company) {
        super(name, money, company);
    }

    @Override
    public String getType() {
        return type;
    }
}
