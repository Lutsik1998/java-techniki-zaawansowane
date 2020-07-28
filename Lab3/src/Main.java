


public class Main {





    public static void main(String[] args) {
        losowanie();

        testMainThread();
        for (int i = 0; i < 5; i++){
            testSimpleThread();
        }
    }

    public static void losowanie() {
        RandomLista.setMax(100);
        RandomLista.setMin(0);
        RandomLista.setS(10);
        RandomLista.setRange(RandomLista.getMax()-RandomLista.getMin()+1);


    }

    public static void testMainThread() {
        MainThread mainThread = new MainThread();
        Thread thread = new Thread(mainThread);
        thread.start();
    }

    public static void testSimpleThread() {
        SimpleThread simpleThread = new SimpleThread();
        Thread thread = new Thread(simpleThread);
        thread.start();
    }


}
