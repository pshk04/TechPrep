package LLD.AccessManagementSystem;

import java.util.HashSet;
import java.util.Set;

public class Role {

    private String name;
    private Set<String> permissions;

    public Role(String name){
        this.name = name;
        this.permissions = new HashSet<>();
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public String getName() {
        return name;
    }
}
