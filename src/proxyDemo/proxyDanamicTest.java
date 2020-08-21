package proxyDemo;

import java.lang.reflect.Proxy;

public class proxyDanamicTest {
    public static void main(String[] args) {
         System.getProperties().setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        UserServiceImpl userServiceImp = new UserServiceImpl();
        ClassLoader classLoader = userServiceImp.getClass().getClassLoader();
        Class[] inteface = userServiceImp.getClass().getInterfaces();
        
        LogHandle logHandle = new LogHandle(userServiceImp);

        UserService proxyInstance = (UserService) Proxy.newProxyInstance(classLoader, inteface, logHandle);
        proxyInstance.select();
        proxyInstance.update();

    }
}
