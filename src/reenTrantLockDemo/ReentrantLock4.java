package reenTrantLockDemo;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock可以实现公平锁
 */
public class ReentrantLock4 extends Thread {
   private static ReentrantLock lock = new ReentrantLock(true);
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "获得锁");
            } finally {
                lock.unlock();
            }

        }
    }

    public static void main(String[] args) {
        ReentrantLock4 lock4 = new ReentrantLock4();
        Thread t1 = new Thread(lock4);
        Thread t2 = new Thread(lock4);
        t1.start();
        t2.start();
    }
}
