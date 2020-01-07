package personal.wt.ufo.nio_test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIO_Test_1 {
    public static void main(String[] args) throws Exception {
        //用NIO的方式复制文件
        FileInputStream fis = new FileInputStream("D:\\test1.txt");
        FileChannel inChannel = fis.getChannel();

        FileOutputStream fos = new FileOutputStream("D:\\test2.txt");
        FileChannel outChannel = fos.getChannel();
        
        ByteBuffer buffer = ByteBuffer.allocate(10);
        int read = 0;
        while(read != -1){
            buffer.clear();
            read = inChannel.read(buffer);
            System.out.println("read: " + read);
            buffer.flip();
            outChannel.write(buffer);
        }
        inChannel.close();
        outChannel.close();
    }
}
