import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GUI{
    private JFrame window;
    private JButton playButton, signInButton, statisticsButton;
    private Player user = new Player("user");
    public GUI(Dimension dim){
        window = new JFrame("Buzz Game");
        window.setResizable(true);
        window.setSize(dim);
        window.setMinimumSize(dim);
        window.setLayout(new FlowLayout(FlowLayout.CENTER));
        window.setLayout(new GridLayout(4,1));


        playButton = new JButton("Παίξε");
        signInButton = new JButton("Εγραφή νέου χρήστη");
        statisticsButton = new JButton("Στατιστκά");

        JLabel label = new JLabel("Μενού", SwingConstants.CENTER);
        window.add(label);
        window.add(playButton);
        window.add(signInButton);
        window.add(statisticsButton);

        window.pack();
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);

        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Dimension dim = new Dimension(800, 550);
                startPlaying(dim);
            }
        });

        signInButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Dimension dim = new Dimension(100, 50);
                afterCheckSignIn(dim);
            }
        });

        statisticsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Dimension dim = new Dimension(600, 450);
                try {
                    statisticsGui(dim);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void statisticsGui(Dimension dim) throws IOException {
        window = new JFrame("Buzz Game");
        window.setResizable(true);
        window.setSize(dim);
        window.setMinimumSize(dim);
        window.setLayout(new FlowLayout(FlowLayout.LEFT));
        window.setLayout(new GridLayout(5,1));
        JPanel panel1 = new JPanel();

        JLabel label = new JLabel("Find your username:", SwingConstants.CENTER);
        String[] pl = user.listOfThePlayers();
        JComboBox cb =new JComboBox(pl);
        JButton show = new JButton("Show");
        panel1.add(label);
        panel1.add(cb);
        panel1.add(show);

        JLabel label1 = new JLabel("Παίκτης: ");

        JLabel label2 = new JLabel("Υψηλότερο σκόρ στο ατομικό παιχνίδι: ");

        JLabel label3 = new JLabel("Συνολικά ατομικά παιχνίδια: ");

        JLabel label4 = new JLabel("Νίκες στο παιχνίδι 2 ατόμων: ");

        JLabel label5 = new JLabel("Συνολικά παιχνίδια 2 ατόμων: ");
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        panel2.add(label1);
        panel3.add(label2);
        panel3.add(label3);
        panel4.add(label4);
        panel4.add(label5);

        window.add(panel1);
        window.add(panel2);
        window.add(panel3);
        window.add(panel4);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setVisible(true);

        show.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String textFieldValue = (String) cb.getItemAt(cb.getSelectedIndex());
                Player player = new Player(textFieldValue);
                try {
                    if(player.checkTheNames(textFieldValue)){
                        label1.setText("Παίκτης: "+player.getName());
                        label2.setText("Υψηλότερο σκόρ στο ατομικό παιχνίδι: "+player.getHighScore());
                        label3.setText("Συνολικά ατομικά παιχνίδια: "+player.getTotalGamesAlone());
                        label4.setText("Νίκες στο παιχνίδι 2 ατόμων: "+player.getWins()+"("+(player.getWins()*1.0/player.getTotalGAmesFriends())*100+"%"+")");
                        label5.setText("Συνολικά παιχνίδια 2 ατόμων: "+player.getTotalGAmesFriends());

                    } else{
                        JOptionPane.showMessageDialog(null, "Λάθος όνομα χρήστη");
                    }
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

    }



    public void startPlaying(Dimension dim){
        window = new JFrame("Buzz Game");
        JPanel panel = new JPanel();

        window.setSize(dim);
        window.setMinimumSize(dim);
        window.setLayout(new GridLayout(10,1));

        JRadioButton r1 = new JRadioButton("'Ενας παίκτης");
        JRadioButton r2 = new JRadioButton("Δύο παίκτες");
        JButton play = new JButton("Επόμενο");
        ButtonGroup bg=new ButtonGroup();
        bg.add(r1);
        bg.add(r2);

        panel.add(r1);
        panel.add(r2);
        panel.add(play);
        window.add(panel);



        window.pack();
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setVisible(true);

        play.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(r1.isSelected()){
                    JPanel panel2 = new JPanel();
                    JLabel label = new JLabel("Όνομα Παίκτη:", SwingConstants.CENTER);
                    String[] pl = new String[0];
                    try {
                        pl = user.listOfThePlayers();
                        JButton run = new JButton("Ξεκίνα");
                        JComboBox cb = new JComboBox(pl);
                        panel2.add(label);
                        panel2.add(cb);
                        panel2.add(run);
                        window.add(panel2);
                        window.setVisible(true);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    JComboBox cb =new JComboBox(pl);
                } else if(r2.isSelected()){
                    JPanel panel2 = new JPanel();
                    JPanel panel3 = new JPanel();
                    JLabel label = new JLabel("Όνομα 1ou Παίκτη:", SwingConstants.CENTER);
                    JLabel label2 = new JLabel("Όνομα 2ou Παίκτη:", SwingConstants.CENTER);
                    String[] pl = new String[0];
                    try {
                        pl = user.listOfThePlayers();
                        JButton run = new JButton("Ξεκίνα");
                        JComboBox cb = new JComboBox(pl);
                        JComboBox cb2 = new JComboBox(pl);
                        panel2.add(label);
                        panel2.add(cb);

                        panel3.add(label2);
                        panel3.add(cb2);
                        panel3.add(run);
                        window.add(panel2);
                        window.add(panel3);
                        window.setVisible(true);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        });


    }

    public void afterCheckSignIn(Dimension dim){
        window = new JFrame("Buzz Game");
        window.setSize(dim);
        window.setMinimumSize(dim);

        JLabel label1 = new JLabel("Enter Username:",JLabel.CENTER);

        JTextField text1 = new JTextField(15);


        JButton SUBMIT=new JButton("SUBMIT");

        JPanel panel=new JPanel(new GridLayout(1,1));
        panel.add(label1);
        panel.add(text1);
        panel.add(SUBMIT);

        window.add(panel);

        window.pack();
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setVisible(true);

        SUBMIT.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String textFieldValue = text1.getText();
                Player player = new Player(textFieldValue);
                try {
                    if (player.newPlayer(player)) {
                        window.setVisible(false);

                    } else{
                        JOptionPane.showMessageDialog(new JFrame(),
                                "Αυτό το όνομα χρήστη υπάρχει ήδη","Σφάλμα",
                                JOptionPane.ERROR_MESSAGE);
                    }

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }


            }
        });
    }
}