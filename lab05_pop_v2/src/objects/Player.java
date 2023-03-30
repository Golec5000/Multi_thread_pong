package objects;

import mainMap.MainMap;
import mainMap.Cord;
import java.util.Objects;


public class Player extends MovingObjects{


    public Player(int x, int y, MainMap mainMap, int id, int delay){
        super(x,y,mainMap,delay);
        setPriority(2);
        setName(String.valueOf(id));

    }

    @Override
    public void run(){
        while(isRunning()) {
            move();
            try {
                Thread.sleep(getDelay());
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    @Override
    public void move(){

        if(getY() <= 0 && getyMove() < 0) setyMove(getyMove() * - 1);
        if(getY() >= mainMap.getGamePanel().getHeightMap() * mainMap.getGamePanel().getyComp() - 40 && getyMove() > 0) setyMove(getyMove() * - 1);

        int testYMove = getY() + getyMove();
        
        Cord temp1 = null,temp2 = null;
        //Pobieranie obecnej wspłórzędnej
        for(Cord c : mainMap.getMagazineOfCords()) {
            if(c.getY() != getY() || c.getX() != getX()) continue;
            temp1 = c;
            break;
        }

        //Pobieranie współrzędnej na którą chcemy się przemieścić
        for(Cord c : mainMap.getMagazineOfCords()) {
            if(c.getY() != testYMove || c.getX() != getX()) continue;
            temp2 = c;
            break;
        }
        if(Objects.requireNonNull(temp2).move(this,testYMove))
            Objects.requireNonNull(temp1).free();

    }
    @Override
    public void terminate(){
        setRunning(false);
        interrupt();
    }

}
