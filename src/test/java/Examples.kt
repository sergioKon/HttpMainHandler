import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import java.io.RandomAccessFile
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel

class SampleTest {

    @Test
    fun writeData() {

        var buffer : MappedByteBuffer;
        var memoryFile = RandomAccessFile("shared_memory.dat", "rw")

        var urls = arrayOf("/root","/login", "/protected");
        var len=0L;
        for( v in urls){
            len+=v.toByteArray().size
        }
            buffer = memoryFile.getChannel().map(FileChannel.MapMode.READ_WRITE, 0, len+10);
            buffer.putInt(len.toInt());
            for( v in  urls){
            buffer.put(v.toByteArray());
        }
    }
 @Test
 fun readData() {
   var memoryFile = RandomAccessFile("shared_memory.dat", "rw")
     var buffer : MappedByteBuffer=  memoryFile.getChannel().map(FileChannel.MapMode.READ_WRITE, 0, 2048);

     var len= buffer.getInt();
     var value= ByteArray(len);

     buffer.get(value);
     println("Value at position 1: " + String(value));
     memoryFile.close();
 }
}

