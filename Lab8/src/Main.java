import com.labyjava.lab1.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Main {


    public static Class classLoader(String path) throws MalformedURLException {
        URL[] url = {new URL("jar:file:" + path + "!/")};
        URLClassLoader urlClassLoader = URLClassLoader.newInstance(url);
        Class clazz = null;
        try {
            clazz = urlClassLoader.loadClass("com.labyjava.lab1.SelectionSort");
        } catch (SecurityException s){
            System.out.println("\n" + s.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return  clazz;
    }


    public static void main(String[] args) throws IOException, IllegalAccessException, InstantiationException {
        System.out.println("Menu:\n0 - Zamknij\n1 - Szyfruj\n2 - Odszyfruj\n3 - Sortowanie\n");
        int wybierz = -1;
        Scanner in = new Scanner(System.in);
        while(true) {
            String file;
            String klucz;
            System.out.println("Wybierz");
            String ret = in.nextLine();
            wybierz = Integer.parseInt(ret);
            if(wybierz == 0) {
                break;
            } else {

                if(wybierz == 1) {
                    System.out.println("Podaj nazwę pliku");
                    file = in.nextLine();
                    System.out.println("Podaj klucz");
                    klucz = in.nextLine();
                    EncrypterDecrepter.encrypt(klucz, file);
                } else if(wybierz == 2) {
                    System.out.println("Podaj nazwę pliku");
                    file = in.nextLine();
                    System.out.println("Podaj klucz");
                    klucz = in.nextLine();
                    EncrypterDecrepter.decrypt(klucz, file);
                } else if(wybierz == 3){
                    JarFile jarFile = new JarFile("D:/Study/VIsemestr/JP/Lab8/SLab1.jar", true);
                    Enumeration<JarEntry> e = jarFile.entries();
                    while(e.hasMoreElements()){
                        JarEntry entry = e.nextElement();
                        try {
                            jarFile.getInputStream(entry);
                            System.out.println(entry);
                        } catch(SecurityException s){
                            System.err.println("\n"+ s.getMessage());
                        }
                    }
                    SelectionSort  selectionSort = (SelectionSort) classLoader("D:/Study/VIsemestr/JP/Lab8/SLab1.jar").newInstance();

                    char[] chars = "0123456789abcdefghijklmnopqrstuvwxyz".toCharArray();
                    IElement a;
                    List<IElement> lista = new ArrayList<>();
                    int randValue;
                    for(int i = 0; i < 10; i++){
                        Random random = new Random();
                        StringBuilder sb = new StringBuilder(5);
                        for (int j = 0; j < 5; j++) {
                            char c = chars[random.nextInt(chars.length)];
                            sb.append(c);
                        }
                        randValue = (int) ( Math.random() * 101 );
                        a = new IntElement(sb.toString(),randValue);
                        lista.add(a);
                    }
                    System.out.println("Lista:");
                    for(int i = 0; i < lista.size(); i++){
                        System.out.println(lista.get(i).getValue());
                    }
                    List<IElement> sortedList = selectionSort.solveFloatAndInt(lista);
                    System.out.println("Posortowana lista:");
                    for (int i = 0; i < sortedList.size(); i++){
                        System.out.println(sortedList.get(i).getValue());
                    }
                }
            }
        }
    }

}
