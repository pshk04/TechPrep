package LLD.DesignLogParser;

public class DesignLogParser {

    public static void main(String[] args) {
        LogParser parser = new LogParser();

//        parser.addLog("100:INFO:User logged in");
//        parser.addLog("200:ERROR:Null pointer exception");
//        parser.addLog("300:INFO:Page loaded");
//        parser.addLog("400:WARN:Disk space low");
//        parser.addLog("500:ERROR:Connection timeout");
//        System.out.println(parser.getLogsByLevel("ERROR"));
//        System.out.println(parser.getLogsByTimeRange(150, 400));
//        System.out.println(parser.getEventCount("INFO"));
//        System.out.println(parser.getEventCount("WARN"));
//
//        System.out.println();
//
//        parser = new LogParser();
//
//        System.out.println(parser.getLogsByLevel("INFO"));
//        System.out.println(parser.getEventCount("ERROR"));
//        parser.addLog("50:INFO:Start");
//        System.out.println(parser.getLogsByLevel("INFO"));
//        System.out.println(parser.getLogsByTimeRange(1, 100));
//
//        System.out.println();
//
//        parser = new LogParser();
//        parser.addLog("100:INFO:a");
//        parser.addLog("100:WARN:b");
//        parser.addLog("100:ERROR:c");
//        parser.addLog("200:INFO:d");
//        System.out.println(parser.getLogsByTimeRange(100, 100));
//        System.out.println(parser.getLogsByTimeRange(200, 200));

        System.out.println();

//        parser = new LogParser();
        parser.addLog("10:INFO:a");
        parser.addLog("20:WARN:b");
        parser.addLog("30:ERROR:c");

        System.out.println(parser.getLogsByTimeRange(10, 30));
        System.out.println(parser.getLogsByTimeRange(15, 25));
        System.out.println(parser.getLogsByTimeRange(1, 5));

    }
}
