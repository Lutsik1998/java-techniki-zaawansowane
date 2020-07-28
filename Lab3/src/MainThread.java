import com.labyjava.lab1.AbstractIntSorter;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import static java.lang.Thread.sleep;


public class MainThread implements Runnable {


    public static volatile List<Class> listaKlas = Collections.synchronizedList(new ArrayList<>());


    FilenameFilter filter = new FilenameFilter() {

        public boolean accept(File f, String name) {
            return name.endsWith(".class");
        }
    };

    @Override
    public void run() {

        try {
            sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (true) {

            try {
                File f = new File("Algorytmy/");
                URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{
                        new URL("file:Algorytmy/")});
                File[] files = f.listFiles(filter);
                boolean klasaIstnieje = false;
                for (File file : files) {
                    String nazwaKlasy = file.getName();
                    nazwaKlasy = nazwaKlasy.replace('/', '.');
                    System.out.println(Thread.currentThread().getName() + " -- wczytuje klase " + nazwaKlasy);
                    nazwaKlasy = file.getName().substring(0, file.getName().length() - 6);
                    Class klas = urlClassLoader.loadClass("com.labyjava.lab1." + nazwaKlasy);
                    for (Class cls : listaKlas) {
                        if (klas.getName().equals(cls.getName())) {
                            System.out.println(Thread.currentThread().getName() + " -- nie ma co dodac1");
                            klasaIstnieje = true;
                        }
                    }
                    if (!klasaIstnieje) {
                        if (isCurrentClass(klas) && !Modifier.isAbstract(klas.getModifiers())) {
                            listaKlas.add(klas);
                            System.out.println(Thread.currentThread().getName() + " -- dodal klase " + klas.getName() + "---- rozmiar listy klas  " + listaKlas.size());

                        } else {
                            System.out.println(Thread.currentThread().getName() + " -- nie ma co dodac2");
                        }
                    }
                    System.out.println(Thread.currentThread().getName() + "sleep");
                    sleep(500);
                    MemoryClass.odsetek();
                }


            } catch (MalformedURLException | InterruptedException | ClassNotFoundException e) {
                e.printStackTrace();
            }


        }

    }

    private synchronized <T> boolean isCurrentClass(Class<T> type) {
        if (type != null) {
            if (type == AbstractIntSorter.class) {
                return true;
            }
            return isCurrentClass(type.getSuperclass());
        }
        return false;
    }


}
