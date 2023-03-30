package objects;

import mainMap.MainMap;

public abstract class MovingObjects extends Thread{

    private int x,y, delay;
    private int yMove = 40;
    private int xMove;
    private boolean running;
    public final MainMap mainMap;
    public MovingObjects(int x, int y,MainMap mainMap, int delay){
        this.x = x;
        this.y = y;
        this.mainMap = mainMap;
        this.running = true;
        this.delay = delay;
        setDaemon(true);

    }

    public abstract void move();

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getyMove() {
        return yMove;
    }

    public void setyMove(int yMove) {
        this.yMove = yMove;
    }

    public abstract void terminate();

    public int getxMove() {
        return xMove;
    }

    public void setxMove(int xMove) {
        this.xMove = xMove;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public int getDelay() {
        return delay;
    }
}
