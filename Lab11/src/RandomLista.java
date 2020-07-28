import com.labyjava.lab1.IntElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomLista {

    public static volatile int max;
    public static volatile int  min;
    public static volatile int  s;
    public static volatile int range;



    public static synchronized int getS() {
        return s;
    }

    public static synchronized void setS(int s) {
        RandomLista.s = s;
    }

    public static synchronized int getMax() {
        return max;
    }

    public static synchronized void setMax(int max) {
        RandomLista.max = max;
    }

    public static synchronized int getMin() {
        return min;
    }

    public static synchronized void setMin(int min) {
        RandomLista.min = min;
    }

    public static synchronized int getRange() {
        return range;
    }

    public static synchronized void setRange(int range) {
        RandomLista.range = range;
    }

    public static synchronized List<IntElement> losujListe(long ziarno){
        char[] chars = "0123456789abcdefghijklmnopqrstuvwxyz".toCharArray();
        IntElement a;
        int randValue;
        List<IntElement> lista = new ArrayList<>();
        for(int i = 0; i < 1000; i++){
            Random random = new Random();
            StringBuilder sb = new StringBuilder(s);
            for (int j = 0; j < s; j++) {
                char c = chars[random.nextInt(chars.length)];
                sb.append(c);
            }
            randValue = (int) ( Math.random() * 101 );
            a = new IntElement(sb.toString(),randValue);
            lista.add(a);
        }

        return lista;

    }


}
