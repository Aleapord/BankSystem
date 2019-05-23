package BankSystem2;

public class BusinessAccount extends Account {
    public BusinessAccount(String name, int id, double maoney) {
        super(name, id, maoney);
        this.maximumOverdraftAmount=100000;
    }
}
