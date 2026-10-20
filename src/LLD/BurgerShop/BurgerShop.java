package LLD.BurgerShop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BurgerShop {

    private int burgerId;
    private int revenue;
    private Map<Integer, Burger> burgersMap;
    private boolean isOrderInProgress;

    public BurgerShop() {
        this.burgerId = 1;
        this.revenue = 0;
        this.burgersMap = new HashMap<>();
        this.isOrderInProgress = false;
    }

    public void startOrder(String bun) {

        BunType bunType = BunType.valueOf(bun);
        Burger burger = new Burger(this.burgerId, bunType);
        this.burgersMap.put(this.burgerId, burger);
        this.isOrderInProgress = true;
        burger.setCost(burger.getCost() + 3);
    }

    public void addPatty(String patty) {
        if(this.isOrderInProgress) {
            Burger burger = this.burgersMap.get(this.burgerId);
            List<PattyType> pattyList = burger.getPattyList();
            PattyType pattyType = PattyType.valueOf(patty);
            pattyList.add(pattyType);
            burger.setPattyList(pattyList);
            if(!burger.isPattyAlreadyAdded()) {
                burger.setCost(burger.getCost() + 5);
            }
            burger.setPattyAlreadyAdded(true);
        }

    }

    public void addCheese(String cheese) {
        if(this.isOrderInProgress) {
            Burger burger = this.burgersMap.get(this.burgerId);
            CheeseType cheeseType = CheeseType.valueOf(cheese);
            List<CheeseType> cheeseList = burger.getCheeseList();
            cheeseList.add(cheeseType);
            burger.setCheeseList(cheeseList);
            burger.setCost(burger.getCost() + 2);
        }
    }

    public void addTopping(String topping) {
        if(this.isOrderInProgress) {
            Burger burger = this.burgersMap.get(this.burgerId);
            Topping toppingType = Topping.valueOf(topping);
            List<Topping> toppingsList = burger.getToppingsList();
            toppingsList.add(toppingType);
            burger.setCost(burger.getCost() + 1);
        }

    }

    public String placeOrder() {

        StringBuilder orderString = new StringBuilder();
        // 'bun|patty|cheese|topping1,topping2|$price'
        if(this.isOrderInProgress){
            Burger burger = this.burgersMap.get(this.burgerId);
            orderString.append(burger.getBunType().name()).append("|");

            if(burger.getPattyList().size() > 0) {
                orderString.append(burger.getPattyList().get(burger.getPattyList().size() - 1).name()).append("|");
            }else{
                orderString.append("|");
            }

            if(burger.getCheeseList().size() > 0) {
                orderString.append(burger.getCheeseList().get(burger.getCheeseList().size() - 1).name()).append("|");
            }else{
                orderString.append("|");
            }

            if(burger.getToppingsList().size() > 0) {
                for(Topping topping : burger.getToppingsList()) {
                    orderString.append(topping.name()).append(",");
                }
                orderString = new StringBuilder(orderString.toString().substring(0,orderString.length() - 1));
                orderString.append("|");
            }else{
                orderString.append("|");
            }

            orderString.append("$"+ burger.getCost());
            this.revenue += burger.getCost();
        }
        this.isOrderInProgress = false;
        this.burgerId++;
        return orderString.toString();
    }

    public int getRevenue() {
        return this.revenue;
    }

    public static void main(String[] args) {
        BurgerShop shop = new BurgerShop();

        shop.startOrder("sesame");
        shop.addPatty("beef");
        shop.addCheese("cheddar");
        shop.addTopping("lettuce");
        shop.addTopping("tomato");
        System.out.println(shop.placeOrder());
        System.out.println(shop.getRevenue());

        System.out.println();

        shop = new BurgerShop();

        shop.addPatty("beef");
        shop.startOrder("brioche");
        System.out.println(shop.placeOrder());
        shop.startOrder("plain");
        shop.addCheese("swiss");
        System.out.println(shop.placeOrder());
        System.out.println(shop.getRevenue());

        System.out.println();

        shop = new BurgerShop();

        shop.startOrder("wheat");
        shop.addPatty("chicken");
        shop.addPatty("beef");
        System.out.println(shop.placeOrder());

    }

}
