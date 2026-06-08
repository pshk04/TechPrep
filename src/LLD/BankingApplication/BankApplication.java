package LLD.BankingApplication;

public class BankApplication {
    public static void main(String[] args) {
        Bank bank = new Bank();
        bank.createAccount(100);
        bank.createAccount(50);
        bank.deposit(1, 200);
        bank.withdraw(2, 30);
        bank.transfer(1, 2, 100);
        System.out.println("Balance in bank account 1: "+bank.getBalance(1));
        System.out.println("Balance in bank account 2: "+bank.getBalance(2));
    }
}
