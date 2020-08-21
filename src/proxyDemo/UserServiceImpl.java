package proxyDemo;

public class UserServiceImpl implements UserService {


    @Override
    public void select() {
        System.out.println("调用select方法");

    }

    @Override
    public void update() {
        System.out.println("调用update方法");

    }
}
