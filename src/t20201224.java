import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class t20201224 {

    @Test
    public void test () {
        Person person = new Person();
        person.name = "a";
        System.out.println(person.name);
        System.out.println(person);
        change(person);
        System.out.println(person.name);
    }

    public void change (Person person) {
        System.out.println(person);
        person.name = "b";
        System.out.println(person.name);
    }

    @Test
    public void test2 () {
        String string = new String("1");
        func(string);
        System.out.println("test2: " + string);
    }

    @Test
    public void test3 () {
        Integer integer = new Integer(2);
        Integer integer1 = new Integer(2);
        System.out.println(integer == integer1);
        Integer integer2 = 100;
        Integer integer3 = 100;
        System.out.println(integer2 == integer3);
        Integer integer4 = 129;
        Integer integer5 = 129;
        System.out.println(integer4 == integer5);
    }

    @Test
    public void test4 () throws InterruptedException {
        Integer[] integers = {1,2,3,4};
        Thread.sleep(3000);
        Thread.yield();
        System.out.println(Arrays.toString(integers));
    }

    @Test
    public void test5 () {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);

//        Iterator<Integer> iterator = arrayList.iterator();
//        while (iterator.hasNext()) {
//            iterator.remove();
//            System.out.println(iterator.next());
//        }

        for (Integer integer : arrayList) {
            System.out.println(integer);
        }
    }

    public void func (String string) {
        string = "2";
        System.out.println("func: " + string);
    }

}

class Person {
    String name;
}