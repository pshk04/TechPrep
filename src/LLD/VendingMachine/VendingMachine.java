package LLD.VendingMachine;

import LLD.VendingMachineForHotels.Product;

import java.util.HashMap;
import java.util.Map;

public class VendingMachine {

    private Map<String, Product> productsMap;
    private int currentBalance;
    private int remainder;


    public VendingMachine() {
        this.productsMap = new HashMap<>();
        this.currentBalance = 0;
        this.remainder = 0;
    }

    public void addItem(String name, int price, int quantity) {
        Product product = null;

        if(this.productsMap.containsKey(name)){
            product = this.productsMap.get(name);
            product.setQuantity(product.getQuantity() + quantity);
        }else{
            product = new Product(name, price, quantity);
        }
        this.productsMap.put(name, product);
    }

    public int insertMoney(int amount) {
        this.currentBalance += amount;
        return this.currentBalance;
    }

    public String selectItem(String name) {

        if(this.productsMap.containsKey(name)){
            Product product = this.productsMap.get(name);
            if(product.getQuantity() == 0){
                return "Out of stock";
            }
            if(this.currentBalance < product.getPrice()){
                return "Insufficient funds";
            }else{
                product.setQuantity(product.getQuantity() - 1);
                this.currentBalance -= product.getPrice();
                return product.getName();
            }

        }else{
            return "Item not found";
        }

    }

    public int getBalance() {
        return this.currentBalance;
    }

    public int refund() {
        this.remainder = this.currentBalance;
        this.currentBalance = 0;
        return this.remainder;
    }


    public static void main(String[] args) {

        VendingMachine vm = new VendingMachine();
        vm.addItem("Cola", 150, 5);
        vm.addItem("Chips", 100, 3);
        System.out.println(vm.insertMoney(200));
        System.out.println(vm.selectItem("Cola"));
        System.out.println(vm.getBalance());
        System.out.println(vm.selectItem("Chips"));
        System.out.println(vm.refund());

        System.out.println();



    }
}
