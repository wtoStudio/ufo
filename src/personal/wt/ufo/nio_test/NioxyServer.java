package personal.wt.ufo.nio_test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class NioxyServer {
    public static void main(String[] args) {
        ServerSocketChannel serverSocketChannel = null;
        Selector selector = null;
        try {
            serverSocketChannel = ServerSocketChannel.open();

            selector = Selector.open();

            serverSocketChannel.socket().bind(new InetSocketAddress(7777));

            serverSocketChannel.configureBlocking(false);

            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("服务端已启动");
        while (true){
            try {
                if(selector.select(2000) == 0){
                    System.out.println("没有接收到客户端的连接请求，继续监听。。。");
                    continue;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            for(Iterator<SelectionKey> keyIterator = selectionKeys.iterator(); keyIterator.hasNext();){
                SelectionKey selectionKey = keyIterator.next();
                if(selectionKey.isAcceptable()){
                    System.out.println("接收到客户端的连接请求");
                    try {
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(selectionKey.isReadable()){
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    try {
                        ByteBuffer buffer = (ByteBuffer) selectionKey.attachment();
                        socketChannel.read(buffer);
                        System.out.println("客户端发来数据：" + new String(buffer.array()));
                        buffer.flip();
                    } catch (IOException e) {
                        e.printStackTrace();
                        selectionKey.cancel();
                        try {
                            socketChannel.close();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                keyIterator.remove();
            }
        }
    }
}
