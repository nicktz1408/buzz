import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class CountDown extends JPanel {
    JLabel label;
    Timer timer;
    int second = 5;
    int millliSecond = 1;
    private int remainingTime = 0;

    public CountDown() {
        label = new JLabel("Υπολοιπόμενος χρόνος: "+second);
        setLayout(new GridBagLayout());
        add(label);
        timer = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                millliSecond -= 1;
                if (second>0) {
                    if(millliSecond==0) {
                        millliSecond = 999;
                        second--;
                    }
                    label.setText("Υπολοιπόμενος χρόνος: " + second + ": " + millliSecond);
                } else {
                    ((Timer) (e.getSource())).stop();
                    label.setText("Ο χρόνος τελείωσε");
                }
            }
        });

        timer.setInitialDelay(0);
        timer.start();
    }

    public void buttonClicked(){
        timer.stop();
        int remainingTime = second*1000+millliSecond;
    }

    public int getMilli(){
        return millliSecond;
    }
}