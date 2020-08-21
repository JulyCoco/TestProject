package singletonDemo;

/**
 * 懒汉式
 * 需要的时候才初始化，线程不安全的
 * 可以加synchronized来解决，但是也会带来效率问题
 */
public class SingletonB {
    private static SingletonB instance;

    private SingletonB(){}

    public static /*synchronized*/ SingletonB getInstance(){
        //A B同时走到这里判断都为null,则会初始化两份
        if (instance == null){
            instance = new SingletonB();
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return instance;

    }

    /**
     * 启30个线程
     * @param args
     */
    public static void main(String[] args) {
        for (int i = 0; i < 500; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(SingletonB.getInstance().hashCode());
                }
            }).start();
        }

    }
}
