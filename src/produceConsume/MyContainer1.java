package produceConsume;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
/**
 * 面试题：写一个固定容量同步容器，拥有put和get方法，以及getCount方法，
 * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 *
 * 使用synchronized 配合 wait和notify/notifyAll来实现
 *
 */
public class MyContainer1<T> {

    LinkedList<T> list = new LinkedList<>();
    int MAX = 10;
    int count = 0;

    public synchronized T get(){
        T t = null;
        while (list.size() == 0 ){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        t = list.removeFirst();
        count--;
        this.notifyAll();
        return t;
    }

    public synchronized void put(T t){
        while (list.size() == MAX){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        list.add(t);
        count++;
        this.notifyAll();
    }

    public static void main(String[] args) {
        final MyContainer1<String> myDemo = new MyContainer1<String>();

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    myDemo.put(Thread.currentThread().getName()+"放入队列");
                }
            }).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(myDemo.get());
                }
            }).start();
        }





    }
}
