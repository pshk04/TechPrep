package LLD.LoggingLibrarySystem;

public class LoggingLibraryService {

    public static void main(String[] args) {
        LoggingFramework logger = new LoggingFramework();

        System.out.println(logger.addAppender("console1", "CONSOLE"));
        System.out.println(logger.addAppender("file1", "FILE"));
        System.out.println(logger.log("DEBUG", "Starting app"));
        System.out.println(logger.log("INFO", "User logged in"));
        System.out.println(logger.log("ERROR", "Null pointer"));
        System.out.println(logger.getLogs("console1"));
        System.out.println(logger.getLogs("file1"));
        System.out.println(logger.setLogLevel("INFO"));
        System.out.println(logger.log("DEBUG", "This should be filtered"));
        System.out.println(logger.log("ERROR", "Disk full"));
        System.out.println(logger.getLogs("console1"));
        System.out.println(logger.getAppenderCount());
    }
}
