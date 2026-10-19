package LLD.BankingSystem;

import java.util.List;

public class User {

    private int id;
    private String name;
    private List<Account> userAccountsList;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Account> getUserAccountsList() {
        return userAccountsList;
    }

    public void setUserAccountsList(List<Account> userAccountsList) {
        this.userAccountsList = userAccountsList;
    }

    public User(List<Account> userAccountsList, int id, String name) {
        this.userAccountsList = userAccountsList;
        this.id = id;
        this.name = name;
    }
}
