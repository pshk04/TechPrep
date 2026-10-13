package LLD.BackgroundJobProcessorWithRetries;

public class Job {

    private String jobId;
    private String status;
    private int backOffDelay;
    private int totalAttempts;
    private int maxRetries;

    public int getTotalAttempts() {
        return totalAttempts;
    }

    public void setTotalAttempts(int totalAttempts) {
        this.totalAttempts = totalAttempts;
    }

    public int getMaxRetries() {
        return maxRetries;
    }

    public void setMaxRetries(int maxRetries) {
        this.maxRetries = maxRetries;
    }

    public int getBackOffDelay() {
        return backOffDelay;
    }

    public void setBackOffDelay(int backOffDelay) {
        this.backOffDelay = backOffDelay;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Job(int backOffDelay, String jobId, int maxRetries, String status, int totalAttempts) {
        this.backOffDelay = backOffDelay;
        this.jobId = jobId;
        this.maxRetries = maxRetries;
        this.status = status;
        this.totalAttempts = totalAttempts;
    }
}
