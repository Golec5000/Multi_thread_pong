package mainMap;

import objects.Ball;
import objects.MovingObjects;
import objects.Player;

public class Cord {
    protected int x,y;
    private boolean isOccupied;
    private boolean lineOccupied;
    public Cord(int x, int y){
        this.x = x;
        this.y = y;
        this.isOccupied = false;
        this.lineOccupied = false;
    }


    public synchronized boolean move(MovingObjects movingObj, int tempMove){
        if(isOccupied){
            if (movingObj instanceof Player) movingObj.setyMove(movingObj.getyMove() * - 1);
            if (movingObj instanceof Ball) movingObj.setxMove(movingObj.getxMove() * -1);
            return false;
        }
        isOccupied = true;
        if (movingObj instanceof Player) movingObj.setY(tempMove);
        if (movingObj instanceof Ball) movingObj.setX(tempMove);
        return true;
    }

    public synchronized void free(){
        isOccupied = false;
    }
    public synchronized void freeLine(){lineOccupied = false;}

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public boolean isLineOccupied() {
        return lineOccupied;
    }

    public void setLineOccupied(boolean lineOccupied) {
        this.lineOccupied = lineOccupied;
    }
}
