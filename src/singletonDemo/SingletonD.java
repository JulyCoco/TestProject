package singletonDemo;

/**
 * DCL(Double Check Lock)双重检查锁
 * 线程安全的
 * 面试问题：dcl要不要加volatile，回答要加，问题出在指令重排序上 new SingletonD()这一步，分成3步，不加volatile可能出现指令重排
 * 举例子：A进来在做new SingletonD()这一步时，分3步：（1）申请内存空间，赋默认值（2）给其赋初始值（3）把instance指向它
 * 如果（2）（3）颠倒顺序，此时instance里面已经有值了（是默认值），然后B 过来，判断instacnce != null，就会把这个错误的值返回去
 * 解决办法：变量用volatile修饰，禁止指令重排。
 */
public class SingletonD {
    private static volatile SingletonD instance;

    private SingletonD() {
    }


    public static SingletonD getInstance() {
        if (instance == null) {
            synchronized (SingletonD.class) {
                /**
                 * 针对SingletonC示例的问题，在这里再判断一段是否为null，B再进来后，判断不为null了，不会再new一遍
                 */
                if (instance == null) {
                    instance = new SingletonD();
                }
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
