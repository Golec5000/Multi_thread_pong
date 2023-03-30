package mainMap;

import gui.GamePanel;
import gui.ScorePanel;
import objects.Ball;
import objects.BallGenerator;
import objects.MovingObjects;
import objects.Player;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class MainMap {

    private final GamePanel gamePanel;
    private ArrayList<Cord> magazineOfCords,tempCords,magOfCordsForBalls;
    private ArrayList<MovingObjects> team1,team2, balls;
    public ArrayList<ScorePanel> left, right;
    private int idOfBall = 0;
    public BallGenerator ballGenerator;

    private int sumPointsTeam1 = 0, sumPointsTeam2 = 0;

    public MainMap(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        this.ballGenerator = new BallGenerator(this);
    }

    public void creatCords(){
        magazineOfCords = new ArrayList<>();
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;
        while(col < gamePanel.getWidthMap() && row < gamePanel.getHeightMap()){

            magazineOfCords.add(new Cord(x,y));
            col++;
            x += gamePanel.getxComp();

            if(col == gamePanel.getWidthMap()){
                col = 0;
                x = 0;
                y += gamePanel.getyComp();
                row++;
            }

        }
    }

    public void createScores(){

        left = new ArrayList<>();
        right = new ArrayList<>();

        int xEnd = (gamePanel.getWidthMap() * gamePanel.getxComp() - 40);

        for(Cord c : magazineOfCords){
            if(c.getX() == 0) left.add(new ScorePanel(0,c.getY()));
            if(c.getX() == xEnd) right.add(new ScorePanel(xEnd,c.getY()));
        }

    }

    public void addScores(){
        for(ScorePanel sp : left)
            gamePanel.add(sp);
        for(ScorePanel sp : right)
            gamePanel.add(sp);
    }

    public void creatStartCordsForBalls(){
        balls = new ArrayList<>();
        magOfCordsForBalls = new ArrayList<>();
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;
        while(col < gamePanel.getWidthMap() && row < gamePanel.getHeightMap()){

            if(col == (gamePanel.getWidthMap()/2)) magOfCordsForBalls.add(new Cord(x,y));
            col++;
            x += gamePanel.getxComp();

            if(col == gamePanel.getWidthMap()){
                col = 0;
                x = 0;
                y += gamePanel.getyComp();
                row++;
            }

        }
    }

    public void creatTeam1(){
        team1 = new ArrayList<>();

        //tworzenie pomocniczej tablicy współrzędnych
        tempCords = new ArrayList<>();
        for (Cord c : magazineOfCords)
            if (c.getX() == 40)
                tempCords.add(new Cord(c.getX(),c.getY()));

        //tworzenie graczy do team1
        createTeams(tempCords,team1);
    }

    public void creatTeam2(){
        team2 = new ArrayList<>();

        //tworzenie pomocniczej tablicy współrzędnych
        tempCords = new ArrayList<>();
        for (Cord c : magazineOfCords)
            if (c.getX() == (gamePanel.getWidthMap() * gamePanel.getxComp() - 80))
                tempCords.add(new Cord(c.getX(),c.getY()));

        //tworzenie graczy do team2
        createTeams(tempCords,team2);
    }

    public void startTeamPlay(ArrayList <MovingObjects> team){

        for(MovingObjects p : team) p.start();

    }

    public void createTeams(ArrayList<Cord> temp, ArrayList<MovingObjects> team){

        int idNumber = 0;

        for(int i = 0; i < gamePanel.getNumberOfPlayers(); i++ ){

            int random = new Random().nextInt(temp.size());
            idNumber++;

            team.add(new Player(temp.get(random).getX(), temp.get(random).getY(),this,idNumber,gamePanel.getPlayerDelay()));

            blockStartCords(temp.get(random).getX(),temp.get(random).getY());

            temp.remove(random);

        }
        temp.clear();

    }

    public void blockStartCords(int x, int y){

        for(Cord c : magazineOfCords)
            if(c.getX() == x && c.getY() == y)
                c.setOccupied(true);

    }

    public synchronized void stopCreatingBalls(){

        if (balls.size() == gamePanel.getHeightMap() ){
            try {
                wait();
            } catch (InterruptedException e) {
                
            }
        }

    }

    public synchronized void creatBalls(){

        if(balls.size() <= gamePanel.getNumberOfBalls()) {

            for (Cord c : magOfCordsForBalls) {
                if (!c.isLineOccupied()) {
                    idOfBall++;
                    balls.add(new Ball(c.getX(), c.getY(), this, idOfBall, gamePanel.getBallDelay()));

                    c.setLineOccupied(true);
                }
            }

        }

    }
    public synchronized void deleteBall(MovingObjects movingObjects){
        for(Cord c : magOfCordsForBalls) if(c.getY() == movingObjects.getY()) c.setLineOccupied(false);
        balls.remove(movingObjects);
        notify();
    }

    public void endRunning(){

        //wyłącznie wątków graczy
        for (MovingObjects p : team1) p.terminate();
        team1.clear();

        for (MovingObjects p : team2) p.terminate();
        team2.clear();

        //wyłącznie wątków piłek
        for(MovingObjects b : balls) if(b instanceof Ball) ((Ball) b).manualTerminate();
        balls.clear();

        //czyszczenie zajętych linii
        for(Cord c : magOfCordsForBalls) c.setLineOccupied(false);

    }

    public void clearLabels(){
        //resetowanie bramek
        for(ScorePanel sp : left) gamePanel.remove(sp);
        left.clear();
        for(ScorePanel sp : right) gamePanel.remove(sp);
        right.clear();
    }
    public void startGenerator(){
        if(ballGenerator.isRunning()) return;
        ballGenerator.setRunning(true);
        ballGenerator.start();
    }

    public void endingSum(){

        for(ScorePanel sp : left) sumPointsTeam1 += sp.getPoint();

        for(ScorePanel sp : right) sumPointsTeam2 += sp.getPoint();

    }

    public void drawMap(Graphics2D g2){

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while(col < gamePanel.getWidthMap() && row < gamePanel.getHeightMap()){

            if(col >= 1 && col <= gamePanel.getWidthMap() -2  && col != (gamePanel.getWidthMap() / 2)){

                g2.setColor(Color.GREEN);
                g2.drawRect(x,y,gamePanel.getxComp(),gamePanel.getyComp());

            }else if(col == (gamePanel.getWidthMap() / 2)){

                g2.setColor(Color.BLACK);
                g2.drawRect(x,y,gamePanel.getxComp(),gamePanel.getyComp());

            }

            col++;
            x += gamePanel.getxComp();

            if(col == gamePanel.getWidthMap()){

                col = 0;
                x = 0;
                y += gamePanel.getyComp();
                row++;

            }

        }
    }
    public void drawTeams(Graphics2D g2){
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;
        while(col < gamePanel.getWidthMap() && row < gamePanel.getHeightMap()){

            g2.setColor(Color.BLACK);

            for(MovingObjects p : team1)
                if(p.getX() == x && p.getY() == y )
                    g2.fillRect(x,y, gamePanel.getxComp(), gamePanel.getyComp());

            for(MovingObjects p : team2)
                if(p.getX() == x && p.getY() == y )
                    g2.fillRect(x,y, gamePanel.getxComp(), gamePanel.getyComp());

            col++;
            x += gamePanel.getxComp();

            if(col == gamePanel.getWidthMap()){
                col = 0;
                x = 0;
                y += gamePanel.getyComp();
                row++;
            }

        }
    }
    public synchronized void drawBalls(Graphics2D g2){
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;
        while(col < gamePanel.getWidthMap() && row < gamePanel.getHeightMap()){
            g2.setColor(Color.DARK_GRAY);
            if(!balls.isEmpty()) {
                for (MovingObjects p : balls) {
                    if (p.getX() == x && p.getY() == y )
                        g2.fillRect(x, y, gamePanel.getxComp(), gamePanel.getyComp());
                }
            }

            col++;
            x += gamePanel.getxComp();

            if(col == gamePanel.getWidthMap()){
                col = 0;
                x = 0;
                y += gamePanel.getyComp();
                row++;
            }

        }
    }

    public synchronized ArrayList<Cord> getMagazineOfCords() {
        return magazineOfCords;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public ArrayList<MovingObjects> getTeam1() {
        return team1;
    }

    public ArrayList<MovingObjects> getTeam2() {
        return team2;
    }

    public synchronized ArrayList<Cord> getMagOfCordsForBalls() {
        return magOfCordsForBalls;
    }

    public synchronized ArrayList<ScorePanel> getLeft() {
        return left;
    }

    public synchronized ArrayList<ScorePanel> getRight() {
        return right;
    }

    public int getSumPointsTeam1() {
        return sumPointsTeam1;
    }

    public int getSumPointsTeam2() {
        return sumPointsTeam2;
    }
}
