package Heaps;

import java.util.*;

public class MeetingRoomsIII {

    public static void main(String[] args) {
        int[][][] meetings = {
                {
                        {0,10},
                        {1,5},
                        {2,7},
                        {3,4}
                },
                {
                        {1,20},
                        {2,10},
                        {3,5},
                        {4,9},
                        {6,8}
                },
                {
                        {18,19},
                        {3,12},
                        {17,19},
                        {2,13},
                        {7,10}
                }
        };

        int[] totalRooms = {2,3,4};

        for(int i = 0 ; i < totalRooms.length; i++){
            System.out.println("The meeting room that held maximum meetings: "+mostBooked(totalRooms[i], meetings[i]));
        }
    }

    public static int mostBooked(int n, int[][] meetings) {
        Arrays.sort(meetings, (a,b) -> Integer.compare(a[0], b[0]));
        PriorityQueue<int[]> meetingRoomMinHeap = new PriorityQueue<>((a,b) -> {
            int maxMeetingsConductedComparator = Integer.compare(b[1],a[1]);
            if(maxMeetingsConductedComparator == 0){
                return Integer.compare(a[0], b[0]);
            }
            return maxMeetingsConductedComparator;
        });

        Map<Integer, List<int[]>> meetingsCountMap = new HashMap<>();
        Map<Integer, Integer> roomAvailabilityMap = new HashMap<>();
        List<int[]> meetingsHeldList;
        int[] newMeetingSchedule, nextRoomAvailableForMeeting;
        int begin = 0, newBegin = 0, newEnd = 0, lastMeetingsBegin = 0, lastMeetingsEnd = 0, roomAvailable = 0;
        boolean roomAssignedForAMeeting = false;

        for(int[] meeting : meetings){
            begin = meeting[0];
            for(int i = 0; i < n; i++){
                if(meetingsCountMap.containsKey(i)){
                    meetingsHeldList = meetingsCountMap.get(i);
                    int[] lastMeetingHeld = meetingsHeldList.get(meetingsHeldList.size() - 1);
                    lastMeetingsBegin = lastMeetingHeld[0];
                    lastMeetingsEnd = lastMeetingHeld[1];
                    if(begin >= lastMeetingsBegin && begin <= lastMeetingsEnd || begin < lastMeetingsBegin){
                        continue;
                    }else{
                        meetingsHeldList.add(meeting);
                        meetingsCountMap.put(i, meetingsHeldList);
                        roomAvailabilityMap.put(i,meeting[1]);
                        roomAssignedForAMeeting = true;
                        break;
                    }
                }else{
                    meetingsHeldList = new ArrayList<>();
                    meetingsHeldList.add(meeting);
                    meetingsCountMap.put(i, meetingsHeldList);
                    roomAvailabilityMap.put(i,meeting[1]);
                    roomAssignedForAMeeting = true;
                    break;
                }
            }
            if(!roomAssignedForAMeeting){
                nextRoomAvailableForMeeting = findRoomAvailableForMeeting(roomAvailabilityMap);
                roomAvailable = nextRoomAvailableForMeeting[0];
                newBegin = nextRoomAvailableForMeeting[1];
                newEnd = newBegin + (meeting[1] - meeting[0]);
                newMeetingSchedule = new int[]{newBegin, newEnd};
                meetingsHeldList = meetingsCountMap.get(roomAvailable);
                meetingsHeldList.add(newMeetingSchedule);
                roomAvailabilityMap.put(roomAvailable, newEnd);
            }
            roomAssignedForAMeeting = false;
        }

        for(Map.Entry<Integer, List<int[]>> entry : meetingsCountMap.entrySet()){
            meetingRoomMinHeap.offer(new int[]{entry.getKey(), entry.getValue().size()});
        }

        return meetingRoomMinHeap.poll()[0];
    }

    public static int[] findRoomAvailableForMeeting(Map<Integer, Integer> roomAvailabilityMap){

        int currentEnd = 0, minimumEnd = Integer.MAX_VALUE, nextRoomAvailable = 0;
        int[] earliestRoomAvailable;

        for(Map.Entry<Integer, Integer> entry : roomAvailabilityMap.entrySet()){
            currentEnd = entry.getValue();
            if(currentEnd < minimumEnd){
                nextRoomAvailable = entry.getKey();
                minimumEnd = currentEnd;
            }
        }
        earliestRoomAvailable = new int[]{nextRoomAvailable, minimumEnd};
        return earliestRoomAvailable;
    }
}
