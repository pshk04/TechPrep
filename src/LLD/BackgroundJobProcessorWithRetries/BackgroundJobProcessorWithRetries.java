package LLD.BackgroundJobProcessorWithRetries;

import java.util.*;

public class BackgroundJobProcessorWithRetries {

    private Queue<Job> jobsQueue;
    private int baseDelay;
    private Map<String, Job> jobsMap;

    public BackgroundJobProcessorWithRetries(int baseDelay) {
        this.baseDelay = baseDelay;
        this.jobsQueue = new LinkedList<>();
        this.jobsMap = new HashMap<>();
    }

    public boolean addJob(String jobId, int maxRetries) {

        if(!this.jobsMap.containsKey(jobId)){
            Job newJob = new Job(0, jobId, maxRetries,"QUEUED",0);
            jobsQueue.offer(newJob);
            this.jobsMap.put(jobId, newJob);
            return true;
        }
        return false;
    }

    public String processNext() {
        if(!this.jobsQueue.isEmpty()){
            Job nextJob = this.jobsQueue.poll();
            nextJob.setStatus("PROCESSING");
            this.jobsMap.put(nextJob.getJobId(), nextJob);
            return nextJob.getJobId();
        }
        return "";
    }

    public boolean completeJob(String jobId) {
        if(this.jobsMap.containsKey(jobId)) {
            Job job = this.jobsMap.get(jobId);
            if (job.getStatus().equals("PROCESSING")) {
                job.setStatus("COMPLETED");
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    public String failJob(String jobId) {
        int updatedBackOffTime = 0, currentAttempt = 0;

        if(this.jobsMap.containsKey(jobId)) {
            Job job = this.jobsMap.get(jobId);
            if (job.getStatus().equals("PROCESSING")) {
                if(job.getMaxRetries() > 0 && job.getTotalAttempts() < job.getMaxRetries()){
                    job.setStatus("QUEUED");
                    currentAttempt = job.getTotalAttempts() + 1;
                    updatedBackOffTime = this.baseDelay * ((int) Math.pow(2, (currentAttempt - 1)));
                    job.setBackOffDelay(updatedBackOffTime);
                    job.setTotalAttempts(currentAttempt);
                    this.jobsQueue.offer(job);
                    return "QUEUED";
                }else{
                    job.setStatus("EXHAUSTED");
                    return "EXHAUSTED";
                }
            }else{
                return "ERROR";
            }
        }
        return "ERROR";
    }

    public String getJobState(String jobId) {
        if(this.jobsMap.containsKey(jobId)){
            return this.jobsMap.get(jobId).getStatus();
        }
        return "NOT_FOUND";
    }

    public int getBackoffDelay(String jobId) {
        if(this.jobsMap.containsKey(jobId)){
            return this.jobsMap.get(jobId).getBackOffDelay();
        }
        return -1;
    }

    public int getQueueSize() {
        if(!this.jobsQueue.isEmpty()){
            return this.jobsQueue.size();
        }
        return 0;
    }

    public int getAttempt(String jobId) {
        if(this.jobsMap.containsKey(jobId)){
            return this.jobsMap.get(jobId).getTotalAttempts();
        }
        return -1;
    }

    public static void main(String[] args) {
        BackgroundJobProcessorWithRetries proc = new BackgroundJobProcessorWithRetries(1);

        System.out.println(proc.addJob("jobA", 2)); // True
        System.out.println(proc.addJob("jobB", 1)); // True
        System.out.println(proc.getQueueSize()); // 2
        System.out.println(proc.processNext()); // jobA
        System.out.println(proc.getJobState("jobA")); // PROCESSING
        System.out.println(proc.failJob("jobA")); // QUEUED
        System.out.println(proc.getBackoffDelay("jobA")); //
        System.out.println(proc.getAttempt("jobA"));
        System.out.println(proc.getQueueSize());
        System.out.println(proc.processNext());
        System.out.println(proc.failJob("jobA"));
        System.out.println(proc.getBackoffDelay("jobA"));
        System.out.println(proc.getAttempt("jobA"));
        System.out.println(proc.processNext());
        System.out.println(proc.failJob("jobA"));
        System.out.println(proc.getJobState("jobA"));
        System.out.println(proc.processNext());
        System.out.println(proc.completeJob("jobB"));
        System.out.println(proc.getJobState("jobB"));

        System.out.println();

        proc = new BackgroundJobProcessorWithRetries(5);

        System.out.println(proc.processNext());
        System.out.println(proc.addJob("j1", 0));
        System.out.println(proc.addJob("j2", 3));
        System.out.println(proc.processNext());
        System.out.println(proc.completeJob("j1"));
        System.out.println(proc.getJobState("j1"));
        System.out.println(proc.processNext());
        System.out.println(proc.completeJob("j2"));
        System.out.println(proc.getQueueSize());

        System.out.println();

        proc = new BackgroundJobProcessorWithRetries(1);

        System.out.println(proc.addJob("jobA", 2)); // true
        System.out.println(proc.addJob("jobB", 1)); // true
        System.out.println(proc.getQueueSize()); // 2
        System.out.println(proc.processNext()); // jobA

        System.out.println(proc.getJobState("jobA")); //PROCESSING
        System.out.println(proc.failJob("jobA")); // QUEUED
        System.out.println(proc.getBackoffDelay("jobA")); // 1
        System.out.println(proc.getAttempt("jobA")); // 1
        System.out.println(proc.getQueueSize()); // 2

        System.out.println(proc.processNext()); // jobB
        System.out.println(proc.failJob("jobA")); // ERROR
        System.out.println(proc.getBackoffDelay("jobA")); // 1
        System.out.println(proc.getAttempt("jobA")); // 1
        System.out.println(proc.processNext()); // jobA

        System.out.println(proc.failJob("jobA")); // QUEUED
        System.out.println(proc.getJobState("jobA")); // QUEUED
        System.out.println(proc.processNext()); // jobA
        System.out.println(proc.failJob("jobA")); // EXHAUSTED
        System.out.println(proc.getJobState("jobA"));

        proc = new BackgroundJobProcessorWithRetries(3);

        System.out.println(proc.addJob("x", 4)); // true
        System.out.println(proc.processNext()); // x
        System.out.println(proc.failJob("x")); // QUEUED
        System.out.println(proc.getBackoffDelay("x")); // 3
        System.out.println(proc.processNext()); // x
        System.out.println(proc.failJob("x")); // QUEUED
        System.out.println(proc.getBackoffDelay("x")); // 6
        System.out.println(proc.processNext()); // x
        System.out.println(proc.failJob("x")); // QUEUED
        System.out.println(proc.getBackoffDelay("x")); //
        System.out.println(proc.processNext()); //
        System.out.println(proc.failJob("x"));
        System.out.println(proc.getBackoffDelay("x"));
        System.out.println(proc.getJobState("x"));

    }
}
