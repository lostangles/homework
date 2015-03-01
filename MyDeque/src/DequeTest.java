import java.util.*;

public class DequeTest {

    public static void main(String[] args) {
        MyDeque<String> d = new MyDeque<String>();
        System.out.print(d.isEmpty());
        System.out.println(" - isEmpty on initial list");
        d.addLast("B");
        System.out.print(!d.isEmpty());
        System.out.println(" - isEmpty after one addition");
        System.out.print(d.getFirst().equals("B"));
        System.out.println(" - getFirst after one addLast");
        d.addLast("C");
        d.addFirst("A");
        String s = d.removeFirst() + d.removeFirst() + d.removeFirst(); 
        System.out.print(s.equals("ABC"));
        System.out.println(" - Sequence of adds followed by removes");
        System.out.print(d.isEmpty());
        System.out.println(" - isEmpty after all removed");
        try {
            d.removeFirst();
        }
        catch (NoSuchElementException e) {
            System.out.println("true - NoSuchElementException thrown");
        }
        catch (Exception e) {
            System.out.println("false - Wrong exception type thrown");
        }
        d.addFirst("B");
        d.addFirst("A");
        d.addLast("C");
        s = d.removeFirst() + d.removeFirst() + d.removeFirst(); 
        System.out.print(s.equals("ABC"));
        System.out.println(" - Sequence of adds followed by removes");
        System.out.print(d.isEmpty());
        System.out.println(" - isEmpty after all removed");
        try {
            d.getFirst();
        }
        catch (NoSuchElementException e) {
            System.out.println("true - NoSuchElementException thrown");
        }
        catch (Exception e) {
            System.out.println("false - Wrong exception type thrown");
        }
    }

}
