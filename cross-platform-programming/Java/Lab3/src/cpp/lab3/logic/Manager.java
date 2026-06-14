package cpp.lab3.logic;

public class Manager {
    private String name;
    private Company company;

    public Manager(String name) {
        this.name = name;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void changeCompanyName(String name) {
        company.setName(name);
    }

    public void changeCompanyPlan(String plan) {
        company.setPlan(plan);
    }
}
