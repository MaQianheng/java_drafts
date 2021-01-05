import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class t20201228DesignPattern {
    @Test
    public void test1 () {
        SingletonLazyUnsafe singletonLazyUnsafe = SingletonLazyUnsafe.getInstance();
        SingletonLazyUnsafe singletonLazyUnsafe1 = SingletonLazyUnsafe.getInstance();
        System.out.println(singletonLazyUnsafe == singletonLazyUnsafe1);
    }

    @Test
    public void test2 () {
        Mei mei = new Mei();
        LaoWang laoWang = new LaoWang();
        LaoLi laoLi = new LaoLi();

        mei.addWatcher(laoWang);
        mei.addWatcher(laoLi);
        mei.notifyWatchers();
    }

    @Test
    public void test3 () {
        Food food = new Bread(new Vegetable(new Cream(new Food("food"))));
        System.out.println(food.make());
    }

    @Test
    public void test4 () {
        Phone phone = new Phone();
        VoltageAdapter voltageAdapter = new VoltageAdapter();
        phone.setVoltageAdapter(voltageAdapter);
        phone.charge();
        phone.charge();
        phone.charge();
    }

    @Test
    public void test5 () {
        Car car = Factory.getCarInstance("Benz");
        if (car != null) {
            car.run();
            car.stop();
        } else {
            System.out.println("no such class");
        }
    }

    @Test
    public void test6 () {
        VehicleFactory vehicleFactory = new BroomFactory();
        Moveable moveable = vehicleFactory.create();
        moveable.run();
    }

    @Test
    public void test7 () {
        WeedingProxy proxyInterface = new WeddingCompany(new Family());
        proxyInterface.marry();
    }
}

/**
 * 1.单例模式：
 简单点说，就是一个应用程序中，某个类的实例对象只有一个，你没有办法去new，因为构造器是被private修饰的，一般通过getInstance()的方法来获取它们的实例。getInstance()的返回值是一个对象的引用，并不是一个新的实例，所以不要错误的理解成多个对象。
 */
// 懒汉式写法（线程不安全）
class SingletonLazyUnsafe {
    private static SingletonLazyUnsafe singletonLazyUnsafe;

    private SingletonLazyUnsafe () {}

    public static SingletonLazyUnsafe getInstance () {
        if (SingletonLazyUnsafe.singletonLazyUnsafe == null) {
            SingletonLazyUnsafe.singletonLazyUnsafe = new SingletonLazyUnsafe();
        }
        return SingletonLazyUnsafe.singletonLazyUnsafe;
    }
}

// 懒汉式写法（线程安全）
class SingletonLazySafe {
    private static SingletonLazySafe singletonLazySafe;

    private SingletonLazySafe () {}

    public static synchronized SingletonLazySafe getInstance() {
        if (SingletonLazySafe.singletonLazySafe == null) {
            SingletonLazySafe.singletonLazySafe = new SingletonLazySafe();
        }
        return SingletonLazySafe.singletonLazySafe;
    }
}

// 饿汉式写法
class SingletonHungry {
    private static final SingletonHungry SINGLETON_HUNGRY = new SingletonHungry();

    private SingletonHungry () {}

    public static SingletonHungry getInstance() {
        return SingletonHungry.SINGLETON_HUNGRY;
    }
}

// 静态内部类
class SingletonStaticInnerClass {
    private static class SingletonHolder {
        private static final SingletonStaticInnerClass SINGLETON_STATIC_INNER_CLASS = new SingletonStaticInnerClass();
    }

    private SingletonStaticInnerClass () {}

    public static SingletonStaticInnerClass getInstance() {
        return SingletonHolder.SINGLETON_STATIC_INNER_CLASS;
    }
}

/**
 * 2.观察者模式：
 * 对象间一对多的依赖关系，当一个对象的状态发生改变时，所有依赖于它的对象都得到通知并被自动更新。
 * 举个栗子：假设有三个人，小美（女，22），小王和小李。小美很漂亮，小王和小李是两个程序猿，时刻关注着小美的一举一动。有一天，小美说了一句：“谁来陪我打游戏啊。”这句话被小王和小李听到了，结果乐坏了，蹭蹭蹭，没一会儿，小王就冲到小美家门口了，在这里，小美是被观察者，小王和小李是观察者，被观察者发出一条信息，然后观察者们进行相应的处理，看代码：
 */

// 这个接口相当于小王和小李的电话号码，小美发送通知的时候就会拨打getMessage这个电话，拨打电话就是调用接口，看不懂没关系，先往下看
interface Watcher {
    void receiveMessage(String msg, String from);
}

class LaoWang implements Watcher {
    private String name = "wang";

    public LaoWang() {}

    @Override
    public void receiveMessage(String msg, String from) {
        System.out.println(this.name + " received message from " + from + ", content is: " + msg);
    }
}

class LaoLi implements Watcher {
    private String name = "li";

    public LaoLi() {}

    @Override
    public void receiveMessage(String msg, String from) {
        System.out.println(this.name + " received message from " + from + ", content is: " + msg);
    }
}

class Mei {
    List<Watcher> list = new ArrayList<Watcher>();

    public Mei() {}

    public void addWatcher(Watcher watcher) {
        list.add(watcher);
    }

    public void notifyWatchers() {
        for (Watcher watcher: this.list) {
            watcher.receiveMessage("play with me", "xiao mei");
        }
    }
}

/**
 * 3.装饰者模式：
 * 对已有的业务逻辑进一步的封装，使其增加额外的功能，如Java中的IO流就使用了装饰者模式。用户在使用的时候，可以任意组装，达到自己想要的效果。
 * 举个栗子，我想吃三明治，首先我需要一根大大的香肠，我喜欢吃奶油，在香肠上面加一点奶油，再放一点蔬菜，最后再用两片面包夹一下，很丰盛的一顿午饭，营养又健康。（ps：不知道上海哪里有卖好吃的三明治的，求推荐～）那我们应该怎么来写代码呢？ 首先，我们需要写一个Food类，让其他所有食物都来继承这个类，
 */

class Food {
    private String foodName;

    public Food() {}

    public Food(String foodName) {
        this.foodName = foodName;
    }

    public String make() {
        return this.foodName;
    }
}

class Bread extends Food {
    private final Food food;

    public Bread(Food food) {
        this.food = food;
    }

    public String make() {
        return this.food.make() + "+bread";
    }
}

class Cream extends Food {
    private final Food food;

    public Cream(Food food) {
        this.food = food;
    }

    public String make() {
        return this.food.make() + "+cream";
    }
}

class Vegetable extends Food {
    private final Food food;

    public Vegetable(Food food) {
        this.food = food;
    }

    public String make() {
        return this.food.make() + "+vegetable";
    }
}

/**
 * 4.适配器模式：
 * 将两种完全不同的事物联系到一起，把一个类的接口变换成客户端所期待的另一种接口，从而使原本因接口不匹配而无法在一起使用的类能够一起工作。
 * 假设一个手机充电器需要的电压是20V，但是正常的电压是220V，这时候就需要一个变压器，将220V的电压转换成20V的电压，这样，变压器就将20V的电压和手机联系起来了。
 */
class Phone {
    public static final int V = 20;

    private VoltageAdapter voltageAdapter;

    public void charge() {
        voltageAdapter.convertVoltage(20);
    }

    public void setVoltageAdapter(VoltageAdapter voltageAdapter) {
        this.voltageAdapter = voltageAdapter;
    }
}

class VoltageAdapter {
    private static int remainingVoltage = 220;

    public void convertVoltage(int v) {
        System.out.println("it is charging");
        System.out.println("original voltage: " + remainingVoltage + "V");
        if (remainingVoltage - v < 0) {
            System.out.println("no sufficient voltage");
            return;
        }
        remainingVoltage -= v;
        System.out.println("after converted voltage: " + v + "V");
        System.out.println("remaining voltage: " + remainingVoltage + "V");
    }
}

/**
 * 5.简单工厂模式：
 * 一个抽象的接口，多个抽象接口的实现类，一个工厂类，用来实例化抽象的接口
 * 工厂类可以根据条件生成不同的子类实例，这些子类有一个公共的抽象父类并且实现了相同的方法，但是这些方法针对不同的数据进行了不同的操作（多态方法）。当得到子类的实例后，开发人员可以调用基类中的方法而不必考虑到底返回的是哪一个子类的实例。
 */
interface Car {
    void run();
    void stop();
}

class Benz implements Car {
    @Override
    public void run() {
        System.out.println("Benz running...");
    }

    @Override
    public void stop() {
        System.out.println("Benz stop...");
    }
}

class Ford implements Car {
    @Override
    public void run() {
        System.out.println("Ford running...");
    }

    @Override
    public void stop() {
        System.out.println("Ford stop...");
    }
}

class Factory {
    public static Car getCarInstance(String brand) {
        switch (brand) {
            case "Benz":
                return new Benz();
            case "Ford":
                return new Ford();
            default:
                return null;
        }
    }
}

/**
 * 6.工厂方法模式：
 * 有四个角色，抽象工厂，具体工厂，抽象产品，具体产品。不再是由一个工厂类去实例化具体的对象，而是由抽象工厂的子类去实例化对象并返回。
 * 与工厂方法模式不同的是，工厂方法模式中的工厂只生产单一的产品，而抽象工厂模式中的工厂生产多个产品。
 *
 * 而且使用抽象工厂模式还要满足一下条件：
    * 系统中有多个产品族，而系统一次只可能消费其中一族产品
    * 同属于同一个产品族的产品以其使用。
 * 来看看抽象工厂模式的各个角色（和工厂方法的如出一辙）：
    * 抽象工厂角色： 这是工厂方法模式的核心，它与应用程序无关。是具体工厂角色必须实现的接口或者必须继承的父类。在java中它由抽象类或者接口来实现。
    * 具体工厂角色：它含有和具体业务逻辑有关的代码。由应用程序调用以创建对应的具体产品的对象。在java中它由具体的类来实现。
    * 抽象产品角色：它是具体产品继承的父类或者是实现的接口。在java中一般有抽象类或者接口来实现。
    * 具体产品角色：具体工厂角色所创建的对象就是此角色的实例。在java中由具体的类来实现。
 */
// 抽象产品角色
interface Moveable {
    void run();
}

// 具体产品角色
class Plane implements Moveable {
    @Override
    public void run() {
        System.out.println("plane...");
    }
}

class Broom implements Moveable {
    @Override
    public void run() {
        System.out.println("broom...");
    }
}

// 抽象工厂
abstract class VehicleFactory {
    abstract Moveable create();
}

// 具体工厂
class PlaneFactory extends VehicleFactory {
    @Override
    Moveable create() {
        return new Plane();
    }
}

class BroomFactory extends VehicleFactory {
    @Override
    Moveable create() {
        return new Broom();
    }
}

/**
 * 7.代理模式：
 * 有两种，静态代理和动态代理。先说静态代理，很多理论性的东西我不讲，我就算讲了，你们也看不懂。什么真实角色，抽象角色，代理角色，委托角色。。。乱七八糟的，我是看不懂。之前学代理模式的时候，去网上翻一下，资料一大堆，打开链接一看，基本上都是给你分析有什么什么角色，理论一大堆，看起来很费劲，不信的话你们可以去看看，我是看不懂他们在说什么。咱不来虚的，直接用生活中的例子说话。（注意：我这里并不是否定理论知识，我只是觉得有时候理论知识晦涩难懂，喜欢挑刺的人一边去，你是来学习知识的，不是来挑刺的）
 * 到了一定的年龄，我们就要结婚，结婚是一件很麻烦的事情，（包括那些被父母催婚的）。有钱的家庭可能会找司仪来主持婚礼，显得热闹，洋气～好了，现在婚庆公司的生意来了，我们只需要给钱，婚庆公司就会帮我们安排一整套结婚的流程。整个流程大概是这样的：家里人催婚->男女双方家庭商定结婚的黄道即日->找一家靠谱的婚庆公司->在约定的时间举行结婚仪式->结婚完毕
 * 婚庆公司打算怎么安排婚礼的节目，在婚礼完毕以后婚庆公司会做什么，我们一概不知。。。别担心，不是黑中介，我们只要把钱给人家，人家会把事情给我们做好。所以，这里的婚庆公司相当于代理角色，现在明白什么是代理角色了吧。
 */
interface WeedingProxy {
    void marry();
}

class WeddingCompany implements WeedingProxy {
    private final WeedingProxy proxyInterface;

    public WeddingCompany(WeedingProxy proxyInterface) {
        this.proxyInterface = proxyInterface;
    }

    @Override
    public void marry() {
        System.out.println("wedding step 1");
        System.out.println("wedding step 2");
        System.out.println("wedding step 3");
        System.out.println("wedding step 4");
        System.out.println("wedding step 5");
        proxyInterface.marry();
        System.out.println("wedding finish");
    }
}
// 重写
class Family implements WeedingProxy {
    @Override
    public void marry() {
        System.out.println("we married");
    }
}














