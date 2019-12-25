package personal.wt.ufo.snake;

import javax.swing.*;
import java.awt.*;

public class SnakeNode {
    private Color color = Color.WHITE;

    private Point currentPoint;

    private Direction direction;

    private Image image;

    private boolean isHeader = false;

    public SnakeNode(){}

    public SnakeNode(Color color, Point currentPoint, Direction direction){
        this.color = color;
        this.currentPoint = currentPoint;
        this.direction = direction;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Point getCurrentPoint() {
        return currentPoint;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public boolean isHeader() {
        return isHeader;
    }

    public void setHeader(boolean header) {
        isHeader = header;
    }
}
