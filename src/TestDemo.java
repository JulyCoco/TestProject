import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class TestDemo {
    public static void main(String[] args) {
        String aaa = "aaa,bbb";
        List<String> requestIdList = Arrays.asList(aaa.split(","));
        for (String item : requestIdList){
            System.out.println(item);
        }

        System.out.println(requestIdList.toString());

    }
}
