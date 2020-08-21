package singletonDemo;

public class SingletonC {
    private static SingletonC instance;

    private SingletonC(){}

    /**
     * 针对SingletonB的改进，锁不加在方法上，做一个更细粒度的锁
     * 但是这种写法也是有问题的，A B同时过来，A 判断为null，获取锁进行初始化，结束后，B已经在等待也会获取锁，也会初始化一份，此时还是有两份
     * @return
     */
    public static SingletonC getInstance(){
        if (instance == null){
            synchronized (SingletonC.class){
                instance = new SingletonC();
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(SingletonC.getInstance().hashCode());
                }
            }).start();
        }

    }

}
