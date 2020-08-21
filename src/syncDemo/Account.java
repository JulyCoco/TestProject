package syncDemo;

import javax.sound.midi.Soundbank;
import java.util.concurrent.TimeUnit;

/**
 * 模拟银行账户
 * 写方法加锁
 * 读方法不加锁
 */
public class Account {
    String accName;
    Integer account;


    public synchronized void  set(String accName,Integer account){
        this.accName = accName;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.account = account;

    }
    public Integer get(String accName){
        return this.account;
    }

    public static void main(String[] args)  {
        final Account account = new Account();

        System.out.println("开始账户余额：======="+account.account);

        new Thread(new Runnable() {
            @Override
            public void run() {
                account.set("测试账户1",100);
            }
        }).start();

        //main线程睡一会，等待上面执行完成
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("第二次账户余额：======="+account.get("测试账户1"));


        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("获取账户余额：==========="+account.get("测试账户1"));
            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("第三次账户余额：======="+account.account);



    }

}
