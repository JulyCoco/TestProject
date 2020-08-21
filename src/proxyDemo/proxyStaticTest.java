package proxyDemo;

public class proxyStaticTest {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        UserServiceProxyImpl proxyService = new UserServiceProxyImpl(userService);
        proxyService.select();
        proxyService.update();

    }
}
