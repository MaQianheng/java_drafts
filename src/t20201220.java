import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.LinkedList;

public class t20201220 {

    public static void main(String[] args) {
        String s1 = new StringBuilder("go").append("od").toString();
        System.out.println(s1.intern());
        System.out.println(s1.intern() == s1);
        String s2 = new StringBuilder("ja").append("va").toString();
        System.out.println(s2.intern() == s2);

        System.out.println(s1.hashCode());
        System.out.println(s2.hashCode());

        System.out.println(2 << 3);

        System.out.println(31 * 5);
        System.out.println((5 << 5) - 5);
        t t = new t();
    }

    @Test
    public void test() {
        String s1 = "Programming";
        String s2 = new String("Programming");
        String s3 = "Program";
        String s4 = "ming";
        String s5 = "Program" + "ming";
        String s6 = s3 + s4;
        System.out.println(s1 == s2);
        System.out.println(s1 == s5);
        System.out.println(s1 == s6);
        System.out.println(s1 == s6.intern());
        System.out.println(s2 == s2.intern());

        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.get(Calendar.YEAR));
        System.out.println(calendar.getTimeInMillis());
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime.getYear());

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate date2 = LocalDate.now();
        System.out.println(date2.format(dateTimeFormatter));
    }

    @Test
    public void test2() throws Exception {
        Human human = new Human();
        human.main();
    }

    @Test
    public void test3() throws Exception {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        System.out.println(arrayList.get(1));
        System.out.println(arrayList);

        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(1);
        linkedList.add(1, 2);
        linkedList.add(1, 5);
        linkedList.add(3);
        linkedList.forEach(integer -> System.out.println(integer));
        System.out.println(linkedList.size());
        System.out.println(linkedList);
    }

    public void t2(t t) {
        t.b = 10;
    }

    static class t {
        int a = 0;
        int b = 0;

        public t() {
        }

        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }

        public int getB() {
            return b;
        }

        public void setB(int b) {
            this.b = b;
        }
    }

}

class Human implements Runnable{
    @Override
    public void run() {

    }

    static class Annoyance extends Exception {
    }

    static class Sneeze extends Annoyance {
    }

    public void main() throws Exception {
        try {
            try {
                throw new Sneeze();
            } catch (Annoyance a) {
                System.out.println("Caught Annoyance");
                throw a;
            }
        } catch (Sneeze s) {
            System.out.println("Caught Sneeze");
            return;
        } finally {
            System.out.println("Hello World!");
        }
    }
}
//"Caught Annoyance"