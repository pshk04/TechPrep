package LLD.ActiveUsersSystem;

public class ActiveUsers {
    public static void main(String[] args) {
        ActiveUsersTracker tracker = new ActiveUsersTracker(1);

        tracker.addLog(1, 10);
        tracker.addLog(2, 20);
        tracker.addLog(3, 30);
        System.out.println("Total Number of active users: "+tracker.activeUsers(70));
        tracker.addLog(4, 80);
        System.out.println("Total Number of active users: "+tracker.activeUsers(80));
        System.out.println("List of active users Id: "+tracker.getActiveUserIds(80));

    }
}
