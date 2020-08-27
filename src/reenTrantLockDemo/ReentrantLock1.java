package reenTrantLockDemo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;


/**
 * ReentrantLock用于替代synchronized
 * 由于在m1()锁定了this，因此m2()要等到m1()执行完，释放锁，才能获取到新的锁执行m2()的内容
 * ReentrantLock需要手动释放锁
 */
public class ReentrantLock1 {
    ReentrantLock lock = new ReentrantLock();

    public void m1(){
        try {
            lock.lock();
            for (int i = 0; i < 10; i++) {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(i);
            }
            System.out.println("print m1()***************");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void m2(){
        try {
            lock.lock();
            System.out.println("print m2()--------------");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
       final ReentrantLock1 lock1 = new ReentrantLock1();


        new Thread(new Runnable() {
            @Override
            public void run() {
                lock1.m1();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                lock1.m2();
            }
        }).start();


    }
}
