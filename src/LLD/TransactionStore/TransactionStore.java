package LLD.TransactionStore;

import java.util.HashMap;
import java.util.Map;

public class TransactionStore {

    private Map<Integer, Integer> transactionsMap;

    public TransactionStore() {
        this.transactionsMap = new HashMap<>();
    }

    public void add(int id, int amount) {
        this.transactionsMap.put(id, amount);
    }

    public int get(int id) {
        if(this.transactionsMap.containsKey(id)){
            return this.transactionsMap.get(id);
        }
        return -1;
    }

    public int count() {
        return this.transactionsMap.size();
    }

    public void deleteAll() {
        this.transactionsMap.clear();
    }

    public static void main(String[] args) {
        TransactionStore store = new TransactionStore();

        store.add(1, 100);
        store.add(2, 250);
        System.out.println(store.count());
        store.deleteAll();
        System.out.println(store.count());
        System.out.println(store.get(1));

        System.out.println();

        store = new TransactionStore();

        store.deleteAll();
        store.add(5, -75);
        System.out.println(store.get(5));
        store.deleteAll();
        store.add(9, 1000);
        System.out.println(store.count());
    }
}
