package personal.wt.ufo.ball;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame {
    public MyPanel myPanel;
    public MyFrame(String title) {
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myPanel = new MyPanel();
        this.add(myPanel, BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();
        JMenuItem showLineMenuItem = new JMenuItem("隐藏连线轨迹");
        showLineMenuItem.addActionListener((event)->{
            switch (event.getActionCommand()){
                case "显示连线轨迹":{
                    Object source = event.getSource();
                    JMenuItem menuItem = (JMenuItem) source;
                    menuItem.setText("隐藏连线轨迹");
                    myPanel.showLine = true;
                    break;
                }
                case "隐藏连线轨迹":{
                    Object source = event.getSource();
                    JMenuItem menuItem = (JMenuItem) source;
                    menuItem.setText("显示连线轨迹");
                    myPanel.showLine = false;
                    break;
                }
            }
        });
        JMenuItem showGuidaoMenuItem = new JMenuItem("隐藏轨道");
        showGuidaoMenuItem.addActionListener((event)->{
            switch (event.getActionCommand()){
                case "显示轨道":{
                    Object source = event.getSource();
                    JMenuItem menuItem = (JMenuItem) source;
                    menuItem.setText("隐藏轨道");
                    myPanel.showGuidao = true;
                    break;
                }
                case "隐藏轨道":{
                    Object source = event.getSource();
                    JMenuItem menuItem = (JMenuItem) source;
                    menuItem.setText("显示轨道");
                    myPanel.showGuidao = false;
                    break;
                }
            }
        });
        JMenuItem resetMenuItem = new JMenuItem("重新绘制");
        resetMenuItem.addActionListener(event-> myPanel.lineList.clear());
        JMenu adjustMenu = new JMenu("调整(A)");
        JMenu colorMenu = new JMenu("颜色(C)");
        JMenu speedMenu = new JMenu("速度(P)");
        JMenuItem redMenuItem = new JMenuItem("红色");
        redMenuItem.addActionListener(new ChangeColorActionListener());
        JMenuItem blueMenuItem = new JMenuItem("蓝色");
        blueMenuItem.addActionListener(new ChangeColorActionListener());
        JMenuItem pinkMenuItem = new JMenuItem("橘色");
        pinkMenuItem.addActionListener(new ChangeColorActionListener());
        colorMenu.add(redMenuItem);
        colorMenu.add(blueMenuItem);
        colorMenu.add(pinkMenuItem);
        colorMenu.addSeparator();
        adjustMenu.add(showLineMenuItem);
        adjustMenu.add(showGuidaoMenuItem);
        adjustMenu.add(resetMenuItem);
        JMenuItem customColorMenuItem = new JMenuItem("自定义颜色");
        customColorMenuItem.addActionListener(new ChangeColorActionListener());
        colorMenu.add(customColorMenuItem);
        adjustMenu.add(colorMenu);
        adjustMenu.add(speedMenu);
        menuBar.add(adjustMenu);
        this.setJMenuBar(menuBar);
        this.pack();
        this.setResizable(false);
        this.setVisible(true);

        while (true){
            myPanel.angleA += myPanel.stepA;
            myPanel.angleB += myPanel.stepB;
            myPanel.repaint();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private class ChangeColorActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()){
                case "红色":{
                    myPanel.color = Color.RED;
                    break;
                }
                case "蓝色":{
                    myPanel.color = Color.BLUE;
                    break;
                }
                case "橘色":{
                    myPanel.color = Color.ORANGE;
                    break;
                }
                case "自定义颜色":{
                    JOptionPane.showMessageDialog(MyFrame.this, "尚未完成!", "warning", JOptionPane.WARNING_MESSAGE);
                    break;
                }
            }
        }
    }
}
