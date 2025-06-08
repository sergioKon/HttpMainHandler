import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;

public class ShareMemoryTest {
    @Test
    void writeData() throws IOException {

        MappedByteBuffer buffer;
        try (RandomAccessFile memoryFile = new RandomAccessFile("shared_memory.dat", "rw")) {

            String[] urls = new String[]{"/root","/login", "/protected"};

            int len=0;
            for( String v:  urls){
                len+=v.getBytes().length;
            }
            buffer = memoryFile.getChannel().map(FileChannel.MapMode.READ_WRITE, 0, len+4);
            buffer.putInt(len+4);
            for( String v:  urls){
                buffer.put(v.getBytes());
            }
        }


    }

    @Test
    void  readData() throws IOException {
        RandomAccessFile memoryFile = new RandomAccessFile("shared_memory.dat", "rw");
        MappedByteBuffer buffer = memoryFile.getChannel().map(FileChannel.MapMode.READ_WRITE, 0, 2048);

        int len= buffer.getInt();
        byte[] value= new byte[len];

        buffer.get(value);
        System.out.println("Value at position 1: " + new String(value));
        memoryFile.close();
    }
}
