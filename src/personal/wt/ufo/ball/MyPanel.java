package personal.wt.ufo.ball;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import static java.lang.Math.*;

public class MyPanel extends JPanel {
    public static final int WIDTH = 1200;
    public static  final int HEIGHT = 800;

    public Color color = Color.WHITE;

    public double angleA = 0;
    public double stepA = PI/120;

    public double angleB = 0;
    public double stepB = PI/80;

    public java.util.List<Line> lineList = new ArrayList<>(1000000);

    public boolean showLine = true;

    public boolean showGuidao = true;

    public MyPanel(){
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(this.color);
        g.drawString("HELLO WORLD", 10, 20);

        if(showGuidao){
            //轨道中心
            paintOval(WIDTH >> 1, HEIGHT >> 1, 4, true, g);
            //轨道A
            paintOval(WIDTH >> 1, HEIGHT >> 1, (HEIGHT-100) >> 1, false, g);
            //轨道B
            paintOval(WIDTH >> 1, HEIGHT >> 1, (HEIGHT-650) >> 1, false, g);
        }

        Line line = new Line();

        Point pointA = c(WIDTH >> 1, HEIGHT >> 1, (HEIGHT-100) >> 1, angleA);
        //轨道A上的圆
        paintOval(pointA.x, pointA.y, 12, true, g);
        line.startX = pointA.x;
        line.startY = pointA.y;

        Point pointB = c(WIDTH >> 1, HEIGHT >> 1, (HEIGHT-650) >> 1, angleB);
        //轨道B上的圆
        paintOval(pointB.x, pointB.y, 12, true, g);
        line.endX = pointB.x;
        line.endY = pointB.y;
        lineList.add(line);

        if(showLine){
            lineList.forEach(l -> {
                g.drawLine(l.startX, l.startY, l.endX, l.endY);
            });
        }
    }

    private void paintOval(int centerX, int centerY, int r, boolean full, Graphics g){
        if(full){
            g.fillOval(centerX - r, centerY - r, r << 1, r << 1);
        }else{
            g.drawOval(centerX - r, centerY - r, r << 1, r << 1);
        }
    }

    private Point c(int centerX, int centerY, int r, double angle){
        Point point = new Point();
        point.x = (int) (centerX + r * cos(angle));
        point.y = (int) (centerY + r * sin(angle));
        return point;
    }
}
