import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class Sort implements SortMBean {

    private int threads = 3;


    List<SimpleThread> listThreads = new ArrayList<SimpleThread>();

    @Override
    public void sort() {
        while(true) {
            if(listThreads.size() > threads) {
                SimpleThread simpleThread = listThreads.get(listThreads.size()-1);
                simpleThread.stopRunning();
                listThreads.remove(simpleThread);
            } else if (listThreads.size() < threads) {
                SimpleThread simpleThread = new SimpleThread();
                listThreads.add(simpleThread);
                new Thread(simpleThread).start();
            }
            System.out.println(toString());
            MemoryClass.odsetek();
            try {
                sleep(1000);
            } catch (InterruptedException e) {}
        }


    }





    @Override
    public void setMaxSize(int maxSize) {
        RandomLista.setRange(maxSize);
    }

    @Override
    public int getMaxSize() {
        return RandomLista.getMax();
    }

    @Override
    public void setThread(int threads) {
        this.threads = threads;
    }

    @Override
    public int getThread() {
        return threads;
    }


    @Override
    public String toString() {
        return "------------------- \n" +
                "Threads: " + this.threads + "\n" +
                "Range: " + RandomLista.getRange();
    }
}
