package LLD.CreditTrackerWithExpirations;

public class CreditTrackerService {
    public static void main(String[] args) {
        CreditTracker tracker = new CreditTracker();

        tracker.addCredits(1, 100, 10);
        tracker.addCredits(2, 50, 5);
        System.out.println(tracker.getBalance(3));

        System.out.println(tracker.spend(4, 70));
        System.out.println(tracker.getBalance(6));
        System.out.println(tracker.getBalance(11));
        System.out.println(tracker.spend(12, 10));
        System.out.println();

        tracker = new CreditTracker();

        tracker.addCredits(1, 5, 100);
        tracker.addCredits(2, 5, 100);
        System.out.println(tracker.spend(3, 7));
        System.out.println(tracker.spend(4, 5));
        System.out.println(tracker.getBalance(5));
        System.out.println();
        tracker = new CreditTracker();

        tracker.addCredits(1, 50, 20);
        tracker.addCredits(2, 50, 10);
        tracker.addCredits(3, 50, 30);
        System.out.println(tracker.getBalance(4));
        System.out.println(tracker.spend(5, 120));
        System.out.println(tracker.getBalance(6));
        System.out.println();
//
        tracker = new CreditTracker();

        tracker.addCredits(1, 100, 50);
        tracker.addCredits(2, 200, 10);
        System.out.println(tracker.spend(3, 150));
        tracker.addCredits(4, 50, 100);
        System.out.println(tracker.getBalance(5));
        System.out.println(tracker.spend(6, 1000));
        System.out.println(tracker.getBalance(7));
        System.out.println();
    }
}
