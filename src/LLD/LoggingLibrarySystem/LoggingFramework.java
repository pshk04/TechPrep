package LLD.LoggingLibrarySystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoggingFramework {

    private Map<String, String> appenderTypeMap;
    private String minLogLevel;
    private List<String> logLevelsList;
    private Map<String, List<String>> levelMessageMap;
    private Map<String, List<String>> levelAppenderMap;
    private Map<String, List<String>> logLevelAppenderSubscriptionCountMap;

    public LoggingFramework() {
        this.logLevelsList = new ArrayList<>();
        this.logLevelsList.add("DEBUG");
        this.logLevelsList.add("INFO");
        this.logLevelsList.add("ERROR");

        this.appenderTypeMap = new HashMap<>();
        this.logLevelAppenderSubscriptionCountMap = new HashMap<>();
        this.levelMessageMap = new HashMap<>();
        this.levelAppenderMap = new HashMap<>();
    }

    public boolean verifyAppender(String appender){

        List<String> appenderTypesList = new ArrayList<>();
        appenderTypesList.add("CONSOLE");
        appenderTypesList.add("FILE");
        appenderTypesList.add("DATABASE");

        return appenderTypesList.contains(appender);
    }

    public boolean verifyLogLevel(String logLevel){

        List<String> logLevelList = new ArrayList<>();
        logLevelList.add("DEBUG");
        logLevelList.add("INFO");
        logLevelList.add("ERROR");

        return logLevelList.contains(logLevel);
    }

    public boolean addAppender(String name, String appenderType) {
        if(!this.appenderTypeMap.containsKey(name) && this.verifyAppender(appenderType)){
            this.appenderTypeMap.put(name, appenderType);
            List<String> appendersList;
            for(String log : this.logLevelsList){
                if(this.logLevelAppenderSubscriptionCountMap.containsKey(log)){
                    appendersList = this.logLevelAppenderSubscriptionCountMap.get(log);
                }else{
                    appendersList = new ArrayList<>();
                }
                appendersList.add(name);
                this.logLevelAppenderSubscriptionCountMap.put(log,appendersList);
            }
            return true;
        }
        return false;
    }

    public boolean removeAppender(String name) {
        if(this.appenderTypeMap.containsKey(name)){
           this.appenderTypeMap.remove(name);
           return true;
        }
        return false;
    }

    public boolean setLogLevel(String level) {
        if(this.verifyLogLevel(level)) {
            this.minLogLevel = level;
            return true;
        }
        return false;
    }

    public int log(String level, String message) {
        List<String> messageList;

        if(!this.levelMessageMap.isEmpty() && this.levelMessageMap.containsKey(level)){
            messageList = this.levelMessageMap.get(level);
        }else{
            messageList = new ArrayList<>();
        }
        messageList.add(message);
        this.levelMessageMap.put(level, messageList);

        if(this.logLevelsList.contains(level) && this.minLogLevel != null && this.logLevelsList.indexOf(level) >= this.logLevelsList.indexOf(this.minLogLevel)) {
            return this.logLevelAppenderSubscriptionCountMap.get(level).size();
        }else{
            return 0;
        }
    }

    public List<String> getLogs(String name) {
        List<String> allAppenderMessagesList = new ArrayList<>();
        if(!this.levelMessageMap.isEmpty() && this.levelMessageMap.containsKey(name)){
            for(String logLevel : this.levelMessageMap.keySet()) {
                allAppenderMessagesList.add("[" + logLevel +"] " + this.levelMessageMap.get(logLevel));
            }
        }
        return allAppenderMessagesList;
    }

    public String getAppenderType(String name) {
        if(!this.appenderTypeMap.isEmpty() && this.appenderTypeMap.containsKey(name)){
            return this.appenderTypeMap.get(name);
        }
        return "";
    }

    public int getAppenderCount() {
        return this.appenderTypeMap.size();
    }
}
