package BankSystem2;


import java.time.LocalDate;

public class Transaction {
    private LocalDate date;
    private Type type;
    private double amount;
    private double balance;
    private String description;

    public double getAmount() {
        return amount;
    }

    public Transaction(Type type, double amount) {
        this.type = type;
        this.amount = amount;
        this.balance=balance;
       }

    public void setType(Type type) {
        this.type = type;
    }

    //存钱
    public void deposit(Account acc){
        date=LocalDate.now();
        balance=acc.getMoney()+amount;
       acc.setMoney(balance);
       description=acc.toString()+type.toString()+amount+"元，当前账户余额:"+balance;
    }
    //取钱
    public void withdraw(Account acc){
        if(acc.money-amount<acc.maximumOverdraftAmount){
            System.out.println("超过最大透支金额，无法"+type.description+"！");
            return;
        }
        date=LocalDate.now();
        balance=acc.getMoney()-amount;
        acc.setMoney(balance);
        description=acc.toString()+type.toString()+amount+"元，当前账户余额:"+balance;
    }
    //转账
    public void transfer(Account acc1, Account acc2){
        if(acc1.money-amount<acc1.maximumOverdraftAmount){
            System.out.println("超过最大透支金额，无法"+type.description+"！");
            return;
        }
        date=LocalDate.now();
        balance=acc1.getMoney()-amount;
        acc1.setMoney(balance);
        description=acc1.toString()+"向 "+acc2.toString()+type.toString()+amount+"元，当前账户余额:"+balance;
        acc2.setMoney(acc2.getMoney()+amount);
    }
    //返回交易信息
    @Override
    public String toString() {
        return "交易信息{" +
                "时间=" + date +
                ", 类型=" + type +
                ", 金额=" + amount +
                ", 当前余额=" + balance +
                ", 描述='" + description + '\'' +
                '}';
    }
    //交易类型枚举
    enum Type{
        W("取款"),
        D("存款"),
        T("转账"),
        A("贷款给个人"),
        B("贷款给银行");

        String description;
        @Override
        public String toString() {
            return description;
        }

        Type(String description){
            this.description=description;
        }

    }
}
