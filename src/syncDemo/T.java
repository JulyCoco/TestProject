package syncDemo;

/**
 * 同步方法和非同步方法，是否可以同时调用
 */
public class T {

    public synchronized void m1(){
        System.out.println(Thread.currentThread().getName() + "m1 start........");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "m1 end...........");
    }

    public void m2(){
        System.out.println(Thread.currentThread().getName() + "m2 start...........");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "m2 end...............");
    }

    public static void main(String[] args) {
       final T  t = new T();
        new Thread(new Runnable() {
            @Override
            public void run() {
                t.m1();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                t.m2();
            }
        }).start();
    }
}
