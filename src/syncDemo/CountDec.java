package syncDemo;

public class CountDec implements Runnable{


    private int count = 100;
    @Override
    public synchronized void run() {
        count--;
        System.out.println(Thread.currentThread().getName() + "--------count = " + count);
    }

    public static void main(String[] args) {
        CountDec t = new CountDec();

        for (int i = 0; i < 100; i++) {
            new Thread(t,"THREAD--" + i).start();
        }

    }
}
