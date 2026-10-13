package LLD.ElevatorSystem;

import java.util.ArrayList;
import java.util.List;

public class ElevatorSystem {

    public static void main(String[] args) {
        ElevatorController elevatorController = new ElevatorController(2,1,10);
        String[] commands = {
                "REQUEST 3 UP",
                "REQUEST 7 DOWN",
                "REQUEST 5 UP"
        };
        List<String> commandsList = new ArrayList<>();

        for(int i = 0 ; i < commands.length; i++){
            commandsList.add(commands[i]);
        }
        elevatorController.processRequests(commandsList);
        System.out.println(elevatorController.getElevatorFloor(1));
        System.out.println(elevatorController.getElevatorState(1));
        System.out.println(elevatorController.getElevatorDirection(1));
    }
}
