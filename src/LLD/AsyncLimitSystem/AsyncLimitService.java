package LLD.AsyncLimitSystem;

public class AsyncLimitService {

    public static void main(String[] args) {
        AsyncLimit limiter = new AsyncLimit(1);
        System.out.println(limiter.submit(10));
        System.out.println(limiter.submit(20));
        System.out.println(limiter.submit(30));
        System.out.println(limiter.submit(40));
        System.out.println("Currently pending count is: "+limiter.pendingCount());
        limiter.complete(10);
        System.out.println("Currently running tasks are: "+limiter.runningTasks());
        System.out.println("Currently pending count is: "+limiter.pendingCount());
        limiter.complete(20);
        System.out.println("Currently running tasks are: "+limiter.runningTasks());
    }
}
