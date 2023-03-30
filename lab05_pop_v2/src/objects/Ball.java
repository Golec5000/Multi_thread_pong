package objects;

import gui.ScorePanel;
import mainMap.MainMap;
import mainMap.Cord;

import java.util.Objects;

public class Ball extends MovingObjects{

    private final int xStart,yStart;
    public Ball(int x, int y, MainMap mainMap, int id, int delay){
        super(x,y,mainMap,delay);
        setPriority(3);
        setName(String.valueOf(id));
        this.xStart = x;
        this.yStart = y;

        if(id % 2 == 0 ) setxMove(40);
        else setxMove(-40);
        start();
    }

    @Override
    public void run(){
        while(isRunning()){
            move();
            endLine();
            try {
                Thread.sleep(getDelay());
            } catch (InterruptedException e) {
                break;
            }
        }
    }
    @Override
    public void move(){

        int testXMove = getX() + getxMove();

        Cord temp1 = null,temp2 = null;
        //Pobieranie obecnej wspłórzędnej
        for(Cord c : mainMap.getMagazineOfCords()) {
            if(c.getY() != getY() || c.getX() != getX()) continue;
            temp1 = c;
        }

        //Pobieranie współrzędnej na którą chcemy się przemieścić
        for(Cord c : mainMap.getMagazineOfCords()) {
            if(c.getY() != getY() || c.getX() != testXMove) continue;
            temp2 = c;
        }
        try {

            if (Objects.requireNonNull(temp2).move(this, testXMove))
                Objects.requireNonNull(temp1).free();

        }catch (NullPointerException ignored){

        }

    }

    public void endLine(){
        int firstCol = 0;
        int lastCol = mainMap.getGamePanel().getWidthMap() * mainMap.getGamePanel().getxComp() - 40;
        if(getX() == firstCol || getX() == lastCol) {

            for(Cord c : mainMap.getMagOfCordsForBalls())
                if(c.getX() == xStart && c.getY() == yStart) c.freeLine();

            for(Cord c : mainMap.getMagazineOfCords()) {
                if (c.getX() == getX() && c.getY() == getY()) {

                    if (c.getX() == firstCol) {
                        for (ScorePanel sp : mainMap.getLeft())
                            if (getY() == sp.getLocationY())
                                sp.setScore();
                    }
                    if (c.getX() == lastCol) {
                        for (ScorePanel sp : mainMap.getRight())
                            if (getY() == sp.getLocationY())
                                sp.setScore();
                    }

                    c.free();
                }
            }

            terminate();
        }
    }


    public void manualTerminate(){
        setRunning(false);
        interrupt();
    }

    @Override
    public void terminate(){
        setRunning(false);
        mainMap.deleteBall(this);
        interrupt();
    }
}
