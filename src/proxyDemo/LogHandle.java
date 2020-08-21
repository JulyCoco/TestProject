package proxyDemo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LogHandle implements InvocationHandler {

    Object target;

    public LogHandle(Object target) {
        this.target = target;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object result = method.invoke(target, args);
        after();
        return result;
    }

    public void before(){
        System.out.println("log start time");
    }

    public void after(){
        System.out.println("log end time");
    }
}
