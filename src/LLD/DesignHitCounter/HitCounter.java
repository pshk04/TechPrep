package LLD.DesignHitCounter;

import java.util.Map;
import java.util.TreeMap;

public class HitCounter {

    private Map<Integer, Integer> hitsCounterMap;

    public HitCounter() {
        this.hitsCounterMap = new TreeMap<>();
    }

    public void hit(int timestamp) {
        if(!this.hitsCounterMap.containsKey(timestamp)){
            this.hitsCounterMap.put(timestamp, 1);
        }else{
            this.hitsCounterMap.put(timestamp, this.hitsCounterMap.get(timestamp) + 1);
        }
    }

    public int getHits(int timestamp) {
        int hitsCount = 0, minimumTimeStamp = 0;

        if(timestamp <= 300){
            for(Map.Entry<Integer, Integer> entry : this.hitsCounterMap.entrySet()){
                int hitTimeStamp = entry.getKey();
                if(hitTimeStamp <= 300){
                    hitsCount += entry.getValue();
                }
            }
        }else{
            minimumTimeStamp = timestamp - 300;
            for(Map.Entry<Integer, Integer> entry : this.hitsCounterMap.entrySet()){
                int hitTimeStamp = entry.getKey();
                if(hitTimeStamp > minimumTimeStamp){
                    hitsCount += entry.getValue();
                }
            }
        }
        return hitsCount;
    }
}
