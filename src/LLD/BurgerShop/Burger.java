package LLD.BurgerShop;

import java.util.ArrayList;
import java.util.List;

public class Burger {

    private int id;
    private List<PattyType> pattyList;
    private List<CheeseType> cheeseList;
    private List<Topping> toppingsList;
    private BunType bunType;
    private int cost;
    private boolean isPattyAlreadyAdded;

    public Burger(int id, BunType bunType) {
        this.id = id;
        this.bunType = bunType;
        this.cost = 0;
        this.pattyList = new ArrayList<>();
        this.cheeseList = new ArrayList<>();
        this.toppingsList = new ArrayList<>();
        this.isPattyAlreadyAdded = false;
    }

    public boolean isPattyAlreadyAdded() {
        return isPattyAlreadyAdded;
    }

    public void setPattyAlreadyAdded(boolean pattyAlreadyAdded) {
        isPattyAlreadyAdded = pattyAlreadyAdded;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<PattyType> getPattyList() {
        return pattyList;
    }

    public void setPattyList(List<PattyType> pattyList) {
        this.pattyList = pattyList;
    }

    public List<CheeseType> getCheeseList() {
        return cheeseList;
    }

    public void setCheeseList(List<CheeseType> cheeseList) {
        this.cheeseList = cheeseList;
    }

    public BunType getBunType() {
        return bunType;
    }

    public void setBunType(BunType bunType) {
        this.bunType = bunType;
    }

    public List<Topping> getToppingsList() {
        return this.toppingsList;
    }

    public void setToppingsList(List<Topping> toppingsList) {
        this.toppingsList = toppingsList;
    }
}
