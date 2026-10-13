package LLD.DinnerPlateStacks;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DinnerPlates {
    private List<Stack<Integer>> dinnerPlateStacksList;
    private int stackCapacity;

    public DinnerPlates(int capacity) {
        this.dinnerPlateStacksList = new ArrayList<>();
        this.stackCapacity = capacity;
    }

    public void push(int val) {
        Stack<Integer> dinnerPlatesStack;
        boolean newStackRequired = true, stackSizeMoreThanZero = false;

        if(this.dinnerPlateStacksList.size() == 0){
            dinnerPlatesStack = new Stack<>();
            dinnerPlatesStack.push(val);
            stackSizeMoreThanZero = true;
            this.dinnerPlateStacksList.add(dinnerPlatesStack);
        }else {
            for (int i = 0; i < this.dinnerPlateStacksList.size(); i++) {
                dinnerPlatesStack = this.dinnerPlateStacksList.get(i);
                if (dinnerPlatesStack.size() < this.stackCapacity) {
                    dinnerPlatesStack.push(val);
                    newStackRequired = false;
                    break;
                }
            }
        }
        if(newStackRequired && !stackSizeMoreThanZero){
            dinnerPlatesStack = new Stack<>();
            dinnerPlatesStack.push(val);
            this.dinnerPlateStacksList.add(dinnerPlatesStack);
        }
    }

    public int pop() {
        int topMostPlate = -1;
        for(int i = this.dinnerPlateStacksList.size() - 1; i >= 0; i--){
            Stack<Integer> dinnerPlatesStack = this.dinnerPlateStacksList.get(i);
            if(dinnerPlatesStack.size() > 0){
                topMostPlate = dinnerPlatesStack.pop();
                break;
            }
        }
        return topMostPlate;
    }

    public int popAtStack(int index) {

        int topMostPlate = -1;

        if(index >= 0 && index < this.dinnerPlateStacksList.size()){
            Stack<Integer> dinnerPlatesStack = this.dinnerPlateStacksList.get(index);
            if(dinnerPlatesStack.size() > 0){
                topMostPlate = dinnerPlatesStack.pop();
            }
        }

        return topMostPlate;
    }
}
