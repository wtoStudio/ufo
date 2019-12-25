package personal.wt.ufo.card.ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private GamePanel gamePanel = new GamePanel();

    public MainFrame(String title){
        this.setLayout(new FlowLayout(0, 0, 0));
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(gamePanel);
        this.pack();
        this.setVisible(true);
        this.setResizable(true);
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
}
