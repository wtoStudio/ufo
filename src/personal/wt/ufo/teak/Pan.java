package personal.wt.ufo.teak;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

public class Pan extends JPanel {

    public static final int WIDTH = 900;
    public static final int HEIGHT = 650;

    private Teak teak = new Teak();

    private JLabel label = new JLabel();

    private ImageIcon imageIcon;

    public Pan(){
        this.setLayout(null);
        String filePath = System.getProperty("user.dir") + File.separator + "images" + File.separator + "watermelon_24.png";
        System.out.println(filePath);
        imageIcon = new ImageIcon(filePath);
        this.label.setIcon(imageIcon);
        label.setBounds(100, 100, 24, 24);
        this.add(label);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.BLACK);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()){
                    case KeyEvent.VK_W:{
                        Pan.this.teak.setDirection(Direction.UP);
                        //Pan.this.teak.getPosition().y -= Pan.this.teak.getSpeed();
                        Pan.this.teak.setMoving(true);
                        break;
                    }
                    case KeyEvent.VK_S:{
                        Pan.this.teak.setDirection(Direction.DOWN);
                        //Pan.this.teak.getPosition().y += Pan.this.teak.getSpeed();
                        Pan.this.teak.setMoving(true);
                        break;
                    }
                    case KeyEvent.VK_A:{
                        Pan.this.teak.setDirection(Direction.LEFT);
                        //Pan.this.teak.getPosition().x -= Pan.this.teak.getSpeed();
                        Pan.this.teak.setMoving(true);
                        break;
                    }
                    case KeyEvent.VK_D:{
                        Pan.this.teak.setDirection(Direction.RIGHT);
                        //Pan.this.teak.getPosition().x += Pan.this.teak.getSpeed();
                        Pan.this.teak.setMoving(true);
                        break;
                    }
                    case KeyEvent.VK_SPACE: {
                        Pan.this.teak.shoot();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()){
                    case KeyEvent.VK_W:{
                    }
                    case KeyEvent.VK_S:{
                    }
                    case KeyEvent.VK_A:{
                    }
                    case KeyEvent.VK_D:{
                        Pan.this.teak.setMoving(false);
                        break;
                    }
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        teak.paint(g);
        teak.getBulletList().forEach(e -> e.paint(g));
        g.drawImage(imageIcon.getImage(), 200, 200, null);
    }

    public Teak getTeak() {
        return teak;
    }

    public void setTeak(Teak teak) {
        this.teak = teak;
    }
}
