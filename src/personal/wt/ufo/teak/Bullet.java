package personal.wt.ufo.teak;

import java.awt.*;

public class Bullet {
    private int size = 3;

    private int x;

    private int y;

    private Rectangle rectangle;

    private int speed = 20;

    private Direction direction;

    private Teak teak;

    public Bullet(){}

    public Bullet(Teak teak, int x, int y, Direction direction){
        this.teak = teak;
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
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

    public Rectangle getRectangle() {
        this.rectangle = new Rectangle(this.x - size, this.y - size, size * 2, size * 2);
        return this.rectangle;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Teak getTeak() {
        return teak;
    }

    public void setTeak(Teak teak) {
        this.teak = teak;
    }

    public void paint(Graphics g){
        Util.paintOval(this.x, this.y, size, true, g);
    }

    public void move(){
        if(this.direction == Direction.UP){
            this.y -= this.getSpeed();
            if(this.y<0 || this.y>Pan.HEIGHT){
                this.teak.getBulletList().remove(this);
            }
        }else if(this.direction == Direction.DOWN){
            this.y += this.getSpeed();
            if(this.y<0 || this.y>Pan.HEIGHT){
                this.teak.getBulletList().remove(this);
            }
        }else if(this.direction == Direction.LEFT){
            this.x -= this.getSpeed();
            if(this.x<0 || this.x>Pan.WIDTH){
                this.teak.getBulletList().remove(this);
            }
        }else{
            this.x += this.getSpeed();
            if(this.x<0 || this.x>Pan.WIDTH){
                this.teak.getBulletList().remove(this);
            }
        }
    }
}
