package LLD.APIBillingAndUsageSystem;

import java.util.*;

public class APIBillingAndUsageSystem {

    private Map<Integer, APIUser> apiUserMap;

    public APIBillingAndUsageSystem() {
        this.apiUserMap = new HashMap<>();
    }

    public void registerUser(int userId, int freeQuota, int costPerCall) {

        APIUser apiUser = null;

        if(!this.apiUserMap.containsKey(userId)){
            apiUser = new APIUser(new ArrayList<>(), costPerCall, freeQuota, userId, 0);
        }else{
            apiUser = this.apiUserMap.get(userId);
            apiUser.setNumberOfApiCallsUnderFreeQuota(freeQuota);
            apiUser.setCostPerApiCall(costPerCall);
        }
        this.apiUserMap.put(userId, apiUser);

    }

    public void logCall(int userId, int timestamp) {
        int totalCost = 0, freeQuotas = 0, costPerCall = 0, totalCallsMade = 0;;

        if(this.apiUserMap.containsKey(userId)) {
            APIUser apiUser = this.apiUserMap.get(userId);
            List<Integer> apiCallsList = apiUser.getApiCallsList();
            apiCallsList.add(timestamp);

            if(apiUser.getApiCallsList().size() > apiUser.getNumberOfApiCallsUnderFreeQuota()){
                freeQuotas = apiUser.getNumberOfApiCallsUnderFreeQuota();
                costPerCall = apiUser.getCostPerApiCall();
                totalCallsMade = apiUser.getApiCallsList().size();
                totalCost += Math.max(0, (totalCallsMade - freeQuotas) * costPerCall);
                apiUser.setTotalCostForApiCalls(totalCost);
            }
        }
    }

    public int getUsage(int userId, int start, int end) {
        int totalApiCalls = 0;

        if(this.apiUserMap.containsKey(userId)) {
            APIUser apiUser = this.apiUserMap.get(userId);
            List<Integer> apiCallsList = apiUser.getApiCallsList();
            Collections.sort(apiCallsList);
            for (int callTiming : apiCallsList) {
                if (callTiming >= start && callTiming <= end) {
                    totalApiCalls++;
                }
            }
        }

        return totalApiCalls;
    }

    public int getBill(int userId) {
        int totalPrice = 0;

        if(this.apiUserMap.containsKey(userId)) {
            APIUser apiUser = this.apiUserMap.get(userId);
            totalPrice = apiUser.getTotalCostForApiCalls();
        }
        return totalPrice;
    }

    public static void main(String[] args) {
        APIBillingAndUsageSystem billing = new APIBillingAndUsageSystem();

        billing.registerUser(7, 0, 10);
        billing.registerUser(8, 5, 2);
        billing.logCall(7, 50);
        billing.logCall(7, 60);
        billing.logCall(8, 55);
        billing.logCall(8, 65);
        System.out.println(billing.getUsage(7, 55, 60));
        System.out.println(billing.getUsage(8, 0, 100));
        System.out.println(billing.getBill(7));
        System.out.println(billing.getBill(8));

        System.out.println();

        billing = new APIBillingAndUsageSystem();

        billing.registerUser(1, 2, 5);
        billing.logCall(1, 100);
        billing.logCall(1, 200);
        billing.logCall(1, 300);
        System.out.println(billing.getUsage(1, 100, 300));
        System.out.println(billing.getBill(1));

        System.out.println();

        billing = new APIBillingAndUsageSystem();

        billing.registerUser(5, 1, 100);
        billing.logCall(5, 10);
        billing.logCall(5, 20);
        billing.registerUser(5, 0, 50);
        billing.logCall(5, 30);

        System.out.println(billing.getBill(5));

    }
}
