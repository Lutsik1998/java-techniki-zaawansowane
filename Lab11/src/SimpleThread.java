import com.labyjava.lab1.*;

import java.util.List;
import java.util.Random;

import static java.lang.Thread.sleep;

public class SimpleThread implements Runnable{

    private long ziarno;

    public synchronized long getZiarno(){
        return ziarno;
    }

    private boolean run = true;

    @Override
    public void run(){
        try {
            while (run){
                ziarno = losujZiarno();
                RandomLista tmpList = new RandomLista();
                List<IntElement> elementsList = tmpList.losujListe(ziarno);
                sleep(1);
                Random random = new Random();
                int chooseAlg = random.nextInt(3);
                AbstractIntSorter sorter = null;
                if(chooseAlg == 0){
                    sorter = new SelectionSort();
                }else if (chooseAlg == 1){
                    sorter = new QuickSort();
                }else if (chooseAlg == 2){
                    sorter = new CountingSort();
                }
                MemoryClass.usunZPamici();
                MemoryClass.dodajDoPamieci(ziarno, sorter.solve(elementsList));
            }

        }catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public void stopRunning() {
        run = false;
    }

    private static synchronized long losujZiarno(){
        int range = RandomLista.getRange();
        long ziarno = (long) (Math.random()*range);
        return ziarno;
    }
}
