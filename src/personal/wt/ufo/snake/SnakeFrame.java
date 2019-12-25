package personal.wt.ufo.snake;

import javax.swing.*;
import java.awt.*;

public class SnakeFrame extends JFrame {
    private SnakePanel snakePanel = new SnakePanel();
    private Timer timer;
    public SnakeFrame(String title){
        initFrame(title);
        start();
    }

    private void initFrame(String title){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle(title);
        this.add(snakePanel, BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);
        this.setResizable(true);
    }

    private void start(){
        timer = new Timer(200, e -> {
            snakePanel.getSnake().move();
            if(snakePanel.getSnake().isArrivedAt(snakePanel.getFood())){
                SnakeNode lastNode = snakePanel.getSnake().getSnakeNodeList().get(snakePanel.getSnake().getSnakeNodeList().size() - 1);
                Point lastPoint = new Point(lastNode.getCurrentPoint());
                snakePanel.getSnake().getSnakeNodeList().add(new SnakeNode(Color.BLUE, new Point(lastPoint.x, lastPoint.y), lastNode.getDirection()));
                snakePanel.makeFood();
            }
            if(snakePanel.getSnake().getSnakeNodeList().get(0).getCurrentPoint().y < 0){
                timer.stop();
            }
            snakePanel.repaint();
        });
        timer.start();
    }

    public SnakePanel getSnakePanel() {
        return snakePanel;
    }

    public void setSnakePanel(SnakePanel snakePanel) {
        this.snakePanel = snakePanel;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }
}
