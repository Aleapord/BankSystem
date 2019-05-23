package BankSystem2;

public class VIPAccount extends Account {
    public VIPAccount(String name, int id, double maoney) {
        super(name, id, maoney);
        this.maximumOverdraftAmount=10000;
    }
}
