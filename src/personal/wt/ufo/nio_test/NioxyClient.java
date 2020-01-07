package personal.wt.ufo.nio_test;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NioxyClient {
    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 7777);

        /*if(!socketChannel.connect(inetSocketAddress)){
            while (!socketChannel.finishConnect()){
                System.out.println("Do something!");
            }
        }*/

        socketChannel.connect(inetSocketAddress);
        if(socketChannel.finishConnect()){
            socketChannel.write(ByteBuffer.wrap("白日依山尽，黄河入海流。".getBytes()));
        }

        //System.in.read();
        JFrame frame = new JFrame("AAA");
        JButton button = new JButton("SEND");
        button.addActionListener(e->{
            try {
                socketChannel.write(ByteBuffer.wrap("欲穷千里目，更上一层楼。".getBytes()));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        JButton button2 = new JButton("EXIT");
        button2.addActionListener(e->System.exit(9));
        frame.setUndecorated(true);
        frame.add(button, BorderLayout.WEST);
        frame.add(button2, BorderLayout.EAST);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
