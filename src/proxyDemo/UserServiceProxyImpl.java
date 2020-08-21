package proxyDemo;

public class UserServiceProxyImpl implements UserService {


    public UserServiceProxyImpl(UserService target) {
        this.target = target;
    }

    private UserService target;



    @Override
    public void select() {
        before();
        target.select();
        after();
    }

    @Override
    public void update() {
        before();
        target.update();
        after();
    }

    public void before(){
        System.out.println("log start time");
    }
    public void after(){
        System.out.println("log end time");
    }
}
