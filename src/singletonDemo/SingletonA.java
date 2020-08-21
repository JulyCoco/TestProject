package singletonDemo;

import java.io.OutputStream;

/**
 * 饿汉式
 * 类加载到内存后，就实例化一个实例，JVM保证线程安全
 * 简单实用，推荐实用
 * 缺点：不管用没用到，类装载时就已经实例化
 */
public class SingletonA {

    private static SingletonA instance = new SingletonA();

    /**
     * 构造方法设置private，调用者不能通过构造方法对其初始化
     */
    private SingletonA(){}

    public static SingletonA getInstance(){
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(SingletonA.getInstance().hashCode());
                }
            }).start();
        }
    }
}
