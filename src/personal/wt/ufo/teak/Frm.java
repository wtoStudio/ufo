package personal.wt.ufo.teak;

import javax.swing.*;
import java.awt.*;

/**
 * @author ttb
 */
public class Frm extends JFrame {
    private Pan pan = new Pan();
    private Timer timer;
    public Frm(){
        this.setTitle("Tea");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(pan, BorderLayout.CENTER);
        this.setVisible(true);
        this.setResizable(false);
        this.pack();

        timer = new Timer(40, actionEvent -> {
            pan.getTeak().move();
            pan.getTeak().getBulletList().forEach(e -> e.move());
            pan.repaint();
        });
        timer.start();
    }

    public Pan getPan() {
        return pan;
    }

    public void setPan(Pan pan) {
        this.pan = pan;
    }

    public static void main(String[] args) {
        Frm frame = new Frm();
        frame.getPan().requestFocus();
    }
}
