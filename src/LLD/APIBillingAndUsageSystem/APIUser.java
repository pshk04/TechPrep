package LLD.APIBillingAndUsageSystem;

import java.util.List;

public class APIUser {

    private int userId;
    private int numberOfApiCallsUnderFreeQuota;
    private int costPerApiCall;
    private int totalCostForApiCalls;
    private List<Integer> apiCallsList;

    public int getTotalCostForApiCalls() {
        return totalCostForApiCalls;
    }

    public void setTotalCostForApiCalls(int totalCostForApiCalls) {
        this.totalCostForApiCalls = totalCostForApiCalls;
    }

    public List<Integer> getApiCallsList() {
        return apiCallsList;
    }

    public void setApiCallsList(List<Integer> apiCallsList) {
        this.apiCallsList = apiCallsList;
    }

    public int getCostPerApiCall() {
        return costPerApiCall;
    }

    public void setCostPerApiCall(int costPerApiCall) {
        this.costPerApiCall = costPerApiCall;
    }

    public int getNumberOfApiCallsUnderFreeQuota() {
        return numberOfApiCallsUnderFreeQuota;
    }

    public void setNumberOfApiCallsUnderFreeQuota(int numberOfApiCallsUnderFreeQuota) {
        this.numberOfApiCallsUnderFreeQuota = numberOfApiCallsUnderFreeQuota;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public APIUser(List<Integer> apiCallsList, int costPerApiCall, int numberOfApiCallsUnderFreeQuota, int userId, int totalCostForApiCalls) {
        this.apiCallsList = apiCallsList;
        this.costPerApiCall = costPerApiCall;
        this.numberOfApiCallsUnderFreeQuota = numberOfApiCallsUnderFreeQuota;
        this.userId = userId;
        this.totalCostForApiCalls = totalCostForApiCalls;
    }
}
