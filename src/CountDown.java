import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

abstract class CountDown extends JPanel {
    JLabel label;
    Timer timer;
    int second = 5;
    int milliSecond = 1;

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

    public void buttonClicked(){
        timer.stop();
    }


    public int getRemainingTime() {
        return second*1000+milliSecond;
    }
    public int getSecond(){
        return second;
    }

    public int getMillliSecond() {
        return milliSecond;
    }

    public abstract void onFinish();
}