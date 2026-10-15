package model;

import java.util.Timer;
import java.util.TimerTask;

public class Time {

    Timer timer = new Timer();
    int millis = 10_000;
    int millisRemaining = millis;

    private TimeListener tickListener;

    public void setTickListener(TimeListener listener) {
        this.tickListener = listener;
    }

    public void start() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                millisRemaining -= 33;
                if (tickListener != null) {
                    tickListener.tick(millisRemaining);
                }
                if (millisRemaining <= 0) {
                    timeUp();
                }
            }
        }, 0, 33);
    }

    public int getMillis() {
        return millisRemaining;
    }

    public void stop() {
        timer.cancel();
    }

    public void reset() {
        stop();
        millisRemaining = millis;
    }

    public void bonusTime(int bonusSeconds) {
        millisRemaining += bonusSeconds * 1000;
    }

    public void timeUp() {
        stop();
        //gameModel.endGame();
    }

}