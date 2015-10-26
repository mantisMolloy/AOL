package first;

import net.openhft.chronicle.bytes.MappedBytesStore;
import net.openhft.chronicle.bytes.MappedFile;

import java.io.IOException;

/**
 * Created by tmolloy on 28/09/2015.
 */
public class ShmToggleMain {

    static final boolean set = Boolean.getBoolean("set");
    static final int runs = Integer.getInteger("runs", 10000);

    public static void main(String[] args) throws IOException {

        MappedFile mappedFile = MappedFile.mappedFile("mapped", 64*1024);
        MappedBytesStore mbb = mappedFile.acquireByteStore(0);
        int from = set ? 0 : 1;
        int to = set ? 1 : 0;

        for(int i = 0; i<10_000; i++){
            while (!mbb.compareAndSwapInt(0L, from, to)){
                Thread.yield();
            }
        }
        System.out.println("Toggleing now");
        long start = System.nanoTime();
        int runs = 10_000;

        for(int i = 0; i<runs; i++){
            while(!mbb.compareAndSwapInt(0L,from,to));
        }
        long time = System.nanoTime() -start;
        System.out.println("toggle took an average of" + time/runs + "ns");

    }
}
