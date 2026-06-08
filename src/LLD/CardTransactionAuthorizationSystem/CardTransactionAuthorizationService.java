package LLD.CardTransactionAuthorizationSystem;

public class CardTransactionAuthorizationService {
    public static void main(String[] args) {
        CardTransactionAuthorizer auth = new CardTransactionAuthorizer();

        System.out.println(auth.createAccount("A1", 100));
//        auth.printBankAccounts();
        System.out.println(auth.authorize("A1", 30, 10));
        System.out.println(auth.authorize("A1", 80, 20));
        System.out.println(auth.authorize("A1", 50, 30));
        System.out.println(auth.getBalance("A1"));

        System.out.println();

        auth = new CardTransactionAuthorizer();

        System.out.println(auth.createAccount("B1", 1000));
        System.out.println(auth.authorize("B1", 10, 100));
        System.out.println(auth.authorize("B1", 10, 110));
        System.out.println(auth.authorize("B1", 10, 150));
        System.out.println(auth.authorize("B1", 10, 200));
        System.out.println(auth.authorize("B1", 10, 300));
        System.out.println(auth.getBalance("B1"));

    }
}
