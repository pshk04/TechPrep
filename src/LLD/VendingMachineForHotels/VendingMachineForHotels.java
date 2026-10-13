package LLD.VendingMachineForHotels;

import java.util.HashMap;
import java.util.Map;

public class VendingMachineForHotels {

    private Map<String, Product> productsMap;
    private int currentBalance;
    private int remainder;

    public VendingMachineForHotels() {
        this.productsMap = new HashMap<>();
        this.currentBalance = 0;
        this.remainder = 0;
    }

    public void addProduct(String name, int price, int quantity) {
        int currentQuantity = 0;

        if(this.productsMap.containsKey(name)){
            Product product = this.productsMap.get(name);
            currentQuantity = this.productsMap.get(name).getQuantity() + quantity;
            product.setQuantity(currentQuantity);
            product.setPrice(price);
            this.productsMap.put(name, product);
        }else{
            Product product = new Product(name, price, quantity);
            this.productsMap.put(name, product);
        }
    }

    public boolean restock(String name, int quantity) {
        int currentQuantity = 0;

        if(this.productsMap.containsKey(name)){
            Product product = this.productsMap.get(name);
            currentQuantity = this.productsMap.get(name).getQuantity() + quantity;
            product.setQuantity(currentQuantity);
            this.productsMap.put(name, product);
            return true;
        }
        return false;
    }

    public int insertCoin(int coin) {

        this.currentBalance += coin;

        return this.currentBalance;
    }

    public String selectProduct(String name) {
        int productPrice = 0;

        if(this.productsMap.containsKey(name)){
            Product product = this.productsMap.get(name);
            if(product.getQuantity() > 0) {
                productPrice = product.getPrice();
                if(this.currentBalance >= productPrice) {
                    this.remainder = this.currentBalance - productPrice;
                    this.currentBalance = 0;
                    product.setQuantity(product.getQuantity() - 1);
                    return "DISPENSED: "+this.remainder;
                }else{
                    return "INSUFFICIENT_FUNDS";
                }
            }else{
                return "OUT_OF_STOCK";
            }
        }
        return "OUT_OF_STOCK";
    }

    public int cancel() {
        this.remainder = this.currentBalance;
        this.currentBalance = 0;
        return this.remainder;
    }

    public int getStock(String name) {
        if(this.productsMap.containsKey(name)){
            return this.productsMap.get(name).getQuantity();
        }
        return -1;
    }

    public static void main(String[] args) {

        VendingMachineForHotels vm = new VendingMachineForHotels();

        vm.addProduct("coke", 150, 2);
        vm.addProduct("chips", 200, 1);
        System.out.println(vm.insertCoin(100));
        System.out.println(vm.insertCoin(100));
        System.out.println(vm.selectProduct("coke"));
        System.out.println(vm.getStock("coke"));
        System.out.println(vm.selectProduct("water"));
        System.out.println(vm.cancel());

        System.out.println();

        vm = new VendingMachineForHotels();

        vm.addProduct("soap", 300, 1);
        System.out.println(vm.insertCoin(100));
        System.out.println(vm.selectProduct("soap"));
        System.out.println(vm.insertCoin(250));
        System.out.println(vm.selectProduct("soap"));
        System.out.println(vm.restock("soap", 5));
        System.out.println(vm.getStock("soap"));
        System.out.println(vm.selectProduct("towel"));
    }
}
