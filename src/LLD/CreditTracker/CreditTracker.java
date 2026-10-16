package LLD.CreditTracker;

import java.util.*;

public class CreditTracker {
    private Map<String, CreditAccount> creditAccountsMap;

    public CreditTracker() {
        this.creditAccountsMap = new HashMap<>();
    }

    public boolean createAccount(String userId, int initialBalance) {

        if(!this.creditAccountsMap.containsKey(userId)){
            CreditAccount creditAccount = new CreditAccount(initialBalance, userId);
            this.creditAccountsMap.put(userId, creditAccount);
            return true;
        }
        return false;
    }

    public boolean addCredits(String userId, int amount) {

        if(amount < 0){
            return false;
        }else{
            if(this.creditAccountsMap.containsKey(userId)){
                CreditAccount creditAccount = this.creditAccountsMap.get(userId);
                creditAccount.setBalance(creditAccount.getBalance() + amount);
                return true;
            }else{
                return false;
            }
        }
    }

    public boolean spendCredits(String userId, int amount) {

        if(this.creditAccountsMap.containsKey(userId)){
            CreditAccount creditAccount = this.creditAccountsMap.get(userId);
            if(creditAccount.getBalance() >= amount) {
                creditAccount.setBalance(creditAccount.getBalance() - amount);
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    public boolean transfer(String fromUserId, String toUserId, int amount) {
        CreditAccount creditAccount = null;

        if(this.creditAccountsMap.containsKey(fromUserId) && (this.creditAccountsMap.containsKey(toUserId))){
            if(this.creditAccountsMap.get(fromUserId).getBalance() >= amount) {
                creditAccount = this.creditAccountsMap.get(fromUserId);
                creditAccount.setBalance(creditAccount.getBalance() - amount);
                creditAccount = this.creditAccountsMap.get(toUserId);
                creditAccount.setBalance(creditAccount.getBalance() + amount);
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    public int getBalance(String userId) {
        if(this.creditAccountsMap.containsKey(userId)){
            return this.creditAccountsMap.get(userId).getBalance();
        }
        return -1;
    }

    public List<String> getTopK(int k) {
        List<String> topKUserIdList = new ArrayList<>();
        PriorityQueue<String[]> topKUsersMaxHeap = new PriorityQueue<>((a,b) -> {
            int maxSpenderComparator = Integer.compare(Integer.parseInt(b[1]), Integer.parseInt(a[1]));
            if(maxSpenderComparator == 0){
                return a[0].compareTo(b[0]);
            }
            return maxSpenderComparator;
        });

        for(Map.Entry<String, CreditAccount> entry : this.creditAccountsMap.entrySet()){
            CreditAccount creditAccount = entry.getValue();
            topKUsersMaxHeap.offer(new String[] {creditAccount.getId(), String.valueOf(creditAccount.getBalance())});
        }

        while(!topKUsersMaxHeap.isEmpty() && k > 0){
            topKUserIdList.add(topKUsersMaxHeap.poll()[0]);
            k--;
        }

        return topKUserIdList;
    }

    public static void main(String[] args) {
        CreditTracker tracker = new CreditTracker();

        System.out.println(tracker.createAccount("alice", 100));
        System.out.println(tracker.createAccount("bob", 50));
        System.out.println(tracker.addCredits("alice", 25));
        System.out.println(tracker.spendCredits("bob", 20));
        System.out.println(tracker.getBalance("alice"));
        System.out.println(tracker.getBalance("bob"));
        System.out.println(tracker.transfer("alice", "bob", 40));
        System.out.println(tracker.getTopK(2));

        System.out.println();


        tracker = new CreditTracker();

        System.out.println(tracker.createAccount("user1", 10));
        System.out.println(tracker.createAccount("user1", 5));
        System.out.println(tracker.spendCredits("user1", 100));
        System.out.println(tracker.transfer("user1", "ghost", 5));
        System.out.println(tracker.getBalance("ghost"));
        System.out.println(tracker.addCredits("user2", 50));
        System.out.println(tracker.createAccount("user2", 200));
        System.out.println(tracker.getTopK(3));

        System.out.println();

    }
}
