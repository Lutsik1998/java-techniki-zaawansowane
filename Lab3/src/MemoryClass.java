import com.labyjava.lab1.IntElement;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryClass   {


    private static volatile Map<Long, SoftReference<List<IntElement>>> memoryMap = new HashMap<>();
    private static volatile int g1 = 0;
    private static volatile int g2 = 0;
    private static volatile int m1 = 0;
    private static volatile int m2 = 0;

    public static synchronized void odsetek() {

        System.out.println(Thread.currentThread().getName() + " Rozmiar pamieci = " + memoryMap.size() + " |  Nieudane odwolania m1 =" + (float) m1 + " |  Nieudane odwolania m2=" + (float) m2 + " | Ogolne odwolania g1 =" + (float) g1 + " | Ogolne odwolania g2=" + (float) g2);

        System.out.println(Thread.currentThread().getName() + " Rozmiar pamieci = " + memoryMap.size() + " |  Globalny odsetek=" + (float) m1 / g1 *100 + "%" + " | Od ostatniego raportu=" + (float) m2 / g2 * 100 + "%");


        setM2(0);
        setG2(0);
    }


    public static synchronized void dodajDoPamieci(long ziarno, List<IntElement> value) {

        licznik(ziarno);
        memoryMap.put(ziarno, new SoftReference<List<IntElement>>(value));

        value = null;
    }

    public static synchronized void licznik(long ziarno) {
        if (memoryMap.containsKey(ziarno)) {
            g1++;
            g2++;
            if (memoryMap.get(ziarno).get() == null) {
                m1++;
                m2++;
            }
        }
    }




    public static synchronized int getG1() {
        return g1;
    }

    public static synchronized void setG1(int g1) {
        MemoryClass.g1 = g1;
    }

    public static synchronized int getG2() {
        return g2;
    }

    public static synchronized void setG2(int g2) {
        MemoryClass.g2 = g2;
    }

    public static synchronized int getM1() {
        return m1;
    }

    public static synchronized void setM1(int m1) {
        MemoryClass.m1 = m1;
    }

    public static synchronized int getM2() {
        return m2;
    }

    public static synchronized void setM2(int m2) {
        MemoryClass.m2 = m2;
    }
}
