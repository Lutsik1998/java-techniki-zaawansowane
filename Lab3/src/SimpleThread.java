import com.labyjava.lab1.AbstractIntSorter;
import com.labyjava.lab1.IntElement;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static java.lang.Thread.sleep;

public class SimpleThread implements Runnable{

    private long ziarno;

    public synchronized long getZiarno(){
        return ziarno;
    }


    @Override
    public void run(){
        try {
            while (true){
                ziarno = losujZiarno();
                //System.out.println(Thread.currentThread().getName() + " wygenerowal ziarno = " + ziarno);
                RandomLista tmpList = new RandomLista();
                //sleep(1500);


                List<IntElement> elementsList = tmpList.losujListe(ziarno);
                //System.out.println(Thread.currentThread().getName() + " -- wylosowal liste");
                sleep(1);
               // sleep(1500);

                int loadSize = MainThread.listaKlas.size();

                if (loadSize > 0) {
                    Random random = new Random();
                    int nrAlgorytmu = random.nextInt(loadSize);

                    try {
                        AbstractIntSorter sorter = (AbstractIntSorter) MainThread.listaKlas.get(nrAlgorytmu).newInstance();

                        //System.out.println(Thread.currentThread().getName() + " -- sortuje metoda " + sorter.getClass());
                        MemoryClass.dodajDoPamieci(ziarno, sorter.solve(elementsList));
                        //sleep(1500);
                        sleep(1);
                       // System.out.println(Thread.currentThread().getName() + " -- konczyl sortowanie");
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }









    }

    private static synchronized long losujZiarno(){
        int range = RandomLista.getRange();
        long ziarno = (long) (Math.random()*range);
        return ziarno;
    }






}
