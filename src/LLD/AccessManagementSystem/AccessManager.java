package LLD.AccessManagementSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccessManager {

    private Map<String, List<String>> userRoleMap;
    private Map<String, List<String>> rolePermissionsMap;

    public AccessManager(){
        this.userRoleMap = new HashMap<>();
        this.rolePermissionsMap = new HashMap<>();
    }

    public void createRole(String name) {
        List<String> permissions = new ArrayList<>();
        this.rolePermissionsMap.put(name, permissions);
    }

    public void grantPermission(String role, String permission) {
        List<String> permissions = this.rolePermissionsMap.get(role);
        permissions.add(permission);
        this.rolePermissionsMap.put(role, permissions);
    }

    public void assignRole(String user, String role) {
        List<String> roles;
        if(this.userRoleMap.containsKey(user)) {
            roles = this.userRoleMap.get(user);
            roles.add(role);
        }else{
            roles = new ArrayList<>();
            roles.add(role);
        }
        this.userRoleMap.put(user, roles);
    }

    public void revokeRole(String user, String role) {
        if(this.userRoleMap.containsKey(user) && this.userRoleMap.get(user).contains(role)){
            List<String> roles = this.userRoleMap.get(user);
            roles.remove(role);
            this.userRoleMap.put(user, roles);
        }
    }

    public boolean hasPermission(String user, String permission) {
        if(this.userRoleMap.containsKey(user)){
            List<String> roles = this.userRoleMap.get(user);
            for(String role : roles){
                if(this.rolePermissionsMap.containsKey(role) && this.rolePermissionsMap.get(role).contains(permission)){
                    return true;
                }
            }
        }
        return false;
    }
}
