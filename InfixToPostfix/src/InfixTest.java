import java.util.*;
public class InfixTest {

    public static void main(String[] args) {
        System.out.println(check(
            "12345678901234567890+0", "12345678901234567890 0 +"));
        System.out.println(check(
            "hello", "hello"));
        System.out.println(check(
            "hello   +world", "hello world +"));
        System.out.println(check(
            "a+b/c^d", "a b c d ^ / +"));
        System.out.println(check(
            "a^b/c+d", "a b ^ c / d +"));
        System.out.println(check(
            "3 / 2", "3 2 /"));
        System.out.println(check(
            "3 ^ 2", "3 2 ^"));
        System.out.println(check(
            "4 + 3 * 2", "4 3 2 * +"));
        System.out.println(check(
            "4 * 3 + 2", "4 3 * 2 +"));
        System.out.println(check(
            "( 4 + 3 ) * 2", "4 3 + 2 *"));
        System.out.println(check(
            "4 * ( 3 + 2 )", "4 3 2 + *"));
        System.out.println(check(
            "2 + 3 * 4 * 5 + 6", "2 3 4 * 5 * + 6 +"));
        System.out.println(check(
            "4 ^ 3 ^ 2", "4 3 2 ^ ^"));
        System.out.println(check(
            "2 * 4 ^ 3 ^ 2 * 2", "2 4 3 2 ^ ^ * 2 *"));
        System.out.println(check(
            "2 * ( 4 ^ 3 ) ^ 2 * 2", "2 4 3 ^ 2 ^ * 2 *"));
        System.out.println(check(
            "( ( ( 2 ) ) ) ^ 2", "2 2 ^"));
        try {
            System.out.println(check(
                ") 0 (", "0"));
        }
        catch (IllegalArgumentException e) {
            System.out.println("true");
        }
        catch (Exception e) {
            System.out.println("false");
        }
        try {
            System.out.println(check(
                "1 & 2", "0"));
        }
        catch (IllegalArgumentException e) {
            System.out.println("true");
        }
        catch (Exception e) {
            System.out.println("false");
        }
        
    }
    
    private static boolean check(String in, String out) {
        InfixToPostfix test = new InfixToPostfix(in);
        Iterator<String> itr = test.iterator();
        String res = itr.next();
        while (itr.hasNext()) {
            res += " " + itr.next(); 
        }
        return res.equals(out);
    }

}

