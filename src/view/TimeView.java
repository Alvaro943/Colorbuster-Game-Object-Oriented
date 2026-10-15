package view;

import javax.swing.*;
import model.Time;
import model.TimeListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TimeView extends JPanel {


    private static final long serialVersionUID = 1L;

    private Time time;
    private JLabel timeLabel;
    private ActionListener listener;
    private ActionListener TimeUpLis;



    public TimeView(ActionListener lis, ActionListener TimeUpListener) {
        time = new Time();
        timeLabel = new JLabel("60.000");
        add(timeLabel);
        listener = lis;
        TimeUpLis =TimeUpListener;

        // Register with the Model via its own listener interface .
        // When the Model ticks, hop onto the EDT and notify the Controller.
        time.setTickListener(new TimeListener() {
            public void tick(int millisRemaining) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        listener.actionPerformed(
                                new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "tick"));
                    }
                });
            }
        });
    }

    public void timeUpdate() {
        int totalMs = time.getMillis();
        if (totalMs < 0) totalMs = 0;

        int seconds = totalMs / 1000;
        int millis  = totalMs % 1000;

        timeLabel.setText(String.format("%d.%03d", seconds, millis));
        if (seconds == 0 && millis == 0) {

            System.out.println("0 seconds: "+millis+" milis");
            timeup();

        }
    }

    public void timeStart(){
        System.out.println("Time has started in TimeView");
        time.start();
    }
    public void timeReset(){
        time.reset();
    }


    public void timeup() {

        System.out.println("OUT OF TIME  ----   Game ending");
        TimeUpLis.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "timeUp"));
    }
    public void timeStop(){
        time.stop();
    }
}