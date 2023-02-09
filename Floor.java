package home.task;

public class Floor extends Thread {
    DigitPanel digitPanel;
    int number;
    boolean generates;
    int millis;

    public Floor(int number, DigitPanel digitPanel, boolean generates, int millis) {
        this.number = number;
        this.digitPanel = digitPanel;
        this.generates = generates;
        this.millis = millis;
    }

    @Override
    public void run() {
        if (generates) {
            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            digitPanel.selectFloor(number);
        }
    }
}
