import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


public class test {

     public static boolean check(String abcd) {
        int a = 0;
        int b = 0;
        int d = 0;

        for (char c : abcd.toCharArray()) {
            if (c == '(')
                a++;
            if (c == '{')
                b++;
            if (c == '[')
                d++;

            if (c == ')')
                a--;
            if (c == '}')
                b--;
            if (c == ']')
                d--;


        }
         if (a != 0 || b != 0 || d != 0)
             return false;
         else
            return true;
    }

    public static void main(String[] args) {



//        String abcd = " a + (b * c) - {d / e}";
//        System.out.println(check(abcd));
        Integer a = 128;

        Integer b = 128;

        Integer c = 200;

        Integer d = 200;

        System.out.println(a == b);

        System.out.println(c == d);


        // {[(])}
        // ((()
        // a + (b * c) - {d / e}
    }
}
