import GameLogic.*;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;


public class GUI{
    private JFrame window;
    private final JButton playButton, signInButton, statisticsButton;
    private JButton answer1;
    private GamePlayer player1 = new GamePlayer();
    private GamePlayer player2 = new GamePlayer();
    private final Player user1 = new Player("user1");
    private final Player user2 = new Player("user2");
    private ImageIcon icon;
    private int betPlayer1 = 0;
    private int betPlayer2 = 0;



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
                URL url = getClass().getResource("ButtonClick.wav");
                AudioClip clip = Applet.newAudioClip(url);
                clip.play();
                Dimension dim = new Dimension(200, 150);
                startPlaying(dim);

            }
        });

        signInButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                URL url = getClass().getResource("ButtonClick.wav");
                AudioClip clip = Applet.newAudioClip(url);
                clip.play();
                Dimension dim = new Dimension(100, 50);
                afterCheckSignIn(dim);

            }
        });

        statisticsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                URL url = getClass().getResource("ButtonClick.wav");
                AudioClip clip = Applet.newAudioClip(url);
                clip.play();
                Dimension dim = new Dimension(600, 450);
                try {
                    statisticsGui(dim);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }



    public void checktheTypeOfRound(GameFacade game, boolean solo){
        Dimension dim = new Dimension(800, 500);
        switch (game.getCurrentRound().getRoundName()){
            case 1:
                if(solo){
                    initializeAloneGame(dim, game);
                }else {
                    initialize2Game(dim, game);
                }
                break;
            case 2:
                if(solo) {
                    stopTheClockRound(dim, game);
                }else{
                    stopTheClock2Round(dim, game);
                }
                break;
            case 3:
                Dimension dimBet = new Dimension(200,150);
                if(solo){
                    bettingChoises(dimBet, game);
                }else{
                    bettingChoissesForTwoPlayers(dim, game);
                }
                break;
            case 4:
                if(!solo) {
                    quickAnswerRound(dim, game);
                }
                break;
            case 5:
                if(!solo) {
                    thermometerRound(dim, game);
                }
                break;
        }
    }

    private void stopTheClock2Round(Dimension dim, GameFacade game) {
        JFrame frame = new JFrame("Buzz Game");
        frame.setResizable(true);
        frame.setSize(dim);
        frame.setMinimumSize(dim);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));
        frame.setLayout(new GridLayout(3,2));
        EmptyBorder border = new EmptyBorder(5, 20, 5, 20);
        LineBorder line = new LineBorder(Color.blue, 2, true);
        LineBorder lineQuestion = new LineBorder(Color.red,2,true);
        CompoundBorder compound = new CompoundBorder(line, border);
        CompoundBorder compoundQuestion = new CompoundBorder(lineQuestion, border);

        JPanel centralPanel = new JPanel();
        CountDown clock = new CountDown() {
            @Override
            public void onFinish() {
                frame.setVisible(false);
                FetchNextQuestionStatus status = game.fetchNextQuestion(player1);
                if(status == FetchNextQuestionStatus.GAME_FINISHED){
                    frame.setVisible(false);
                    if(player1.getScore()>player2.getScore()){
                        JOptionPane.showMessageDialog(new JFrame(),
                                "Σκόρ "+user1.getName()+": "+player1.getScore()+"\n"+"Σκορ "+user2.getName()+": "+player2.getScore()+"\n"+"Νικητής ο παίκτης "+user1.getName()+"!","Μπράβο!",
                                JOptionPane.INFORMATION_MESSAGE);
                        try {
                            user1.newDataWithTwoPlayers(user1.getName(), true);
                            user2.newDataWithTwoPlayers(user2.getName(), false);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }

                    }else {
                        JOptionPane.showMessageDialog(new JFrame(),
                                "Σκόρ " + user1.getName() + ": " + player1.getScore() + "\n" + "Σκορ " + user2.getName() + ": " + player2.getScore() + "\n" + "Νικητής ο παίκτης " + user2.getName() + "!", "Μπράβο!",
                                JOptionPane.INFORMATION_MESSAGE);
                        try {
                            user1.newDataWithTwoPlayers(user1.getName(), false);
                            user2.newDataWithTwoPlayers(user2.getName(), true);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                    player1 = new GamePlayer();
                    player2 = new GamePlayer();
                }else {
                    checktheTypeOfRound(game, false);
                }
            }
        };
        clock.setBorder(compound);


        JLabel label1 = new JLabel("Γύρος: "+game.getCurrentRoundIndex());
        label1.setBorder(compound);

        JLabel label2 = new JLabel("Σκορ 1ου παίκτη: "+(int)player1.getScore());
        label2.setBorder(compound);

        JLabel label4 = new JLabel("Σκορ 2ου παίκτη: "+(int)player2.getScore());
        label4.setBorder(compound);

        JLabel label5 = new JLabel("Τύπος Γύρου: "+getRoundName(game.getCurrentRound().getRoundName()));
        label5.setBorder(compound);


        JLabel question = new JLabel("Ερώτηση: "+game.getCurrentQuestion(player1).getQuestionText());
        question.setBorder(compoundQuestion);


        centralPanel.add(label5);
        centralPanel.add(label1);
        centralPanel.add(label2);
        centralPanel.add(label4);
        centralPanel.add(clock);


        JPanel panel2 = new JPanel();

        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.orange);


        panel2.add(question);
        if(game.getCurrentQuestion(player1) instanceof QuestionWithImage){
            String imgName =  ((QuestionWithImage) game.getCurrentQuestion(player1)).getImagePath();
            URL imageURL = getClass().getResource("Images/" +imgName);
            if (imageURL != null) {
                icon = new ImageIcon(imageURL);

            }

            JLabel labelWithIcon = new JLabel(new ImageIcon(icon.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT)));
            panel2.add(labelWithIcon);
        }


        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();
        panel4.setBackground(Color.orange);
        panel5.setBackground(Color.green);









        KeyListener myListener = new KeyListener() {
            int answerIndex = -1;
            boolean pressed1 = false;
            boolean pressed2 = false;
            int time1, time2;
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(!pressed1) {
                    switch (e.getKeyChar()) {
                        case 'q':
                            pressed1 = true;
                            answerIndex = 0;
                            break;
                        case 'w':
                            pressed1 = true;
                            answerIndex = 1;
                            break;
                        case 'e':
                            pressed1 = true;
                            answerIndex = 2;
                            break;
                        case 'r':
                            pressed1 = true;
                            answerIndex = 3;
                            break;
                    }
                    if(pressed1){
                        time1 = clock.getRemainingTime();
                        game.answerQuestion(player1, answerIndex, time1);

                    }

                }
                if(!pressed2) {
                    switch (e.getKeyChar()) {
                        case 'u':
                            pressed2 = true;
                            answerIndex = 0;
                            break;
                        case 'i':
                            pressed2 = true;
                            answerIndex = 1;
                            break;
                        case 'o':
                            pressed2 = true;
                            answerIndex = 2;
                            break;
                        case 'p':
                            pressed2 = true;
                            answerIndex = 3;
                            break;
                    }
                    if(pressed2) {
                        time2 = clock.getRemainingTime();
                        game.answerQuestion(player2, answerIndex, time2);

                    }
                }

                if((pressed1 && pressed2) || (clock.getSecond()==0 && clock.getMillliSecond()==1)){
                    clock.buttonClicked();
                    frame.setVisible(false);
                    FetchNextQuestionStatus status = game.fetchNextQuestion(player1);
                    if(status == FetchNextQuestionStatus.GAME_FINISHED){
                        frame.setVisible(false);
                        if(player1.getScore()>player2.getScore()){
                            JOptionPane.showMessageDialog(new JFrame(),
                                    "Σκόρ "+user1.getName()+": "+player1.getScore()+"\n"+"Σκορ "+user2.getName()+": "+player2.getScore()+"\n"+"Νικητής ο παίκτης "+user1.getName()+"!","Μπράβο!",
                                    JOptionPane.INFORMATION_MESSAGE);
                            try {
                                user1.newDataWithTwoPlayers(user1.getName(), true);
                                user2.newDataWithTwoPlayers(user2.getName(), false);
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }

                        }else {
                            JOptionPane.showMessageDialog(new JFrame(),
                                    "Σκόρ " + user1.getName() + ": " + player1.getScore() + "\n" + "Σκορ " + user2.getName() + ": " + player2.getScore() + "\n" + "Νικητής ο παίκτης " + user2.getName() + "!", "Μπράβο!",
                                    JOptionPane.INFORMATION_MESSAGE);
                            try {
                                user1.newDataWithTwoPlayers(user1.getName(), false);
                                user2.newDataWithTwoPlayers(user2.getName(), true);
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }
                        player1 = new GamePlayer();
                        player2 = new GamePlayer();
                    }else {
                        checktheTypeOfRound(game, false);
                    }
                }


            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };


        frame.addKeyListener(myListener);

        JButton answer2 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(0));
        answer2.addKeyListener(myListener);
        JButton answer3 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(1));
        answer3.addKeyListener(myListener);
        JButton answer4 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(2));
        answer4.addKeyListener(myListener);
        JButton answer5 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(3));
        answer5.addKeyListener(myListener);
        JButton answer6 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(0));
        answer6.addKeyListener(myListener);
        JButton answer7 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(1));
        answer7.addKeyListener(myListener);
        JButton answer8 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(2));
        answer8.addKeyListener(myListener);
        answer1 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(3));
        answer1.addKeyListener(myListener);


        panel4.add(answer2);
        panel4.add(answer3);
        panel4.add(answer4);
        panel4.add(answer5);
        panel5.add(answer6);
        panel5.add(answer7);
        panel5.add(answer8);
        panel5.add(answer1);

        JSplitPane jsp1 = new JSplitPane();
        jsp1.setLayout(new GridLayout(2,2));
        jsp1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, panel4, panel5);
        jsp1.setDividerLocation(dim.width / 2);




        frame.add(centralPanel);
        frame.add(panel2);
        frame.add(jsp1);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    private void quickAnswerRound(Dimension dim, GameFacade game) {
        JFrame frame = new JFrame("Buzz Game");
        frame.setResizable(true);
        frame.setSize(dim);
        frame.setMinimumSize(dim);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));
        frame.setLayout(new GridLayout(3,2));
        EmptyBorder border = new EmptyBorder(5, 20, 5, 20);
        LineBorder line = new LineBorder(Color.blue, 2, true);
        CompoundBorder compound = new CompoundBorder(line, border);
        LineBorder lineQuestion = new LineBorder(Color.red,2,true);
        CompoundBorder compoundQuestion = new CompoundBorder(lineQuestion,border);

        JPanel centralPanel = new JPanel();



        JPanel panel1 = new JPanel();
        JLabel label1 = new JLabel("Γύρος: "+game.getCurrentRoundIndex());
        label1.setBorder(compound);

        JLabel label2 = new JLabel("Σκορ: "+player1.getScore());
        label2.setBorder(compound);

        JLabel label4 = new JLabel("Σκορ: "+player2.getScore());
        label4.setBorder(compound);

        JLabel label5 = new JLabel("Τύπος Γύρου: "+getRoundName(game.getCurrentRound().getRoundName()));
        label5.setBorder(compound);


        JLabel question = new JLabel("Ερώτηση: "+game.getCurrentQuestion(player1).getQuestionText());
        question.setBorder(compoundQuestion);


        centralPanel.add(label5);
        centralPanel.add(label1);
        centralPanel.add(label4);
        centralPanel.add(label2);



        JPanel panel2 = new JPanel();


        panel1.setBackground(Color.orange);


        panel2.add(question);
        JSplitPane jsp = new JSplitPane();
        jsp.setLayout(new GridLayout(2,2));


        jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, panel1, panel2);
        jsp.setDividerLocation(dim.width / 2);

        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();
        panel4.setBackground(Color.orange);
        panel5.setBackground(Color.green);


        KeyListener myListener = new KeyListener() {
            int answerIndex = 0;
            boolean pressed1 = false;
            boolean pressed2 = false;
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(!pressed1) {
                    switch (e.getKeyChar()) {
                        case 'q':
                            pressed1 = true;
                            answerIndex = 0;
                            break;
                        case 'w':
                            pressed1 = true;
                            answerIndex = 1;
                            break;
                        case 'e':
                            pressed1 = true;
                            answerIndex = 2;
                            break;
                        case 'r':
                            pressed1 = true;
                            answerIndex = 3;
                            break;
                    }
                    if(pressed1){
                        game.answerQuestion(player1, answerIndex);
                    }

                }
                if(!pressed2) {
                    switch (e.getKeyChar()) {
                        case 'u':
                            pressed2 = true;
                            answerIndex = 0;
                            break;
                        case 'i':
                            pressed2 = true;
                            answerIndex = 1;
                            break;
                        case 'o':
                            pressed2 = true;
                            answerIndex = 2;
                            break;
                        case 'p':
                            pressed2 = true;
                            answerIndex = 3;
                            break;
                    }
                    if(pressed2) {
                        game.answerQuestion(player2, answerIndex);
                    }
                }

                if(pressed1 && pressed2){
                    game.fetchNextQuestion(player1);
                    frame.setVisible(false);
                    FetchNextQuestionStatus status = game.fetchNextQuestion(player1);
                    if(status == FetchNextQuestionStatus.GAME_FINISHED){
                        frame.setVisible(false);
                        if(player1.getScore()>player2.getScore()){
                            JOptionPane.showMessageDialog(new JFrame(),
                                    "Σκόρ "+user1.getName()+": "+player1.getScore()+"\n"+"Σκορ "+user2.getName()+": "+player2.getScore()+"\n"+"Νικητής ο παίκτης "+user1.getName()+"!","Μπράβο!",
                                    JOptionPane.INFORMATION_MESSAGE);
                            try {
                                user1.newDataWithTwoPlayers(user1.getName(), true);
                                user2.newDataWithTwoPlayers(user2.getName(), false);
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }

                        }else {
                            JOptionPane.showMessageDialog(new JFrame(),
                                    "Σκόρ " + user1.getName() + ": " + player1.getScore() + "\n" + "Σκορ " + user2.getName() + ": " + player2.getScore() + "\n" + "Νικητής ο παίκτης " + user2.getName() + "!", "Μπράβο!",
                                    JOptionPane.INFORMATION_MESSAGE);
                            try {
                                user1.newDataWithTwoPlayers(user1.getName(), false);
                                user2.newDataWithTwoPlayers(user2.getName(), true);
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }
                        player1 = new GamePlayer();
                        player2 = new GamePlayer();
                    }else {
                        frame.setVisible(false);
                        checktheTypeOfRound(game, false);
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };


        frame.addKeyListener(myListener);

        JButton answer2 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(0));
        answer2.addKeyListener(myListener);
        JButton answer3 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(1));
        answer3.addKeyListener(myListener);
        JButton answer4 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(2));
        answer4.addKeyListener(myListener);
        JButton answer5 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(3));
        answer5.addKeyListener(myListener);
        JButton answer6 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(0));
        answer6.addKeyListener(myListener);
        JButton answer7 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(1));
        answer7.addKeyListener(myListener);
        JButton answer8 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(2));
        answer8.addKeyListener(myListener);
        answer1 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(3));
        answer1.addKeyListener(myListener);


        panel4.add(answer2);
        panel4.add(answer3);
        panel4.add(answer4);
        panel4.add(answer5);
        panel5.add(answer6);
        panel5.add(answer7);
        panel5.add(answer8);
        panel5.add(answer1);

        JSplitPane jsp1 = new JSplitPane();
        jsp1.setLayout(new GridLayout(2,2));
        jsp1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, panel4, panel5);
        jsp1.setDividerLocation(dim.width / 2);




        frame.add(centralPanel);
        frame.add(panel2);
        frame.add(jsp1);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

    }

    private void thermometerRound(Dimension dim, GameFacade game) {
        Frame frame = new JFrame("Buzz Game");
        frame.setResizable(true);
        frame.setSize(dim);
        frame.setMinimumSize(dim);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));
        frame.setLayout(new GridLayout(3,2));
        EmptyBorder border = new EmptyBorder(5, 20, 5, 20);
        LineBorder line = new LineBorder(Color.blue, 2, true);
        LineBorder lineQuestion = new LineBorder(Color.red,2,true);
        CompoundBorder compound = new CompoundBorder(line, border);
        CompoundBorder compoundQuestion = new CompoundBorder(lineQuestion,border);

        JPanel centralPanel = new JPanel();



        JPanel panel1 = new JPanel();
        JLabel label1 = new JLabel("Γύρος: "+game.getCurrentRoundIndex());
        label1.setBorder(compound);

        JLabel label2 = new JLabel("Σκορ 1ου παίκτη: "+player1.getScore());
        label2.setBorder(compound);

        JLabel label4 = new JLabel("Σκορ 2ου παίκτη: "+player2.getScore());
        label4.setBorder(compound);

        JLabel label5 = new JLabel("Τύπος Γύρου: "+getRoundName(game.getCurrentRound().getRoundName()));
        label5.setBorder(compound);

        JLabel question1 = new JLabel("Ερώτηση: "+game.getCurrentQuestion(player1).getQuestionText());
        question1.setBorder(compoundQuestion);

        JLabel question2 = new JLabel("Ερώτηση: "+game.getCurrentQuestion(player2).getQuestionText());
        question2.setBorder(compoundQuestion);



        centralPanel.add(label5);
        centralPanel.add(label1);
        centralPanel.add(label4);
        centralPanel.add(label2);


        JPanel panel2 = new JPanel();


        panel1.setBackground(Color.orange);
        panel1.add(question1);

        panel2.setBackground(Color.green);
        panel2.add(question2);
        JSplitPane jsp = new JSplitPane();
        jsp.setLayout(new GridLayout(2,2));


        jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, panel1, panel2);
        jsp.setDividerLocation(dim.width / 2);

        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();
        panel4.setBackground(Color.orange);
        panel5.setBackground(Color.green);









        KeyListener myListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                boolean hasFinished = false;
                int answerIndex = -1;

                switch (e.getKeyChar()) {
                    case 'q':
                        answerIndex = 0;
                        break;
                    case 'w':
                        answerIndex = 1;
                        break;
                    case 'e':
                        answerIndex = 2;
                        break;
                    case 'r':
                        answerIndex = 3;
                        break;
                }

                if(answerIndex != -1) {
                    game.answerQuestion(player1, answerIndex);

                    hasFinished = game.fetchNextQuestion(player1) == FetchNextQuestionStatus.GAME_FINISHED;
                }

                if(answerIndex == -1) { // user1 has not answered
                    switch (e.getKeyChar()) {
                        case 'u':
                            answerIndex = 0;
                            break;
                        case 'i':
                            answerIndex = 1;
                            break;
                        case 'o':
                            answerIndex = 2;
                            break;
                        case 'p':
                            answerIndex = 3;
                            break;
                    }

                    if(answerIndex != -1) {
                        game.answerQuestion(player2, answerIndex);

                        hasFinished = game.fetchNextQuestion(player2) == FetchNextQuestionStatus.GAME_FINISHED;
                    }
                }

                if(game.getCurrentRound() instanceof ThermometerRound) {
                    System.out.println("Total right 1:" + ((ThermometerRound) game.getCurrentRound()).getPlayerWins(player1));
                    System.out.println("Total right 2:" + ((ThermometerRound) game.getCurrentRound()).getPlayerWins(player2));
                }

                if(hasFinished){
                    frame.setVisible(false);
                    if(player1.getScore() > player2.getScore()){
                        JOptionPane.showMessageDialog(new JFrame(),
                                "Σκόρ "+user1.getName()+": "+player1.getScore()+"\n"+"Σκορ "+user2.getName()+": "+player2.getScore()+"\n"+"Νικητής ο παίκτης "+user1.getName()+"!","Μπράβο!",
                                JOptionPane.INFORMATION_MESSAGE);
                        try {
                            user1.newDataWithTwoPlayers(user1.getName(), true);
                            user2.newDataWithTwoPlayers(user2.getName(), false);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }else {
                        JOptionPane.showMessageDialog(new JFrame(),
                                "Σκόρ " + user1.getName() + ": " + player1.getScore() + "\n" + "Σκορ " + user2.getName() + ": " + player2.getScore() + "\n" + "Νικητής ο παίκτης " + user2.getName() + "!", "Μπράβο!",
                                JOptionPane.INFORMATION_MESSAGE);
                        try {
                            user1.newDataWithTwoPlayers(user1.getName(), false);
                            user2.newDataWithTwoPlayers(user2.getName(), true);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                    player1 = new GamePlayer();
                    player2 = new GamePlayer();
                }else {
                    frame.setVisible(false);
                    checktheTypeOfRound(game, false);
                }
            }





            @Override
            public void keyReleased(KeyEvent e) {

            }
        };


        frame.addKeyListener(myListener);

        JButton answer2 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(0));
        answer2.addKeyListener(myListener);
        JButton answer3 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(1));
        answer3.addKeyListener(myListener);
        JButton answer4 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(2));
        answer4.addKeyListener(myListener);
        JButton answer5 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(3));
        answer5.addKeyListener(myListener);
        JButton answer6 = new JButton(game.getCurrentQuestion(player2).getAnswerAtIndex(0));
        answer6.addKeyListener(myListener);
        JButton answer7 = new JButton(game.getCurrentQuestion(player2).getAnswerAtIndex(1));
        answer7.addKeyListener(myListener);
        JButton answer8 = new JButton(game.getCurrentQuestion(player2).getAnswerAtIndex(2));
        answer8.addKeyListener(myListener);
        answer1 = new JButton(game.getCurrentQuestion(player2).getAnswerAtIndex(3));
        answer1.addKeyListener(myListener);


        panel4.add(answer2);
        panel4.add(answer3);
        panel4.add(answer4);
        panel4.add(answer5);
        panel5.add(answer6);
        panel5.add(answer7);
        panel5.add(answer8);
        panel5.add(answer1);

        JSplitPane jsp1 = new JSplitPane();
        jsp1.setLayout(new GridLayout(2,2));
        jsp1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, panel4, panel5);
        jsp1.setDividerLocation(dim.width / 2);




        frame.add(centralPanel);
        frame.add(jsp);
        frame.add(jsp1);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void bettingRound(Dimension dim, GameFacade game){
        JFrame frame = new JFrame("Buzz Game");
        frame.setResizable(true);
        frame.setSize(dim);
        frame.setMinimumSize(dim);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));
        frame.setLayout(new GridLayout(3,3));
        EmptyBorder border = new EmptyBorder(5, 20, 5, 20);
        LineBorder line = new LineBorder(Color.blue, 2, true);
        LineBorder lineQuestion = new LineBorder(Color.red,2,true);
        CompoundBorder compound = new CompoundBorder(line, border);
        CompoundBorder compoundQuestion = new CompoundBorder(lineQuestion, border);

        JPanel panel1 = new JPanel();
        JLabel label1 = new JLabel("Γύρος: "+game.getCurrentRoundIndex());
        label1.setBorder(compound);

        JLabel label2 = new JLabel("Σκορ: "+player1.getScore());
        label2.setBorder(compound);

        JLabel label3 = new JLabel("Ερώτηση: "+game.getCurrentQuestion(player1).getQuestionText());
        label3.setBorder(compoundQuestion);

        JLabel label4 = new JLabel("Τύπος Γύρου: "+getRoundName(game.getCurrentRound().getRoundName()));
        label4.setBorder(compound);

        JLabel label5 = new JLabel("Κατηγορία ερώτησης: " +  game.getCurrentQuestion(player1).getCategory());
        label5.setBorder(compound);




        panel1.add(label4);
        panel1.add(label1);
        panel1.add(label5);
        panel1.add(label2);
        frame.add(panel1);

        JPanel panel2 = new JPanel();
        panel2.add(label3);

        if(game.getCurrentQuestion(player1) instanceof QuestionWithImage){
            String imgName =  ((QuestionWithImage) game.getCurrentQuestion(player1)).getImagePath();

            URL imageURL = getClass().getResource("Images/" + imgName);
            if (imageURL != null) {
                icon = new ImageIcon(imageURL);
            }

            JLabel labelWithIcon = new JLabel(new ImageIcon(icon.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT)));
            panel2.add(labelWithIcon);
        }






        frame.add(panel2);



        JPanel panel3 = new JPanel();
        JButton answer1 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(0));

        JButton answer2 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(1));

        JButton answer3 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(2));

        JButton answer4 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(3));

        panel3.add(answer1);
        panel3.add(answer2);
        panel3.add(answer3);
        panel3.add(answer4);
        frame.add(panel3);






        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        ActionListener answerListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int answerIndex = 0;
                if(e.getSource() == answer1){
                    answerIndex=0;
                }else if(e.getSource() == answer2){
                    answerIndex=1;
                } else if(e.getSource() == answer3){
                    answerIndex = 2;
                } else if(e.getSource() == answer4){
                    answerIndex = 3;
                }
                game.answerQuestion(player1, answerIndex, betPlayer1);
                FetchNextQuestionStatus status = game.fetchNextQuestion(player1);
                if(status == FetchNextQuestionStatus.GAME_FINISHED){
                    frame.setVisible(false);
                    JOptionPane.showMessageDialog(new JFrame(),
                            "Τελείωσες το παιχνίδι με σκορ "+player1.getScore(),"Μπράβο!",
                            JOptionPane.INFORMATION_MESSAGE);
                    try {
                        user1.newDataAlone(user1.getName(), (int)player1.getScore());
                        player1 = new GamePlayer();
                        player2 = new GamePlayer();
                    } catch (IOException | ClassNotFoundException ioException) {
                        ioException.printStackTrace();
                    }
                    player1 = new GamePlayer();
                    player2 = new GamePlayer();
                }else {
                    frame.setVisible(false);
                    checktheTypeOfRound(game, true);
                }

            }
        };

        answer1.addActionListener(answerListener);
        answer2.addActionListener(answerListener);
        answer3.addActionListener(answerListener);
        answer4.addActionListener(answerListener);
    }

    public void stopTheClockRound(Dimension dim, GameFacade game){
        JFrame frame = new JFrame("Buzz Game");
        frame.setResizable(true);
        frame.setSize(dim);
        frame.setMinimumSize(dim);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));
        frame.setLayout(new GridLayout(3,3));
        EmptyBorder border = new EmptyBorder(5, 20, 5, 20);
        LineBorder line = new LineBorder(Color.blue, 2, true);
        LineBorder lineQuestion = new LineBorder(Color.red,2,true);
        CompoundBorder compound = new CompoundBorder(line, border);
        CompoundBorder compoundQuestion = new CompoundBorder(lineQuestion, border);

        JPanel panel1 = new JPanel();
        JLabel label1 = new JLabel("Γύρος: "+game.getCurrentRoundIndex());
        label1.setBorder(compound);

        JLabel label2 = new JLabel("Σκορ: "+player1.getScore());
        label2.setBorder(compound);

        JLabel label3 = new JLabel("Ερώτηση: "+game.getCurrentQuestion(player1).getQuestionText());
        label3.setBorder(compoundQuestion);

        JLabel label4 = new JLabel("Τύπος Γύρου: "+getRoundName(game.getCurrentRound().getRoundName()));
        label4.setBorder(compound);

        JLabel label5 = new JLabel("Κατηγορία ερώτησης: " +  game.getCurrentQuestion(player1).getCategory());
        label5.setBorder(compound);

        panel1.add(label4);
        panel1.add(label1);
        panel1.add(label5);
        panel1.add(label2);
        CountDown clock = new CountDown() {
            @Override
            public void onFinish() {
                frame.setVisible(false);
                FetchNextQuestionStatus status = game.fetchNextQuestion(player1);
                if (status == FetchNextQuestionStatus.GAME_FINISHED) {
                    frame.setVisible(false);
                    JOptionPane.showMessageDialog(new JFrame(),
                            "Σκόρ " + user1.getName() + ": " + player1.getScore() + "\n" + "Σκορ " + user2.getName() + ": " + player2.getScore() + "\n" + "Νικητής ο παίκτης " + user1.getName() + "!", "Μπράβο!",
                            JOptionPane.INFORMATION_MESSAGE);

                    try {
                        user1.newDataAlone(user1.getName(), (int) player1.getScore());
                    } catch (ClassNotFoundException | IOException e) {
                        e.printStackTrace();
                    }


                    player1 = new GamePlayer();
                    player2 = new GamePlayer();
                } else {
                    checktheTypeOfRound(game, true);
                }
            }};
        clock.setBorder(compound);

        panel1.add(clock);
        frame.add(panel1);


        JPanel panel2 = new JPanel();
        panel2.add(label3);
        if(game.getCurrentQuestion(player1) instanceof QuestionWithImage){
            String imgName =  ((QuestionWithImage) game.getCurrentQuestion(player1)).getImagePath();
            URL imageURL = getClass().getResource("Images/" +imgName);
            if (imageURL != null) {
                icon = new ImageIcon(imageURL);
            }

            JLabel labelWithIcon = new JLabel(new ImageIcon(icon.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT)));
            panel2.add(labelWithIcon);
        }







        frame.add(panel2);


        JPanel panel3 = new JPanel();
        JButton answer1 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(0));

        JButton answer2 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(1));

        JButton answer3 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(2));

        JButton answer4 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(3));

        panel3.add(answer1);
        panel3.add(answer2);
        panel3.add(answer3);
        panel3.add(answer4);
        frame.add(panel3);






        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);



        ActionListener answerListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clock.buttonClicked();
                int answerIndex = -1;
                if(e.getSource() == answer1){
                    answerIndex=0;
                }else if(e.getSource() == answer2){
                    answerIndex=1;
                } else if(e.getSource() == answer3){
                    answerIndex = 2;
                } else if(e.getSource() == answer4){
                    answerIndex = 3;
                }
                int remaining = clock.getRemainingTime();
                game.answerQuestion(player1, answerIndex, remaining);

                FetchNextQuestionStatus status = game.fetchNextQuestion(player1);
                if(status == FetchNextQuestionStatus.GAME_FINISHED){
                    frame.setVisible(false);
                    JOptionPane.showMessageDialog(new JFrame(),
                            "Τελείωσες το παιχνίδι με σκορ "+player1.getScore(),"Μπράβο!",
                            JOptionPane.INFORMATION_MESSAGE);

                    try {
                        user1.newDataAlone(user1.getName(), (int) player1.getScore());
                        player1 = new GamePlayer();
                        player2 = new GamePlayer();
                    } catch (IOException | ClassNotFoundException ioException) {
                        ioException.printStackTrace();
                    }
                }else {
                    frame.setVisible(false);
                    checktheTypeOfRound(game,true);
                }

            }

        };

        answer1.addActionListener(answerListener);
        answer2.addActionListener(answerListener);
        answer3.addActionListener(answerListener);
        answer4.addActionListener(answerListener);
    }


    public void statisticsGui(Dimension dim) throws IOException {
        window = new JFrame("Buzz Game");
        window.setResizable(true);
        window.setSize(dim);
        window.setMinimumSize(dim);
        window.setLayout(new FlowLayout(FlowLayout.LEFT));
        window.setLayout(new GridLayout(5,1));
        EmptyBorder border = new EmptyBorder(5, 20, 5, 20);
        LineBorder line = new LineBorder(Color.blue, 2, true);
        CompoundBorder compound = new CompoundBorder(line, border);
        JPanel panel1 = new JPanel();

        JLabel label = new JLabel("Βρές το όνομά σου:", SwingConstants.CENTER);
        String[] pl = user1.listOfThePlayers();
        JComboBox cb =new JComboBox(pl);
        JButton show = new JButton("Δείξε");
        panel1.add(label);
        panel1.add(cb);
        panel1.add(show);

        JLabel label1 = new JLabel("Παίκτης: ");
        label1.setBorder(compound);

        JLabel label2 = new JLabel("Υψηλότερο σκόρ στο ατομικό παιχνίδι: ");
        label2.setBorder(compound);

        JLabel label3 = new JLabel("Συνολικά ατομικά παιχνίδια: ");
        label3.setBorder(compound);

        JLabel label4 = new JLabel("Νίκες στο παιχνίδι 2 ατόμων: ");
        label4.setBorder(compound);

        JLabel label5 = new JLabel("Συνολικά παιχνίδια 2 ατόμων: ");
        label5.setBorder(compound);



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
                URL url = getClass().getResource("ButtonClick.wav");
                AudioClip clip = Applet.newAudioClip(url);
                clip.play();
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
        window.setResizable(false);
        window.setSize(dim);
        window.setMinimumSize(dim);
        window.setLayout(new GridLayout(5,1));




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
                URL url = getClass().getResource("ButtonClick.wav");
                AudioClip clip = Applet.newAudioClip(url);
                clip.play();
                if(r1.isSelected()){
                    GameFacade game = new GameFacadeDirector().buildSoloGame();
                    JPanel panel2 = new JPanel();
                    JLabel label = new JLabel("Όνομα Παίκτη:", SwingConstants.CENTER);
                    String[] pl = new String[0];
                    try {
                        pl = user1.listOfThePlayers();
                        JButton run = new JButton("Ξεκίνα");
                        JComboBox cb = new JComboBox(pl);
                        panel2.add(label);
                        panel2.add(cb);
                        panel2.add(run);
                        window.add(panel2);
                        window.setVisible(true);
                        run.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                URL url = getClass().getResource("ButtonClick.wav");
                                AudioClip clip = Applet.newAudioClip(url);
                                clip.play();
                                window.setVisible(false);
                                String name = (String) cb.getItemAt(cb.getSelectedIndex());
                                user1.setName(name);
                                window.setVisible(false);
                                checktheTypeOfRound(game,true);
                            }
                        });
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                } else if(r2.isSelected()){
                    GameFacade game = new GameFacadeDirector().buildTwoPlayersGame();
                    JPanel panel2 = new JPanel();
                    JPanel panel3 = new JPanel();
                    JPanel panel4 = new JPanel();
                    JLabel label = new JLabel("Όνομα 1ou Παίκτη:", SwingConstants.CENTER);
                    JLabel label2 = new JLabel("Όνομα 2ou Παίκτη:", SwingConstants.CENTER);
                    String[] pl = new String[0];
                    try {
                        pl = user1.listOfThePlayers();
                        JButton run = new JButton("Ξεκινήστε");
                        JComboBox cb = new JComboBox(pl);
                        JComboBox cb2 = new JComboBox(pl);
                        panel2.add(label);
                        panel2.add(cb);

                        panel3.add(label2);
                        panel3.add(cb2);
                        panel4.add(run);
                        window.add(panel2);
                        window.add(panel3);
                        window.add(panel4);
                        window.setVisible(true);
                        run.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                URL url = getClass().getResource("ButtonClick.wav");
                                AudioClip clip = Applet.newAudioClip(url);
                                clip.play();
                                String name1 = (String) cb.getItemAt(cb.getSelectedIndex());
                                user1.setName(name1);
                                String name2 = (String) cb2.getItemAt(cb2.getSelectedIndex());
                                user2.setName(name2);
                                if(name1.equals(name2)){
                                    JOptionPane.showMessageDialog(null, "Οι δύο παίκτες πρέπει να είναι διαφορετικοί");
                                } else {
                                    window.setVisible(false);
                                    checktheTypeOfRound(game,false);
                                }
                            }
                        });
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

        JLabel label1 = new JLabel("Βάλε ένα όνομα:",JLabel.CENTER);

        JTextField text1 = new JTextField(15);


        JButton SUBMIT=new JButton("ΥΠΟΒΟΛΗ");

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
                URL url = getClass().getResource("ButtonClick.wav");
                AudioClip clip = Applet.newAudioClip(url);
                clip.play();
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


    public void initializeAloneGame(Dimension dim,GameFacade game){

        JFrame frame = new JFrame("Buzz Game");
        frame.setResizable(true);
        frame.setSize(dim);
        frame.setMinimumSize(dim);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));
        frame.setLayout(new GridLayout(3,3));
        EmptyBorder border = new EmptyBorder(5, 20, 5, 20);
        LineBorder line = new LineBorder(Color.blue, 2, true);
        CompoundBorder compound = new CompoundBorder(line, border);
        LineBorder lineQuestion = new LineBorder(Color.red,2,true);
        CompoundBorder compoundQuestion = new CompoundBorder(lineQuestion,border);

        JPanel panel1 = new JPanel();
        JLabel label1 = new JLabel("Γύρος: "+game.getCurrentRoundIndex());
        label1.setBorder(compound);

        JLabel label2 = new JLabel("Σκορ: "+player1.getScore());
        label2.setBorder(compound);

        JLabel label3 = new JLabel("Ερώτηση: "+game.getCurrentQuestion(player1).getQuestionText());
        label3.setBorder(compoundQuestion);

        JLabel label4 = new JLabel("Τύπος Γύρου: "+getRoundName(game.getCurrentRound().getRoundName()));
        label4.setBorder(compound);

        JLabel label5 = new JLabel("Κατηγορία ερώτησης: " +  game.getCurrentQuestion(player1).getCategory());
        label5.setBorder(compound);

        panel1.add(label4);
        panel1.add(label1);
        panel1.add(label5);
        panel1.add(label2);
        frame.add(panel1);

        JPanel panel2 = new JPanel();
        panel2.add(label3);


        if(game.getCurrentQuestion(player1) instanceof QuestionWithImage){
            String imgName =  ((QuestionWithImage) game.getCurrentQuestion(player1)).getImagePath();
            URL imageURL = getClass().getResource("Images/" +imgName);
            if (imageURL != null) {
                icon = new ImageIcon(imageURL);

            }

            JLabel labelWithIcon = new JLabel(new ImageIcon(icon.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT)));
            panel2.add(labelWithIcon);
        }




        frame.add(panel2);


        JPanel panel3 = new JPanel();
        JButton answer1 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(0));

        JButton answer2 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(1));

        JButton answer3 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(2));

        JButton answer4 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(3));

        panel3.add(answer1);
        panel3.add(answer2);
        panel3.add(answer3);
        panel3.add(answer4);
        frame.add(panel3);






        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        ActionListener answerListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int answerIndex = -1;
                if(e.getSource() == answer1){
                    answerIndex=0;
                }else if(e.getSource() == answer2){
                    answerIndex=1;
                } else if(e.getSource() == answer3){
                    answerIndex = 2;
                } else if(e.getSource() == answer4){
                    answerIndex = 3;
                }
                game.answerQuestion(player1, answerIndex);
                FetchNextQuestionStatus status = game.fetchNextQuestion(player1);
                if(status == FetchNextQuestionStatus.GAME_FINISHED){
                    frame.setVisible(false);
                    JOptionPane.showMessageDialog(new JFrame(),
                            "Τελείωσες το παιχνίδι με σκορ "+player1.getScore(),"Μπράβο!",
                            JOptionPane.INFORMATION_MESSAGE);

                    try {
                        user1.newDataAlone(user1.getName(), (int)player1.getScore());
                        player1 = new GamePlayer();
                        player2 = new GamePlayer();
                    } catch (IOException | ClassNotFoundException ioException) {
                        ioException.printStackTrace();
                    }
                }else {
                    frame.setVisible(false);
                    checktheTypeOfRound(game, true);
                }
            }
        };

        answer1.addActionListener(answerListener);
        answer2.addActionListener(answerListener);
        answer3.addActionListener(answerListener);
        answer4.addActionListener(answerListener);
    }

    public void initialize2Game(Dimension dim, GameFacade game) {
        JFrame frame = new JFrame("Buzz Game");
        frame.setResizable(true);
        frame.setSize(dim);
        frame.setMinimumSize(dim);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));
        frame.setLayout(new GridLayout(3,2));
        EmptyBorder border = new EmptyBorder(5, 20, 5, 20);
        LineBorder line = new LineBorder(Color.blue, 2, true);
        LineBorder lineQuestion = new LineBorder(Color.red,2,true);
        CompoundBorder compound = new CompoundBorder(line, border);
        CompoundBorder compoundQuestion = new CompoundBorder(lineQuestion, border);

        JPanel centralPanel = new JPanel();
        JLabel label1 = new JLabel("Γύρος: "+game.getCurrentRoundIndex());
        label1.setBorder(compound);

        JLabel label2 = new JLabel("Σκορ 1ου παίκτη: "+(int)player1.getScore());
        label2.setBorder(compound);

        JLabel label4 = new JLabel("Σκορ 2ου παίκτη: "+(int)player2.getScore());
        label4.setBorder(compound);

        JLabel label5 = new JLabel("Τύπος Γύρου: "+getRoundName(game.getCurrentRound().getRoundName()));
        label5.setBorder(compound);


        JLabel question = new JLabel("Ερώτηση: "+game.getCurrentQuestion(player1).getQuestionText());
        question.setBorder(compoundQuestion);


        centralPanel.add(label5);
        centralPanel.add(label1);
        centralPanel.add(label2);
        centralPanel.add(label4);
        centralPanel.add(question);


        JPanel panel2 = new JPanel();

        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.orange);


        panel2.add(question);
        if(game.getCurrentQuestion(player1) instanceof QuestionWithImage){
            String imgName =  ((QuestionWithImage) game.getCurrentQuestion(player1)).getImagePath();
            URL imageURL = getClass().getResource("Images/" +imgName);
            if (imageURL != null) {
                icon = new ImageIcon(imageURL);

            }

            JLabel labelWithIcon = new JLabel(new ImageIcon(icon.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT)));
            panel2.add(labelWithIcon);
        }


        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();
        panel4.setBackground(Color.orange);
        panel5.setBackground(Color.green);









        KeyListener myListener = new KeyListener() {
            int answerIndex = 0;
            boolean pressed1 = false;
            boolean pressed2 = false;
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(!pressed1) {
                    switch (e.getKeyChar()) {
                        case 'q':
                            pressed1 = true;
                            answerIndex = 0;
                            break;
                        case 'w':
                            pressed1 = true;
                            answerIndex = 1;
                            break;
                        case 'e':
                            pressed1 = true;
                            answerIndex = 2;
                            break;
                        case 'r':
                            pressed1 = true;
                            answerIndex = 3;
                            break;
                    }
                    if(pressed1){
                        game.answerQuestion(player1, answerIndex);

                    }

                }
                if(!pressed2) {
                    switch (e.getKeyChar()) {
                        case 'u':
                            pressed2 = true;
                            answerIndex = 0;
                            break;
                        case 'i':
                            pressed2 = true;
                            answerIndex = 1;
                            break;
                        case 'o':
                            pressed2 = true;
                            answerIndex = 2;
                            break;
                        case 'p':
                            pressed2 = true;
                            answerIndex = 3;
                            break;
                    }
                    if(pressed2) {
                        game.answerQuestion(player2, answerIndex);
                    }
                }

                if(pressed1 && pressed2){
                    frame.setVisible(false);
                    FetchNextQuestionStatus status = game.fetchNextQuestion(player1);
                    if(status == FetchNextQuestionStatus.GAME_FINISHED){
                        frame.setVisible(false);
                        if(player1.getScore()>player2.getScore()){
                            JOptionPane.showMessageDialog(new JFrame(),
                                    "Σκόρ "+user1.getName()+": "+player1.getScore()+"\n"+"Σκορ "+user2.getName()+": "+player2.getScore()+"\n"+"Νικητής ο παίκτης "+user1.getName()+"!","Μπράβο!",
                                    JOptionPane.INFORMATION_MESSAGE);
                            try {
                                user1.newDataWithTwoPlayers(user1.getName(), true);
                                user2.newDataWithTwoPlayers(user2.getName(), false);
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }

                        }else {
                            JOptionPane.showMessageDialog(new JFrame(),
                                    "Σκόρ " + user1.getName() + ": " + player1.getScore() + "\n" + "Σκορ " + user2.getName() + ": " + player2.getScore() + "\n" + "Νικητής ο παίκτης " + user2.getName() + "!", "Μπράβο!",
                                    JOptionPane.INFORMATION_MESSAGE);
                            try {
                                user1.newDataWithTwoPlayers(user1.getName(), false);
                                user2.newDataWithTwoPlayers(user2.getName(), true);
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }
                        player1 = new GamePlayer();
                        player2 = new GamePlayer();
                    }else {
                        checktheTypeOfRound(game, false);
                    }
                }


            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };


        frame.addKeyListener(myListener);

        JButton answer2 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(0));
        answer2.addKeyListener(myListener);
        JButton answer3 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(1));
        answer3.addKeyListener(myListener);
        JButton answer4 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(2));
        answer4.addKeyListener(myListener);
        JButton answer5 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(3));
        answer5.addKeyListener(myListener);
        JButton answer6 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(0));
        answer6.addKeyListener(myListener);
        JButton answer7 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(1));
        answer7.addKeyListener(myListener);
        JButton answer8 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(2));
        answer8.addKeyListener(myListener);
        answer1 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(3));
        answer1.addKeyListener(myListener);


        panel4.add(answer2);
        panel4.add(answer3);
        panel4.add(answer4);
        panel4.add(answer5);
        panel5.add(answer6);
        panel5.add(answer7);
        panel5.add(answer8);
        panel5.add(answer1);

        JSplitPane jsp1 = new JSplitPane();
        jsp1.setLayout(new GridLayout(2,2));
        jsp1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, panel4, panel5);
        jsp1.setDividerLocation(dim.width / 2);




        frame.add(centralPanel);
        frame.add(panel2);
        frame.add(jsp1);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);





    }


    public String getRoundName(int roundId){
        switch (roundId){
            case 1:
                return "Σωστή Απάντηση";
            case 2:
                return "Σταμάτησε το χρονόμετρο";
            case 3:
                return "Ποντάρισμα";
            case 4:
                return "Γρήγορη Απάντηση";
            case 5:
                return "Θερμόμετρο";
        }
        return null;
    }



    public void bettingChoises(Dimension dim, GameFacade game){
        window = new JFrame("Buzz Game");
        window.setResizable(false);
        window.setSize(dim);
        window.setMinimumSize(dim);
        window.setLayout(new GridLayout(5,1));



        JPanel panel1 = new JPanel();
        JLabel label = new JLabel("Η επόμενη ερώτηση ανήκει στην κατηγορία "+game.getCurrentQuestion(player1).getCategory());
        panel1.add(label);


        JPanel panel2 = new JPanel();
        JLabel label2 = new JLabel("Πόσους πόντους θέλεις να ποντάρεις? ");
        panel2.add(label2);




        JPanel panel3 = new JPanel();
        JButton bet1 = new JButton("250");
        JButton bet2 = new JButton("500");
        JButton bet3 = new JButton("750");
        JButton bet4 = new JButton("1000");
        panel3.add(bet1);
        panel3.add(bet2);
        panel3.add(bet3);
        panel3.add(bet4);

        window.add(panel1);
        window.add(panel2);
        window.add(panel3);

        window.pack();
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setVisible(true);

        bet1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.setVisible(false);
                betPlayer1 = 250;
                Dimension dimension = new Dimension(800,500);
                bettingRound(dimension, game);
            }
        });
        bet2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.setVisible(false);
                betPlayer1 = 500;
                Dimension dimension = new Dimension(800,500);
                bettingRound(dimension, game);
            }
        });
        bet3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.setVisible(false);
                betPlayer1 = 750;
                Dimension dimension = new Dimension(800,500);
                bettingRound(dimension, game);
            }
        });
        bet4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.setVisible(false);
                betPlayer1 = 1000;
                Dimension dimension = new Dimension(800,500);
                bettingRound(dimension, game);
            }
        });

    }


    public void bettingChoissesForTwoPlayers(Dimension dim, GameFacade game){

        window = new JFrame("Buzz Game");
        window.setResizable(false);
        window.setSize(dim);
        window.setMinimumSize(dim);
        window.setLayout(new GridLayout(5,1));



        JPanel panel1 = new JPanel();
        JLabel label = new JLabel("Η επόμενη ερώτηση ανήκει στην κατηγορία "+game.getCurrentQuestion(player1).getCategory());
        panel1.add(label);


        JPanel panel2 = new JPanel();
        JLabel label2 = new JLabel("Πόσους πόντους θέλεις να ποντάρεις "+user1.getName()+"?");
        panel2.add(label2);

        JLabel label3 = new JLabel("Πόσους πόντους θέλεις να ποντάρεις "+user2.getName()+"?");



        JPanel panel3 = new JPanel();
        JButton bet1 = new JButton("250");

        JButton bet2 = new JButton("500");
        JButton bet3 = new JButton("750");
        JButton bet4 = new JButton("1000");
        JButton bet5 = new JButton("250");
        JButton bet6 = new JButton("500");
        JButton bet7 = new JButton("750");
        JButton bet8 = new JButton("1000");
        panel2.add(bet1);
        panel2.add(bet2);
        panel2.add(bet3);
        panel2.add(bet4);

        panel3.add(label3);
        panel3.add(bet5);
        panel3.add(bet6);
        panel3.add(bet7);
        panel3.add(bet8);



        window.add(panel1);
        window.add(panel2);
        window.add(panel3);

        window.pack();
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setVisible(true);

        KeyListener myListener = new KeyListener() {
            boolean pressed1 = false;
            boolean pressed2 = false;
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(!pressed1) {
                    switch (e.getKeyChar()) {
                        case 'q':
                            pressed1 = true;
                            betPlayer1 =250;
                            break;
                        case 'w':
                            pressed1 = true;
                            betPlayer1 = 500;
                            break;
                        case 'e':
                            pressed1 = true;
                            betPlayer1 = 750;
                            break;
                        case 'r':
                            pressed1 = true;
                            betPlayer1 = 1000;
                            break;
                    }


                }
                if(!pressed2) {
                    switch (e.getKeyChar()) {
                        case 'u':
                            pressed2 = true;
                            betPlayer2 = 250;
                            break;
                        case 'i':
                            pressed2 = true;
                            betPlayer2 = 500;
                            break;
                        case 'o':
                            pressed2 = true;
                            betPlayer2 = 750;
                            break;
                        case 'p':
                            pressed2 = true;
                            betPlayer2 = 1000;
                            break;
                    }
                }

                if(pressed1 && pressed2){
                    window.setVisible(false);
                    Dimension dimension = new Dimension(800, 500);
                    betting2Round(dimension, game);
                }


            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };

        bet1.addKeyListener(myListener);
        bet2.addKeyListener(myListener);
        bet3.addKeyListener(myListener);
        bet4.addKeyListener(myListener);
        bet5.addKeyListener(myListener);
        bet6.addKeyListener(myListener);
        bet7.addKeyListener(myListener);
        bet8.addKeyListener(myListener);

    }

    private void betting2Round(Dimension dimension, GameFacade game) {
        JFrame frame = new JFrame("Buzz Game");
        frame.setResizable(true);
        frame.setSize(dimension);
        frame.setMinimumSize(dimension);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));
        frame.setLayout(new GridLayout(3,2));
        EmptyBorder border = new EmptyBorder(5, 20, 5, 20);
        LineBorder line = new LineBorder(Color.blue, 2, true);
        LineBorder lineQuestion = new LineBorder(Color.red,2,true);
        CompoundBorder compound = new CompoundBorder(line, border);
        CompoundBorder compoundQuestion = new CompoundBorder(lineQuestion, border);

        JPanel centralPanel = new JPanel();
        JLabel label1 = new JLabel("Γύρος: "+game.getCurrentRoundIndex());
        label1.setBorder(compound);

        JLabel label2 = new JLabel("Σκορ 1ου παίκτη: "+(int)player1.getScore());
        label2.setBorder(compound);

        JLabel label4 = new JLabel("Σκορ 2ου παίκτη: "+(int)player2.getScore());
        label4.setBorder(compound);

        JLabel label5 = new JLabel("Τύπος Γύρου: "+getRoundName(game.getCurrentRound().getRoundName()));
        label5.setBorder(compound);


        JLabel question = new JLabel("Ερώτηση: "+game.getCurrentQuestion(player1).getQuestionText());
        question.setBorder(compoundQuestion);


        centralPanel.add(label5);
        centralPanel.add(label1);
        centralPanel.add(label2);
        centralPanel.add(label4);


        JPanel panel2 = new JPanel();

        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.orange);


        panel2.add(question);
        if(game.getCurrentQuestion(player1) instanceof QuestionWithImage){
            String imgName =  ((QuestionWithImage) game.getCurrentQuestion(player1)).getImagePath();
            URL imageURL = getClass().getResource("Images/" +imgName);
            if (imageURL != null) {
                icon = new ImageIcon(imageURL);

            }

            JLabel labelWithIcon = new JLabel(new ImageIcon(icon.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT)));
            panel2.add(labelWithIcon);
        }


        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();
        panel4.setBackground(Color.orange);
        panel5.setBackground(Color.green);









        KeyListener myListener = new KeyListener() {
            int answerIndex = 0;
            boolean pressed1 = false;
            boolean pressed2 = false;
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(!pressed1) {
                    switch (e.getKeyChar()) {
                        case 'q':
                            pressed1 = true;
                            answerIndex = 0;
                            break;
                        case 'w':
                            pressed1 = true;
                            answerIndex = 1;
                            break;
                        case 'e':
                            pressed1 = true;
                            answerIndex = 2;
                            break;
                        case 'r':
                            pressed1 = true;
                            answerIndex = 3;
                            break;
                    }
                    if(pressed1){
                        game.answerQuestion(player1, answerIndex, betPlayer1);

                    }

                }
                if(!pressed2) {
                    switch (e.getKeyChar()) {
                        case 'u':
                            pressed2 = true;
                            answerIndex = 0;
                            break;
                        case 'i':
                            pressed2 = true;
                            answerIndex = 1;
                            break;
                        case 'o':
                            pressed2 = true;
                            answerIndex = 2;
                            break;
                        case 'p':
                            pressed2 = true;
                            answerIndex = 3;
                            break;
                    }
                    if(pressed2) {
                        game.answerQuestion(player2, answerIndex, betPlayer2);
                    }
                }

                if(pressed1 && pressed2){
                    frame.setVisible(false);
                    FetchNextQuestionStatus status = game.fetchNextQuestion(player1);
                    if(status == FetchNextQuestionStatus.GAME_FINISHED){
                        if(player1.getScore()>player2.getScore()){
                            JOptionPane.showMessageDialog(new JFrame(),
                                    "Σκόρ "+user1.getName()+": "+player1.getScore()+"\n"+"Σκορ "+user2.getName()+": "+player2.getScore()+"\n"+"Νικητής ο παίκτης "+user1.getName()+"!","Μπράβο!",
                                    JOptionPane.INFORMATION_MESSAGE);
                            try {
                                user1.newDataWithTwoPlayers(user1.getName(), true);
                                user2.newDataWithTwoPlayers(user2.getName(), false);
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }

                        }else {
                            JOptionPane.showMessageDialog(new JFrame(),
                                    "Σκόρ " + user1.getName() + ": " + player1.getScore() + "\n" + "Σκορ " + user2.getName() + ": " + player2.getScore() + "\n" + "Νικητής ο παίκτης " + user2.getName() + "!", "Μπράβο!",
                                    JOptionPane.INFORMATION_MESSAGE);
                            try {
                                user1.newDataWithTwoPlayers(user1.getName(), false);
                                user2.newDataWithTwoPlayers(user2.getName(), true);
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }
                        player1 = new GamePlayer();
                        player2 = new GamePlayer();
                    }else {
                        checktheTypeOfRound(game, false);
                    }
                }


            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };


        frame.addKeyListener(myListener);

        JButton answer2 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(0));
        answer2.addKeyListener(myListener);
        JButton answer3 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(1));
        answer3.addKeyListener(myListener);
        JButton answer4 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(2));
        answer4.addKeyListener(myListener);
        JButton answer5 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(3));
        answer5.addKeyListener(myListener);
        JButton answer6 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(0));
        answer6.addKeyListener(myListener);
        JButton answer7 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(1));
        answer7.addKeyListener(myListener);
        JButton answer8 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(2));
        answer8.addKeyListener(myListener);
        answer1 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(3));
        answer1.addKeyListener(myListener);


        panel4.add(answer2);
        panel4.add(answer3);
        panel4.add(answer4);
        panel4.add(answer5);
        panel5.add(answer6);
        panel5.add(answer7);
        panel5.add(answer8);
        panel5.add(answer1);

        JSplitPane jsp1 = new JSplitPane();
        jsp1.setLayout(new GridLayout(2,2));
        jsp1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, panel4, panel5);
        jsp1.setDividerLocation(dimension.width / 2);




        frame.add(centralPanel);
        frame.add(panel2);
        frame.add(jsp1);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}