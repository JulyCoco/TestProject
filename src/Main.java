import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        Stud stud = new Stud();
        Tech tech = new Tech();
        build(Stud.class,"","");
    }

    public static <T> Class build(Class T, String aa, String bb ) throws InvocationTargetException, IllegalAccessException {
        Method[] methods = T.getMethods();
       for (Method m : methods){
           System.out.println(m.invoke(aa));

       }
       return T;

    }

}
