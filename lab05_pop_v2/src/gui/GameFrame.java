package gui;

import mainMap.MainMap;

import javax.swing.*;
import java.util.ArrayList;

public class GameFrame extends JFrame {

    private final InputPanel ip;
    private final GamePanel gp;

    private final ArrayList<Boolean> correctData = new ArrayList<>();
    private final ArrayList<Integer> correctWidth = new ArrayList<>();

    private int widthMap, heightMap, numberOfPlayers, numberOfBalls, playerDelay, ballDelay;

    public void symulation(){
        ip.startButton.addActionListener(e -> {
            gp.mainMap = new MainMap(gp);

            try{

                widthMap = Integer.parseInt(ip.tfCol.getText());
                heightMap = Integer.parseInt(ip.tfRow.getText());
                numberOfBalls = Integer.parseInt(ip.tfBalls.getText());
                numberOfPlayers = Integer.parseInt(ip.tfPlayers.getText());
                ballDelay = Integer.parseInt(ip.tfSleepOfBall.getText());
                playerDelay = Integer.parseInt(ip.tfSleepOfPlayer.getText());


                //sprawdzanie poprawności ilości kolumn
                if(!correctWidth.contains(widthMap)){
                    correctData.set(0,false);
                    JOptionPane.showMessageDialog(null,"zła ilość kolumn","Ostrzeżenie !",JOptionPane.WARNING_MESSAGE);
                }else{
                    correctData.set(0,true);
                    gp.setWidthMap(widthMap);
                }

                //sprawdzanie poprawności ilości wierszy
                if(heightMap >= 10 && heightMap <= 17){
                    correctData.set(1,true);
                    gp.setHeightMap(heightMap);
                }else {
                    correctData.set(1,false);
                    JOptionPane.showMessageDialog(null,"Ilość wierszy powinno być 10 - 17","Ostrzeżenie !",JOptionPane.WARNING_MESSAGE);
                }

                //sprawdzanie poprawności ilości piłek
                if(numberOfBalls < 3 ){
                    correctData.set(2,false);
                    JOptionPane.showMessageDialog(null,"Za mało piłek","Ostrzeżenie !",JOptionPane.WARNING_MESSAGE);
                } else if (numberOfBalls > heightMap) {
                    correctData.set(2,false);
                    JOptionPane.showMessageDialog(null,"Za dużo piłek ","Ostrzeżenie !",JOptionPane.WARNING_MESSAGE);
                }else {
                    correctData.set(2,true);
                    gp.setNumberOfBalls(numberOfBalls);
                }

                //sprawdzanie poprawności ilości graczy
                if(numberOfPlayers < 2 ){
                    correctData.set(3,false);
                    JOptionPane.showMessageDialog(null,"Za mało graczy","Ostrzeżenie !",JOptionPane.WARNING_MESSAGE);
                } else if (numberOfPlayers > heightMap) {
                    correctData.set(3,false);
                    JOptionPane.showMessageDialog(null,"Za dużo graczy ","Ostrzeżenie !",JOptionPane.WARNING_MESSAGE);
                }else {
                    correctData.set(3,true);
                    gp.setNumberOfPlayers(numberOfPlayers);
                }

                //sprawdzenie prędkoci wykonywania dla graczy i piłek

                if(ballDelay >= 300 && ballDelay <= 700){
                    correctData.set(4,true);
                    gp.setBallDelay(ballDelay);
                }else {
                    correctData.set(4,false);
                    JOptionPane.showMessageDialog(null,"Zła wartość opóżnienia ","Ostrzeżenie !",JOptionPane.WARNING_MESSAGE);
                }

                if(playerDelay >= 300 && playerDelay <= 700){
                    correctData.set(5,true);
                    gp.setPlayerDelay(playerDelay);
                }else {
                    correctData.set(5,false);
                    JOptionPane.showMessageDialog(null,"Zła wartość opóżnienia ","Ostrzeżenie !",JOptionPane.WARNING_MESSAGE);
                }

                if(!correctData.contains(false)){
                    fps();
                }

            }catch (NumberFormatException error){
                System.out.println(error.getMessage());
            }


        });
        ip.stopButton.addActionListener(e -> {
            gp.endSymulation();
            ip.startButton.setEnabled(true);
            ip.stopButton.setEnabled(false);
        });
    }
    public void fps(){
        ip.startButton.setEnabled(false);
        ip.stopButton.setEnabled(true);
        new Thread(gp::gameLoop).start();
    }

    public GameFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1350,750);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Lab05");
        this.setLayout(null);
        this.setVisible(true);


        ip = new InputPanel();
        gp = new GamePanel();

        //log data

        correctData.add(false);
        correctData.add(false);
        correctData.add(false);
        correctData.add(false);
        correctData.add(false);
        correctData.add(false);

        correctWidth.add(13);
        correctWidth.add(15);
        correctWidth.add(17);
        correctWidth.add(19);
        correctWidth.add(21);
        correctWidth.add(23);
        correctWidth.add(25);
        correctWidth.add(27);

        this.add(ip);
        this.add(gp);
    }

}
