package application;

import gui.GameFrame;
import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Daniel Ryszkoski
 */

public class App {
    public static void main(String[] args) throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait(() ->{
            GameFrame gm = new GameFrame();
            gm.symulation();
        });
    }
}