package home.task;

import java.util.*;

public class ElevatorSync extends Thread {

    private final DigitPanel digitPanel; // Sequence of selected floors
    private final int floors; // Tech value
    private final int middleFloor; // Middle floor is a tech value
    private int currentFloor; // Store current elevator position
    private ElevatorDirection direction; // By default is running UP
    private Queue<Integer> floorSequence; // Store sequence of floors
    private final boolean[] mustVisitFloors;

    public ElevatorSync(int floors, DigitPanel digitPanel) {
        this.floors = floors;
        this.digitPanel = digitPanel;
        this.middleFloor = (int) Math.floor(this.floors / 2);
        this.currentFloor = 1;
        this.direction = ElevatorDirection.UP;
        this.floorSequence = new ArrayDeque<>();
        this.mustVisitFloors = new boolean[floors + 1];
    }

    // Method is used if floor sequence is empty (elevator doesn't run) and new floor was selected
    private void calcDirection() {
        this.direction = this.currentFloor <= this.middleFloor ? ElevatorDirection.UP : ElevatorDirection.DOWN;
    }

    public void calcFloorSequence() {
        // Get data from digit panel
        List<Integer> selectedFloors = digitPanel.getSelectedFloors();

        // Define elevator next floor (from floor sequence)
        if (floorSequence.isEmpty() && !selectedFloors.isEmpty()) {
            calcDirection();
        }

        // Based on elevator's next floor and direction build correct floor sequence
        if (!selectedFloors.isEmpty()) {
            for (var selectedFloor : selectedFloors) {
                mustVisitFloors[selectedFloor] = true;
            }

            int i = currentFloor;
            if (direction == ElevatorDirection.DOWN) {
                while (i >= 0) {
                    if (mustVisitFloors[i]) {
                        floorSequence.add(i);
                        mustVisitFloors[i] = false;
                    }
                    i--;
                }
            } else if (direction == ElevatorDirection.UP) {
                while (i < floors) {
                    if (mustVisitFloors[i]) {
                        floorSequence.add(i);
                        mustVisitFloors[i] = false;
                    }
                    i++;
                }
            }
            digitPanel.removeFloor(floorSequence.stream().toList());
        }
    }

    // What is this? I mean 2 methods with the same name, but not equal params
    // Add comment below
    // This is method overloading. It's possible to use methods with the same method name but with different parameters
    // When waiting method is called without params it calls sleep with value 1000
    // When it is given the parameter it calls sleep for that amount of milliseconds
    private void waiting() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private void waiting(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    // What is this?

    public void move() {
        // One by one
        for (Integer floor : this.floorSequence) {
            // New stop
            currentFloor = floor;
            // Wait a little bit, because our housemates are rather slow
            waiting(100);
            // Remove current stop
            this.floorSequence.poll();
        }
    }

    @Override
    public void run() {

        // Start elevator
        while (true) {
            waiting();
            // Calculate new sequence
            calcFloorSequence();
            // And go!
            move();
        }


    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Queue<Integer> getFloorSequence() {
        return floorSequence;
    }
}
