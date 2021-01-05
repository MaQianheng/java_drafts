package package1;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class C20201227 {
    @Test
    public void test() {
        MyThread myThread = new MyThread();
        MyThread2 myThread2 = new MyThread2();
        myThread.start();
        myThread2.start();
    }


    @Test
    public void test2() {
        Collection<Student> collection = new TreeSet<Student>();
        collection.add(new Student("rbs", 11));
        collection.add(new Student("aa", 18));
        collection.add(new Student("aa", 28));
        collection.add(new Student("bbs", 2));
        collection.add(new Student("ji", 92));
        collection.add(new Student("opp", 22));
        collection.add(new Student("qqs", 52));
        collection.add(new Student("zza", 32));
        System.out.println(collection);

        List<Student> list = new ArrayList<Student>();
        list.add(new Student("aasw", 18));
        list.add(new Student("aasw", 12));
        list.add(new Student("icobbs", 90));
        list.add(new Student("aswji", 92));
        list.add(new Student("dfropp", 8));
        list.add(new Student("qsdwqs", 22));
        list.add(new Student("zza", 32));
        list.add(new Student("icobbs", 96));
        list.sort(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                if (!o1.getName().equals(o2.getName())) return o1.getName().compareTo(o2.getName());
                return o1.getAge() - o2.getAge();
            }
        });
        for (Student student : list) {
            System.out.println(student);
        }
    }

    @Test
    public void test3() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Account account = new Account();
        for (int i = 0; i < 10; i++) {
            executorService.execute(account);
        }
        while (!executorService.isShutdown()) {
        }
    }

    @Test
    public void test4() {
        System.out.println(test5());
    }

    public int test5() {
        int a = 10;
        try {
            System.out.println(a / 0);
            a = 20;
        } catch (ArithmeticException e) {
            a = 30;
            System.out.println("this is catch");
            return a;
            /*
             * return a 在程序执行到这一步的时候，这里不是return a 而是 return 30；这个返回路径就形成了
             * 但是呢，它发现后面还有finally，所以继续执行finally的内容，a=40
             * 再次回到以前的路径,继续走return 30，形成返回路径之后，这里的a就不是a变量了，而是常量30
             */
        } finally {
            System.out.println("this is finally1");
            a = 40;
            System.out.println("this is finally2");
        }
        return a;
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) System.out.println(Thread.currentThread().getName() + " -> " + i);
        }
    }
}

class MyThread2 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 != 0) System.out.println(Thread.currentThread().getName() + " -> " + i);
        }
    }
}

class Student implements Comparable<Student> {
    private String name;
    private int age;

    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int compareTo(Student o) {
        if (!this.name.equals(o.name)) return this.name.compareTo(o.name);
        return this.age - o.age;
    }
}

class Account implements Runnable {
    private double balance = 1000;
    // solution3: 创建锁对象，显示的加锁
    private final ReentrantLock reentrantLock = new ReentrantLock();

    public double getBalance() {
        return balance;
    }

    // solution 2:
//    public void synchronized deposit (double amount) {
    public void deposit(double amount) {
        // solution 3:
        this.reentrantLock.lock();

        double tmp = this.balance + amount;
        try {
            Thread.sleep(10);
            this.balance = tmp;
            System.out.println(Thread.currentThread().getName() + ": " + this.balance);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        } finally {
            this.reentrantLock.unlock();
        }
    }

    @Override
    public void run() {
        // solution 1: run方法加synchronized块，将要执行的代码放进去
//        synchronized (this) {
//            this.deposit(100);
//        }
        // solution 2: 被调用的方法加synchronized
        this.deposit(100);
    }
}
