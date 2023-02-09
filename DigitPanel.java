package home.task;

import java.util.ArrayList;
import java.util.List;

public class DigitPanel {

    private static DigitPanel instance = null;
    private int floors;
    private List<Integer> selectedFloors;

    private DigitPanel(){
        this.selectedFloors = new ArrayList<>();
    }

    public static DigitPanel getInstance(){
        if(instance ==null){
            instance = new DigitPanel();
        }
        return instance;
    }

    public void setFloors(int floors) {
        this.floors = floors;
    }

    public void removeFloor(int floor) {
        this.selectedFloors.remove(floor);
    }

    public void removeFloor(List<Integer> removeFloors) {
        this.selectedFloors.removeAll(removeFloors);
    }

    public void selectFloor(int floor) {
        if (floor < 0 || floor > floors) {
            return;
        }
        this.selectedFloors.add(floor);
    }

    public List<Integer> getSelectedFloors() {
        return selectedFloors;
    }
}
