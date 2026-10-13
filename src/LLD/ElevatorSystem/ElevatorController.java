package LLD.ElevatorSystem;

import java.util.*;

class ElevatorController {

    private int numElevators;
    private int minFloor;
    private int maxFloor;
    private Map<Integer, List<String>> elevatorStatusMap;
    private PriorityQueue<int[]> elevatorAvailabilityMinHeap;

    public ElevatorController(int numElevators, int minFloor, int maxFloor) {
        this.numElevators = numElevators;
        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
        this.elevatorStatusMap = new TreeMap<>();

        this.elevatorAvailabilityMinHeap = new PriorityQueue<>((a,b) -> {
            return Integer.compare(a[0], b[0]);
        });

        for(int i = 1; i <= this.numElevators; i++){
            this.elevatorAvailabilityMinHeap.offer(new int[]{0, i}); // time, car_id
        }

    }

    // Process a batch of "REQUEST F DIR" commands
    public void processRequests(List<String> cmds) {
        String currentCommand = "", direction = "";
        int floorId = 0, availableCar = 0;
        Queue<String> elevatorRequestsQueue = new LinkedList<>();
        List<String> elevatorCarStatusList;

        for(String command : cmds){
            elevatorRequestsQueue.add(command);
        }

        while(!elevatorRequestsQueue.isEmpty()){
            currentCommand = elevatorRequestsQueue.poll();
            floorId = Integer.parseInt(currentCommand.split(" ")[1]);
            direction = currentCommand.split(" ")[2];
            elevatorCarStatusList = new ArrayList<>();
            availableCar = this.elevatorAvailabilityMinHeap.poll()[1];
            elevatorCarStatusList.add(floorId+"");
            elevatorCarStatusList.add(ElevatorState.MOVING.name());
            elevatorCarStatusList.add(direction);
            this.elevatorAvailabilityMinHeap.offer(new int[]{floorId, availableCar});
            this.elevatorStatusMap.put(availableCar, elevatorCarStatusList);
        }

        for(Map.Entry<Integer, List<String>> entry : this.elevatorStatusMap.entrySet()){
            elevatorCarStatusList = entry.getValue();
            elevatorCarStatusList.set(1,ElevatorState.STOPPED.name());
            elevatorCarStatusList.set(2,Direction.IDLE.name());
        }

    }

    // Return the current floor of elevator eid
    public int getElevatorFloor(int eid) {
        List<String> elevatorStatus = this.elevatorStatusMap.get(eid);
        return Integer.parseInt(elevatorStatus.get(0));
    }

    // Return the current state of elevator eid
    public ElevatorState getElevatorState(int eid) {
        List<String> elevatorStatus = this.elevatorStatusMap.get(eid);
        String status = elevatorStatus.get(1);
        if(status.equals("MOVING")){
            return ElevatorState.MOVING;
        }else if(status.equals("STOPPED")){
            return ElevatorState.STOPPED;
        }else{
            return ElevatorState.DOOR_OPEN;
        }
    }

    // Return the current direction of elevator eid
    public Direction getElevatorDirection(int eid) {
        List<String> elevatorStatus = this.elevatorStatusMap.get(eid);
        String direction = elevatorStatus.get(1);
        if(direction.equals("UP")){
            return Direction.UP;
        }else if(direction.equals("DOWN")){
            return Direction.DOWN;
        }else{
            return Direction.IDLE;
        }
    }
}
