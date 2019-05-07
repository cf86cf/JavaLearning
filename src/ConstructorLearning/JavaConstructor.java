package ConstructorLearning;

/**
 * Java构造器的构造顺序，
 *
 * static修饰的语句(static字段和static语句块)；
 * main开始
 * 基类的成员对象构造函数或构造块（构造块可以看出成员对象以成员对象顺序构造）；
 * 基类的构造函数
 *
 * 派生类的成员对象构造函数或构造块
 * 派生类的构造函数
 *
 * main结束
 */

class C {
    public C(){
        System.out.println("A的成员对象c的构造函数");
    }

}
class HelloA {

    public HelloA(String str) {
        System.out.println("HelloA"+str);
    }
    public HelloA(){}

    { System.out.println("I'm A class"); }
    C c=new C();
    static { System.out.println("static A"); }
}

public class JavaConstructor extends HelloA {
    public JavaConstructor() {
//        super("sf");
        System.out.println("HelloB");

    }

    HelloA a = new HelloA("hhh");

    {
        System.out.println("I'm B class");
    }
    int numb;
    {
        System.out.println(numb);
    }

    static {
        System.out.println("static B");
    }

    public static void main(String[] args) {
        System.out.println("-------main start-------");
        new JavaConstructor();
        System.out.println("-------main end-------");

    }
}