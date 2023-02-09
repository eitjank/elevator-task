package home.task.test;

import home.task.DigitPanel;
import home.task.ElevatorSync;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

public class ElevatorTest {

    @Test
    public void shouldElevatorGoUpToSelectedFloor(){
        DigitPanel digitPanel = new DigitPanel(25);
        ElevatorSync elevator = new ElevatorSync(25, digitPanel);

        digitPanel.selectFloor(12);

        elevator.calcFloorSequence();
        elevator.move();

        Assertions.assertEquals(new ArrayList<>(), digitPanel.getSelectedFloors());
        Assertions.assertEquals(12, elevator.getCurrentFloor());
        Assertions.assertIterableEquals(new ArrayDeque<>(), elevator.getFloorSequence());
    }

    @Test
    public void shouldElevatorGoDownToSelectedFloor(){
        DigitPanel digitPanel = new DigitPanel(25);
        ElevatorSync elevator = new ElevatorSync(25, digitPanel);

        digitPanel.selectFloor(19);

        elevator.calcFloorSequence();
        elevator.move();

        Assertions.assertEquals(19, elevator.getCurrentFloor());

        digitPanel.selectFloor(1);

        elevator.calcFloorSequence();
        elevator.move();

        Assertions.assertEquals(new ArrayList<>(), digitPanel.getSelectedFloors());
        Assertions.assertEquals(1, elevator.getCurrentFloor());
        Assertions.assertIterableEquals(new ArrayDeque<>(), elevator.getFloorSequence());
    }


    @Test
    public void shouldFloorsBeStoredInPanelIfItDoesNotSuiteDirection() {
        DigitPanel digitPanel = new DigitPanel(25);
        ElevatorSync elevator = new ElevatorSync(25, digitPanel);

        digitPanel.selectFloor(4);

        elevator.calcFloorSequence();
        elevator.move();

        digitPanel.selectFloor(1);
        digitPanel.selectFloor(3);
        digitPanel.selectFloor(5);
        digitPanel.selectFloor(6);
        digitPanel.selectFloor(7);
        digitPanel.selectFloor(8);

        elevator.calcFloorSequence();

        Assertions.assertEquals(new ArrayList<>(Arrays.asList(1, 3)), digitPanel.getSelectedFloors());
        Assertions.assertEquals(4, elevator.getCurrentFloor());
        Assertions.assertIterableEquals(new ArrayDeque<>(Arrays.asList(5,6,7,8)), elevator.getFloorSequence());
    }

    @Test
    public void shouldElevatorNotHaveDuplicateFloorSequence(){
        DigitPanel digitPanel = new DigitPanel(25);

        ElevatorSync elevator = new ElevatorSync(25, digitPanel);

        digitPanel.selectFloor(1);
        digitPanel.selectFloor(3);
        digitPanel.selectFloor(3);
        digitPanel.selectFloor(3);

        elevator.calcFloorSequence();

        Assertions.assertEquals(new ArrayList<>(), digitPanel.getSelectedFloors());
        Assertions.assertIterableEquals(new ArrayDeque<>(Arrays.asList(1,3)), elevator.getFloorSequence());
    }
}
