import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApacheLogParser {
    public static void main(String[] args) {
        String log = "1.1.1.1 - - [01/Jan/2024:00:00:00 +0000] \"GET / HTTP/1.1\" 200 0\n2.2.2.2 - - [01/Jan/2024:00:00:01 +0000] \"GET / HTTP/1.1\" 200 0\n3.3.3.3 - - [01/Jan/2024:00:00:02 +0000] \"GET / HTTP/1.1\" 200 0\"\n";

        System.out.println("The IP address that is most frequent is: "+findMostFrequentIPAddress(log));
    }

    public static String findMostFrequentIPAddress(String logs){
        String[] logLines = logs.split("\\R");
        String ipAddress = "";
        Map<String, List<Integer>> ipAddressIndexMap = new HashMap<>();
        List<Integer> indexList;
        int maxFrequency = Integer.MIN_VALUE, currentFrequency = 0, minEarliestOccurence = -1, currentearliestOccurence = -1;


        for(int i = 0; i < logLines.length; i++){
            ipAddress = logLines[i].split(" ")[0];
            System.out.println(ipAddress);
            if(ipAddressIndexMap.containsKey(ipAddress)){
                indexList = ipAddressIndexMap.get(ipAddress);
                indexList.add(i);
            }else{
                indexList = new ArrayList<>();
                indexList.add(i);
            }
            ipAddressIndexMap.put(ipAddress, indexList);
        }
        for(Map.Entry<String, List<Integer>> entry : ipAddressIndexMap.entrySet()){
            currentFrequency = entry.getValue().size();
            if(currentFrequency >= maxFrequency){
                maxFrequency = Math.max(maxFrequency, currentFrequency);
                if(minEarliestOccurence != -1) {
                    currentearliestOccurence = entry.getValue().get(0);
                    if(currentearliestOccurence < minEarliestOccurence) {
                        minEarliestOccurence = currentearliestOccurence;
                        ipAddress = entry.getKey();
                    }
                }else{
                    minEarliestOccurence = entry.getValue().get(0);
                    ipAddress = entry.getKey();
                }

            }
        }

        return ipAddress;
    }
}
