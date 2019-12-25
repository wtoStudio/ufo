package personal.wt.ufo.card;

import personal.wt.ufo.card.ui.MainFrame;
import javax.swing.*;

/**
 * @author ttb
 */
public class Main {
    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        MainFrame app = new MainFrame("斗地主");
        app.getGamePanel().requestFocus();
    }
}
