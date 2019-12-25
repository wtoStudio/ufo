package personal.wt.ufo.teak;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Teak {

    private int r = 16;

    private int x = this.r;

    private int y = this.r;

    private Color color = Color.WHITE;

    private Direction direction = Direction.RIGHT;

    private int speed = 4;

    private boolean isMoving = false;

    private CopyOnWriteArrayList<Bullet> bulletList = new CopyOnWriteArrayList<>();

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

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

    public CopyOnWriteArrayList<Bullet> getBulletList() {
        return bulletList;
    }

    public void paint(Graphics g){
        g.setColor(this.color);
        Util.paintOval(this.x, this.y, this.r, true, g);
        paintDirectionLine(g);
    }

    public void move(){
        if(isMoving){
            if(this.direction == Direction.UP){
                this.y -= this.getSpeed();
            }else if(this.direction == Direction.DOWN){
                this.y += this.getSpeed();
            }else if(this.direction == Direction.LEFT){
                this.x -= this.getSpeed();
            }else{
                this.x += this.getSpeed();
            }
        }
    }

    public void paintDirectionLine(Graphics g){
        g.setColor(Color.RED);
        switch (this.direction){
            case UP:{
                g.drawLine(this.x, this.y, this.x, this.y-this.r);
                break;
            }
            case DOWN:{
                g.drawLine(this.x, this.y, this.x, this.y+this.r);
                break;
            }
            case LEFT:{
                g.drawLine(this.x, this.y, this.x-this.r, this.y);
                break;
            }
            case RIGHT:{
                g.drawLine(this.x, this.y, this.x+this.r, this.y);
                break;
            }
        }
    }

    public void shoot(){
        /*if(this.getBulletList().size() > 2){
            return;
        }*/
        int bulletX, bulletY;
        Direction bullectDirection;
        if(this.direction == Direction.UP){
            bullectDirection = Direction.UP;
            bulletX = this.x;
            bulletY = this.y - this.r;
        }else if(this.direction == Direction.DOWN){
            bullectDirection = Direction.DOWN;
            bulletX = this.x;
            bulletY = this.y + this.r;

        }else if(this.direction == Direction.LEFT){
            bullectDirection = Direction.LEFT;
            bulletX = this.x - this.r;
            bulletY = this.y;

        }else{
            bullectDirection = Direction.RIGHT;
            bulletX = this.x + this.r;
            bulletY = this.y;
        }
        Bullet bullet = new Bullet(this, bulletX, bulletY, bullectDirection);
        this.getBulletList().add(bullet);
    }
}
