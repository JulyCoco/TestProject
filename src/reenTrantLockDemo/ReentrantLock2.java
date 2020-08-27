package reenTrantLockDemo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock可以使用tryLock()尝试锁定，在一定的时间内要是无法锁定，线程可自行决定是否继续等待
 */
public class ReentrantLock2 {

    ReentrantLock lock = new ReentrantLock();

    public void m1(){
        try {
            lock.lock();
            for (int i = 0; i < 5; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(i);
            }
            System.out.println("print m1()****************");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void m2(){
        boolean locked = false;
        try {
            locked = lock.tryLock(2,TimeUnit.SECONDS);
            System.out.println("print m2()------------------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (locked){
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        final ReentrantLock2 reentrantLock2 = new ReentrantLock2();
        new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantLock2.m1();
            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantLock2.m2();;
            }
        }).start();
    }
}
