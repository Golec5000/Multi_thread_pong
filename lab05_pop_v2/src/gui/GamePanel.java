package gui;

import mainMap.*;
import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private final int xComp = 40;
    private final int yComp = 40;
    private int widthMap, heightMap, numberOfPlayers, numberOfBalls , playerDelay, ballDelay;

    private boolean runOfGame;

    public MainMap mainMap = new MainMap(this);

    public GamePanel() {
        this.setSize(1100,750);
        this.setLocation(250,0);
        this.setBackground(new Color(123,55,250));
        this.setLayout(null);
        this.runOfGame = false;
    }


    public void gameLoop(){
        mainMap.creatCords();
        mainMap.createScores();
        mainMap.creatStartCordsForBalls();
        mainMap.creatTeam1();
        mainMap.creatTeam2();

        mainMap.addScores();

        runOfGame = true;

        mainMap.startTeamPlay(mainMap.getTeam1());
        mainMap.startTeamPlay(mainMap.getTeam2());
        mainMap.startGenerator();

        while (runOfGame) {
            try {
                Thread.sleep(1000 / 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            repaint();
        }

    }
    public void endSymulation(){
        runOfGame = false;

        mainMap.endRunning();
        mainMap.endingSum();
        mainMap.clearLabels();

        if (mainMap.getSumPointsTeam1() == mainMap.getSumPointsTeam2())
            JOptionPane.showMessageDialog(null,"Remis","Koniec",JOptionPane.INFORMATION_MESSAGE);
        else if(mainMap.getSumPointsTeam1() < mainMap.getSumPointsTeam2())
            JOptionPane.showMessageDialog(null,"Wygrał team lewy ! " + mainMap.getSumPointsTeam1() + ":" + mainMap.getSumPointsTeam2(),"Koniec",JOptionPane.INFORMATION_MESSAGE);
        else
            JOptionPane.showMessageDialog(null,"Wygrał team prawy ! " + mainMap.getSumPointsTeam2() + ":" + mainMap.getSumPointsTeam1(),"Koniec",JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        if(mainMap != null){
            mainMap.drawMap(g2);
            mainMap.drawTeams(g2);
            mainMap.drawBalls(g2);
        }

    }

    public int getWidthMap() {
        return widthMap;
    }

    public void setWidthMap(int widthMap) {
        this.widthMap = widthMap;
    }

    public int getHeightMap() {
        return heightMap;
    }

    public void setHeightMap(int heightMap) {
        this.heightMap = heightMap;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public int getNumberOfBalls() {
        return numberOfBalls;
    }

    public void setNumberOfBalls(int numberOfBalls) {
        this.numberOfBalls = numberOfBalls;
    }

    public int getxComp() {
        return xComp;
    }

    public int getyComp() {
        return yComp;
    }

    public int getPlayerDelay() {
        return playerDelay;
    }

    public void setPlayerDelay(int playerDelay) {
        this.playerDelay = playerDelay;
    }

    public int getBallDelay() {
        return ballDelay;
    }

    public void setBallDelay(int ballDelay) {
        this.ballDelay = ballDelay;
    }

    public boolean isRunOfGame() {
        return runOfGame;
    }
}
