package reenTrantLockDemo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLock3 {
    public static void main(String[] args) {
       final ReentrantLock lock = new ReentrantLock();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    System.out.println("t1 start-----------");
                    TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
                    System.out.println("t1 end-----------");
                } catch (InterruptedException e) {
                    System.out.println("t1 interrupt---------");
                } finally {
                    lock.unlock();
                }
            }
        }).start();

       Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lockInterruptibly();
                    System.out.println("t2 start-----------");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println("t2 end-----------");
                } catch (InterruptedException e) {
                    System.out.println("t2 interrupt---------");
                } finally {
                    lock.unlock();
                }
            }
        });
        t2.start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.interrupt();

    }
}
