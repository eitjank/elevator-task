package home.task;

import java.util.*;

public class ElevatorSync {

    private final DigitPanel digitPanel; // Sequence of selected floors
    private final int floors; // Tech value
    private final int middleFloor; // Middle floor is a tech value
    private int currentFloor; // Store current elevator position
    private ElevatorDirection direction; // By default is running UP
    private Queue<Integer> floorSequence; // Store sequence of floors

    public ElevatorSync(int floors, DigitPanel digitPanel) {
        this.floors = floors;
        this.digitPanel = digitPanel;
        this.middleFloor = (int) Math.floor(this.floors / 2);
        this.currentFloor = 1;
        this.direction = ElevatorDirection.UP;
        this.floorSequence = new ArrayDeque<>();
    }

    // Method is used if floor sequence is empty (elevator doesn't run) and new floor was selected
    private void calcDirection() {
        this.direction = this.currentFloor <= this.middleFloor ? ElevatorDirection.UP : ElevatorDirection.DOWN;
    }

    private void calcFloorSequence() {

        // Get data from digit panel

        // Define elevator next floor (from floor sequence)

        // Based on elevator's next floor and direction build correct floor sequence

    }

    // What is this? I mean 2 methods with the same name, but not equal params
    // Add comment below
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

    private void move() {
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


    public void run() {

        // Start elevator
        while (true) {
            // Calculate new sequence
            calcFloorSequence();
            // And go!
            move();
        }


    }

}
