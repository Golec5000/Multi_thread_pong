package gui;

import javax.swing.*;
import java.awt.*;

public class InputPanel extends JPanel {

    public JTextField tfPlayers,tfBalls, tfCol,tfRow, tfSleepOfBall, tfSleepOfPlayer;
    public JButton startButton;
    public JButton stopButton;

    public InputPanel(){
        //JLabel

        JLabel title = new JLabel("Welcome in the game");
        title.setSize(200,20);
        title.setLayout(null);
        title.setLocation(60,20);

        JLabel parmXMap = new JLabel("Ilość kolumn 13/15/17/19/21/23/25/27");
        parmXMap.setSize(250,20);
        parmXMap.setLayout(null);
        parmXMap.setLocation(10,80);

        JLabel parmYMap = new JLabel("Podaj ilość wierszy w zakresie 10 - 17");
        parmYMap.setSize(250,20);
        parmYMap.setLayout(null);
        parmYMap.setLocation(20,180);

        JLabel parmBalls = new JLabel("Podaj liczbę piłek min.3 ");
        parmBalls.setSize(200,20);
        parmBalls.setLayout(null);
        parmBalls.setLocation(50,280);

        JLabel players = new JLabel("Podaj liczbę graczy min. 2");
        players.setSize(200,20);
        players.setLayout(null);
        players.setLocation(50,380);

        JLabel sleepOfBall = new JLabel("Opóźnienie piłki 300 - 700 [ms]");
        sleepOfBall.setSize(200,20);
        sleepOfBall.setLayout(null);
        sleepOfBall.setLocation(30,480);

        JLabel sleepOfPlayer = new JLabel("Opóźnienie gracza 300 - 700 [ms]");
        sleepOfPlayer.setSize(200,20);
        sleepOfPlayer.setLayout(null);
        sleepOfPlayer.setLocation(30,580);

        //JButton

        //Start
        startButton = new JButton("Start");
        startButton.setSize(200,20);
        startButton.setLayout(null);
        startButton.setLocation(20,650);

        //End
        stopButton = new JButton("Pauza");
        stopButton.setSize(200,20);
        stopButton.setLayout(null);
        stopButton.setLocation(20,670);
        stopButton.setEnabled(false);


        //JTextField
        tfCol = new JTextField();
        tfCol.setSize(200,20);
        tfCol.setLayout(null);
        tfCol.setLocation(20,100);
        tfCol.setText("13");


        tfRow = new JTextField();
        tfRow.setSize(200,20);
        tfRow.setLayout(null);
        tfRow.setLocation(20,200);
        tfRow.setText("10");


        tfBalls = new JTextField();
        tfBalls.setSize(200,20);
        tfBalls.setLayout(null);
        tfBalls.setLocation(20,300);
        tfBalls.setText("3");


        tfPlayers = new JTextField();
        tfPlayers.setSize(200,20);
        tfPlayers.setLayout(null);
        tfPlayers.setLocation(20,400);
        tfPlayers.setText("2");

        tfSleepOfBall = new JTextField();
        tfSleepOfBall.setSize(200,20);
        tfSleepOfBall.setLayout(null);
        tfSleepOfBall.setLocation(20,500);
        tfSleepOfBall.setText("300");

        tfSleepOfPlayer = new JTextField();
        tfSleepOfPlayer.setSize(200,20);
        tfSleepOfPlayer.setLayout(null);
        tfSleepOfPlayer.setLocation(20,600);
        tfSleepOfPlayer.setText("300");


        //InputPanel
        this.setSize(250,750);
        this.setLocation(0,0);
        this.setBackground(Color.GRAY);
        this.setLayout(null);

        this.add(tfCol);
        this.add(tfRow);
        this.add(tfPlayers);
        this.add(tfBalls);
        this.add(tfSleepOfBall);
        this.add(tfSleepOfPlayer);
        this.add(startButton);
        this.add(stopButton);
        this.add(title);
        this.add(parmXMap);
        this.add(parmYMap);
        this.add(parmBalls);
        this.add(players);
        this.add(sleepOfBall);
        this.add(sleepOfPlayer);
    }
}
