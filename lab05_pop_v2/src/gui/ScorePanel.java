package gui;

import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JLabel {
    private int point = 0;
    private final int locationY;
    public ScorePanel(int x, int y){

        this.locationY = y;

        this.setLayout(null);
        this.setLocation(x,locationY);
        this.setSize(40,40);

        this.setText(String.valueOf(point));

        this.setHorizontalAlignment(CENTER);
        this.setVerticalAlignment(CENTER);

        this.setOpaque(true);
        this.setBackground(Color.cyan);

    }
    public synchronized void setScore(){
        point++;
        this.setText(String.valueOf(point));
    }

    public int getLocationY() {
        return locationY;
    }

    public int getPoint() {
        return point;
    }
    public void resetPoints(){
        setText(String.valueOf(0));
    }
}
