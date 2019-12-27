package personal.wt.ufo.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SnakePanel extends JPanel {
    private static final int WIDTH = 450;

    private static final int HEIGHT = 600;

    private static final int CELL_SIZE = 20;

    private static final int ROW = HEIGHT/CELL_SIZE;

    private static final int COL = WIDTH/CELL_SIZE;

    private Snake snake = Snake.initSnake();

    private Point food;

    private static final String PROJECT_DIR = System.getProperty("user.dir");

    private Image foodImage = new ImageIcon(PROJECT_DIR + "\\images\\snakes\\apple.png").getImage();

    private Random random = new Random();

    private Map<Direction, Image> headIconMap = new HashMap<>();

    private Map<Direction, Image> bodyIconMap = new HashMap<>();

    public SnakePanel(){
        initIconMap();
        makeFood();
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.BLACK);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String input = e.getKeyChar() + "";
                Direction inputDirection = directionMap(input);
                if(inputDirection != null){
                    snake.getSnakeNodeList().get(0).setDirection(inputDirection);
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //initGrid(g);
        paintFood(g);
        paintSnake(snake, g);
    }

    private void initIconMap(){
        headIconMap.put(Direction.UP, new ImageIcon(PROJECT_DIR + "\\images\\snakes\\headup.png").getImage());
        headIconMap.put(Direction.DOWN, new ImageIcon(PROJECT_DIR + "\\images\\snakes\\headdown.png").getImage());
        headIconMap.put(Direction.LEFT, new ImageIcon(PROJECT_DIR + "\\images\\snakes\\headleft.png").getImage());
        headIconMap.put(Direction.RIGHT, new ImageIcon(PROJECT_DIR + "\\images\\snakes\\headright.png").getImage());

        bodyIconMap.put(Direction.UP, new ImageIcon(PROJECT_DIR + "\\images\\snakes\\vbody.png").getImage());
        bodyIconMap.put(Direction.DOWN, new ImageIcon(PROJECT_DIR + "\\images\\snakes\\vbody.png").getImage());
        bodyIconMap.put(Direction.LEFT, new ImageIcon(PROJECT_DIR + "\\images\\snakes\\hbody.png").getImage());
        bodyIconMap.put(Direction.RIGHT, new ImageIcon(PROJECT_DIR + "\\images\\snakes\\hbody.png").getImage());
    }

    private void initGrid(Graphics g){
        for(int r = 0; r < ROW; r++){
            for(int c = 0; c < COL; c++){
                g.drawRect(c * CELL_SIZE, r * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }
    }

    public void makeFood(){
        if(this.food == null){
            this.food = new Point(random.nextInt(COL-1), random.nextInt(ROW-1));
        }else{
            this.food.x = random.nextInt(COL-1);
            this.food.y = random.nextInt(ROW-1);
        }
    }

    private void paintFood(Graphics g){
        System.out.println("绘制FOOD:" + this.food);
        //g.setColor(Color.CYAN);
        //g.fillRect(food.x * CELL_SIZE, food.y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        g.drawImage(foodImage, food.x * CELL_SIZE, food.y * CELL_SIZE, food.x * CELL_SIZE + CELL_SIZE, food.y * CELL_SIZE + CELL_SIZE, 0, 0, 33, 33, null);
    }

    private void paintSnakeNode(SnakeNode snakeNode, Graphics g){
        //g.setColor(snakeNode.getColor());
        //g.fillRect(snakeNode.getCurrentPoint().x * CELL_SIZE, snakeNode.getCurrentPoint().y * CELL_SIZE, CELL_SIZE, CELL_SIZE);

        Image image;
        if(snakeNode.isHeader()){
            image = headIconMap.get(snakeNode.getDirection());
            g.drawImage(image, snakeNode.getCurrentPoint().x * CELL_SIZE, snakeNode.getCurrentPoint().y * CELL_SIZE, snakeNode.getCurrentPoint().x * CELL_SIZE + CELL_SIZE, snakeNode.getCurrentPoint().y * CELL_SIZE + CELL_SIZE, 0, 0, 33, 33, null);
        }else{
            image = bodyIconMap.get(snakeNode.getDirection());
            g.drawImage(image, snakeNode.getCurrentPoint().x * CELL_SIZE, snakeNode.getCurrentPoint().y * CELL_SIZE, snakeNode.getCurrentPoint().x * CELL_SIZE + CELL_SIZE, snakeNode.getCurrentPoint().y * CELL_SIZE + CELL_SIZE, 8, 8, 30, 30, null);
        }
    }

    private void paintSnake(Snake snake, Graphics g){
        snake.getSnakeNodeList().forEach(snakeNode -> paintSnakeNode(snakeNode, g));
    }

    private Direction directionMap(String input){
        Map<String, Direction> directionMap = new HashMap<>();
        directionMap.put("w", Direction.UP);
        directionMap.put("s", Direction.DOWN);
        directionMap.put("a", Direction.LEFT);
        directionMap.put("d", Direction.RIGHT);
        System.out.println("directionMap.get(" + input + "):" + directionMap.get(input));
        return directionMap.get(input);
    }

    public Snake getSnake() {
        return snake;
    }

    public Point getFood() {
        return food;
    }
}
