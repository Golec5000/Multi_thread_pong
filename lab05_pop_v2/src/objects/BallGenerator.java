package objects;

import mainMap.*;
import java.util.ConcurrentModificationException;


public class BallGenerator extends Thread{

    private final MainMap mainMap;
    private boolean running = false;

    public BallGenerator(MainMap mainMap){
        setDaemon(true);
        setPriority(1);
        this.mainMap = mainMap;
    }

    @Override
    public void run(){

        while (running){
            try {
                mainMap.stopCreatingBalls();
                mainMap.creatBalls();
            }catch (ConcurrentModificationException e){
                System.out.println(e.getMessage());
                return;
            }
        }

    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isRunning() {
        return running;
    }
}
