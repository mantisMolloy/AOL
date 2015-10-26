package first;

import net.openhft.chronicle.bytes.MappedBytesStore;
import net.openhft.chronicle.bytes.MappedFile;

import java.io.IOException;
import java.nio.MappedByteBuffer;

/**
 * Created by tmolloy on 28/09/2015.
 */
public class ShmMain {
    public static void main(String[] args) throws IOException{
        MappedFile mappedFile = MappedFile.mappedFile("mapped", 64*1024);
        MappedBytesStore mbb = mappedFile.acquireByteStore(0);
        System.out.println(mbb.compareAndSwapInt(0L, 0, 1));

        //UnsafeMain.UNSAFE.getInt(0);


    }
}
