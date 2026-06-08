package LLD.BankingApplication2;

public class BankingApplicationService {
    public static void main(String[] args) {
        BankingSystem bank = new BankingSystem();

        System.out.println(bank.createAccount(1, "a"));
        System.out.println(bank.createAccount(2, "b"));
        System.out.println(bank.createAccount(3, "c"));
        System.out.println(bank.deposit(4, "a", 1000));
        System.out.println(bank.deposit(5, "b", 1000));
        System.out.println(bank.deposit(6, "c", 1000));
        System.out.println(bank.transfer(7, "a", "b", 300));
        System.out.println(bank.transfer(8, "b", "c", 300));
        System.out.println(bank.transfer(9, "c", "a", 300));
        System.out.println(bank.topSpenders(10, 3));
        System.out.println();

        bank = new BankingSystem();

        System.out.println(bank.topSpenders(1, 5));
        System.out.println(bank.createAccount(2, "solo"));
        System.out.println(bank.topSpenders(3, 5));
        System.out.println();

        bank = new BankingSystem();

        System.out.println(bank.createAccount(1, "u1"));
        System.out.println(bank.createAccount(2, "u2"));
        System.out.println(bank.createAccount(3, "u3"));
        System.out.println(bank.deposit(4, "u1", 500));
        System.out.println(bank.deposit(5, "u2", 500));
        System.out.println(bank.transfer(6, "u1", "u3", 200));
        System.out.println(bank.transfer(8, "u2", "u3", 300));
        System.out.println(bank.topSpenders(8, 1));
        System.out.println(bank.topSpenders(9, 5));
        System.out.println();

        bank = new BankingSystem();

        System.out.println(bank.createAccount(1, "z"));
        System.out.println(bank.createAccount(2, "y"));
        System.out.println(bank.createAccount(3, "x"));
        System.out.println(bank.createAccount(4, "w"));
        System.out.println(bank.deposit(5, "z", 1000));
        System.out.println(bank.deposit(6, "y", 1000));
        System.out.println(bank.deposit(7, "x", 1000));
        System.out.println(bank.deposit(8, "w", 1000));
        System.out.println(bank.transfer(9, "z", "y", 100));
        System.out.println(bank.transfer(10, "y", "x", 100));
        System.out.println(bank.transfer(11, "x", "w", 100));
        System.out.println(bank.topSpenders(12, 4));
        System.out.println();
    }
}
