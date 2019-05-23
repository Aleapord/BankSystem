package BankSystem2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BankEntity {
    private static double totlalAssets=1000000;
    private static List<Account> accounts=new ArrayList<>();
    private static Account currentAccount=null;
    private static boolean loginFlag = false;
    private static boolean exitFlag =true;


    private static void mainMenu() {
        System.out.println("请选择你的操作：" + "\n" +
                "1.通过id登陆账户" + "\n" +
                "2.创建新账号"+"\n" +
                "3.退出");
    }
    private static void menuForVipAccount() {
        System.out.println("请选择你的操作：" + "\n" +
                "1.存款" + "\n" +
                "2.取款" + "\n" +
                "3.转账" + "\n" +
                "4.退出" + "\n" +
                "5.查询账户信息" +"\n" +
                "6.退出登录");
    }
    private static void menuForBusinessAccount(){
        System.out.println("请选择你的操作：" + "\n" +
                "1.存款" + "\n" +
                "2.取款" + "\n" +
                "3.转账" + "\n" +
                "4.贷款给个人"+"\n"+
                "5.贷款给银行"+"\n"+
                "6.退出登陆" + "\n" +
                "7.查询账户信息" +"\n" +
                "8.退出");
    }
    private static Account findAccountById(int id, List<Account> accounts) {
        Account account = null;
        for (Account acc :
                accounts) {
            if (acc.equals(new Account("", id, 0))) {
                account = acc;
            }
        }
        if (account == null)
            System.out.println("查无此人！");
        return account;

    }
    private static void operationsForVipAccount(){

        Scanner scanner=new Scanner(System.in);

            System.out.println();
            System.out.println("当前账户:" + currentAccount.toString());
            System.out.println("账户类型：个人账户");
            menuForVipAccount();
            switch (scanner.nextInt()) {
                case 1: {
                    System.out.println("请输入要存款的金额：");
                    Transaction t = new Transaction(Transaction.Type.D, scanner.nextInt());
                    t.deposit(currentAccount);
                    totlalAssets+=t.getAmount();
                    currentAccount.addTransaction(t);
                    break;
                }
                case 2: {
                    System.out.println("请输入要取款的金额：");
                    Transaction t = new Transaction(Transaction.Type.W, scanner.nextInt());
                    t.withdraw(currentAccount);
                    totlalAssets-=t.getAmount();
                    currentAccount.addTransaction(t);
                    break;
                }
                case 3: {
                    System.out.println("请输入要转账的账户ID和金额：");
                    Account secAccount= findAccountById(scanner.nextInt(),accounts);
                    double amount=new Scanner(System.in).nextDouble();
                    Transaction t = new Transaction(Transaction.Type.T, amount);
                    t.transfer(currentAccount,secAccount);
                    currentAccount.addTransaction(t);
                    secAccount.addTransaction(t);
                    break;
                }
                case 4: {
                    System.out.println("感谢使用，再见！");
                    exitFlag=false;
                }
                case 5: {
                    currentAccount.showDital();
                    break;
                }
                case 6: {
                    loginFlag =false;
                }

            }


    }
    private static void operationsForBusinessAccount(){

        Scanner scanner=new Scanner(System.in);
        System.out.println();
        System.out.println("当前账户:" + currentAccount.toString());
        System.out.println("账户类型：企业客户");

        menuForBusinessAccount();
        switch (scanner.nextInt()) {
            case 1: {
                System.out.println("请输入要存款的金额：");
                Transaction t = new Transaction(Transaction.Type.D, scanner.nextInt());
                t.deposit(currentAccount);
                totlalAssets+=t.getAmount();
                currentAccount.addTransaction(t);
                break;
            }
            case 2: {
                System.out.println("请输入要取款的金额：");
                Transaction t = new Transaction(Transaction.Type.W, scanner.nextInt());
                t.withdraw(currentAccount);
                totlalAssets-=t.getAmount();
                currentAccount.addTransaction(t);
                break;
            }
            case 3: {
                System.out.println("请输入要贷款的账户ID和金额：");
                Account secAccount= findAccountById(scanner.nextInt(),accounts);
                double amount=new Scanner(System.in).nextDouble();
                Transaction t = new Transaction(Transaction.Type.T, amount);
                t.setType(Transaction.Type.A);
                t.transfer(currentAccount,secAccount);
                currentAccount.addTransaction(t);
                secAccount.addTransaction(t);
                break;
            }
            case 4: {
                System.out.println("请输入要贷款给银行的金额：");
                Transaction t = new Transaction(Transaction.Type.D, scanner.nextInt());
                t.setType(Transaction.Type.B);
                t.deposit(currentAccount);
                totlalAssets+=t.getAmount();
                currentAccount.addTransaction(t);
                break;
            }
            case 5: {
                System.out.println("请输入要转账的账户ID和金额：");
                Account secAccount= findAccountById(scanner.nextInt(),accounts);
                double amount=new Scanner(System.in).nextDouble();
                Transaction t = new Transaction(Transaction.Type.T, amount);
                t.transfer(currentAccount,secAccount);
                currentAccount.addTransaction(t);
                secAccount.addTransaction(t);
                break;
            }
            case 6: {
                currentAccount.showDital();
                break;
            }
            case 7:{
                loginFlag =false;
            }
            case 8:{
                System.out.println("感谢使用，再见！");
                exitFlag=false;
            }

        }

    }
    public static void main(String... args) {
        accounts.add(new Account("曾庶强", 1, 100000));
        accounts.add(new Account("晏庆明", 2, 1000));
        accounts.add(new Account("熊一睿", 3, 10000));
        accounts.add(new Account("王翔茂", 4, 20000));
        Scanner scanner=new Scanner(System.in);
        Account currentAccount = new Account(" ",0,0);
        while (exitFlag) {
            mainMenu();
            switch (scanner.nextInt()) {
                case 1: {
                    System.out.println("请输入你的账户Id进行登陆：");
                    currentAccount = findAccountById(scanner.nextInt(), accounts);
                    break;
                }
                case 2: {
                    System.out.println("请依次输入：姓名，ID");
                    accounts.add(currentAccount = new Account(new Scanner(System.in).nextLine(), scanner.nextInt(), 0));
                    break;
                }
                case 3:{
                    exitFlag=false;
                }
            }
            loginFlag = true;
            while (loginFlag){
                if(currentAccount.getClass().toString().equals("BankSystem2.Account.VIPAccount")) {
                    operationsForVipAccount();


                }
                else {
                    operationsForBusinessAccount();
                }

           }
        }
    }
}

