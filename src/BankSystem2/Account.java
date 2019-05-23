package BankSystem2;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Account {
    public String name;
    public int id;
    public double money;
    public List<Transaction> list;
    public int maximumOverdraftAmount;
    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void addTransaction(Transaction t){
        list.add(t);
    }

    @Override
    public String toString() {
        return ""+id+"号账户 "+name;
    }

    //用id判断是否为同一个账户
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Account(String name, int id, double money) {
        this.name = name;
        this.id = id;
        this.money = money;
        list =new ArrayList<>();
        maximumOverdraftAmount=0;
    }
    //显示账户详情
    public void showDital() {
        if(list!=null){
            System.out.println("姓名："+name+"" +"\n"+
                    "id:"+id+"" +"\n"+
                    "账户余额："+money+"" +"\n"+
                    "交易记录："+list.toString());
        }
        else {
            System.out.println("无交易信息!");
        }
        }

}
