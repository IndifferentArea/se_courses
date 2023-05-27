package hw2.p4;

interface Animal {
    default void greet(Animal a) {
        System.out.println("hello animal");
    }

    default void sniff(Animal a) {
        System.out.println("sniff animal");
    }

    default void praise(Animal a) {
        System.out.println("u r cool animal");
    }
}

public class Dog implements Animal {
    @Override
    public void sniff(Animal a) {
        System.out.println("dog sniff animal");
    }

    void praise(Dog a) {
        System.out.println("u r cool dog");
    }

    public static void main(String[] args) {
        Animal a = new Dog();
        Dog d = new Dog();
        a.greet(d);
        a.sniff(d);
        d.praise(d);
        a.praise(d);
        ((Dog) a).praise(d);
    }
}

// 运行结果为：
// hello animal         greet() 没有被override
// dog sniff animal     sniff() 被override了，动态选择
// u r cool dog         praise() 被overload了，就近原则
// u r cool animal      praise() 没有被override，依旧选择父类
// u r cool dog         (cast 会转换静态类型)