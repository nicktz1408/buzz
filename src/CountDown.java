import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * In this class we implement the countdown clock for the StopTheClock game (1 or 2 players)
 */
abstract class CountDown extends JPanel {
    JLabel label;
    Timer timer;
    int second = 5;
    int milliSecond = 1;

    /**
     * The constructor of the clock, that counts backwards until the end of the five seconds the player/players has/have available to answer the question.
     */
    public CountDown() {
        label = new JLabel("Υπολοιπόμενος χρόνος: "+second);
        setLayout(new GridBagLayout());
        add(label);
        timer = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                milliSecond -= 1;
                if (second>-1) {
                    if(milliSecond==0) {
                        milliSecond = 999;
                        second--;
                    }
                    label.setText("Υπολοιπόμενος χρόνος: " + second + ": " + milliSecond);
                } else {
                    ((Timer) (e.getSource())).stop();
                    label.setText("Ο χρόνος τελείωσε");
                    onFinish();
                }
            }
        });
        timer.setInitialDelay(0);
        timer.start();
    }

    /**
     * Timer with custom time delay.
     * @param sec time delay
     */
    public CountDown(int sec){
        this.second = sec;
        timer = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                milliSecond -= 1;
                if (second>-1) {
                    if(milliSecond==0) {
                        milliSecond = 999;
                        second--;
                    }
                } else {
                    ((Timer) (e.getSource())).stop();
                    onFinish();
                }
            }
        });
        timer.setInitialDelay(0);
        timer.start();
    }


    /**
     * When a button with answer is clicked then the clock stop
     */
    public void buttonClicked(){
        timer.stop();
    }

    /**
     * This is a method that return the remaining time in the clock
     * @return the remaining time in the clock for count the score
     */
    public int getRemainingTime() {
        return second*1000+milliSecond;
    }


    /**
     * @return the seconds in the clock
     */
    public int getSecond(){
        return second;
    }


    /**
     * @return the milliseconds in the clock
     */
    public int getMilliSecond() {
        return milliSecond;
    }

    /**
     * function to be called after timer goes under the zero seconds
     */
    public abstract void onFinish();
}