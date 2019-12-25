package personal.wt.ufo.teak;

import java.awt.*;

public class Util {
    public static void paintOval(int centerX, int centerY, int r, boolean full, Graphics g){
        if(full){
            g.fillOval(centerX - r, centerY - r, r << 1, r << 1);
        }else{
            g.drawOval(centerX - r, centerY - r, r << 1, r << 1);
        }
    }

    public static void paintRect(int centerX, int centerY, int r, boolean full, Graphics g){
        if(full){
            g.fillRect(centerX - r, centerY - r, r << 1, r << 1);
        }else{
            g.drawRect(centerX - r, centerY - r, r << 1, r << 1);
        }
    }
}
