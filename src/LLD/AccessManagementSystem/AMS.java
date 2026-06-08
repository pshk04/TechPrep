package LLD.AccessManagementSystem;

public class AMS {

    public static void main(String[] args) {
        AccessManager ams = new AccessManager();
        ams.createRole("admin");
        ams.createRole("user");
        ams.grantPermission("admin", "delete_user");
        ams.grantPermission("admin", "ban_user");
        ams.grantPermission("user", "read_posts");
        ams.assignRole("alice", "admin");
        ams.assignRole("bob", "user");
        System.out.println(ams.hasPermission("alice", "delete_user"));
        System.out.println(ams.hasPermission("bob", "delete_user"));
        System.out.println(ams.hasPermission("alice", "read_posts"));
        ams.revokeRole("alice", "admin");
        System.out.println(ams.hasPermission("alice", "delete_user"));
    }
}
