package SafePublish;

import java.util.Arrays;

/**
 * 发布对象 ： 使一个对象能够被当前范围之外的代码所使用
 * 对象溢出： 一种错误的发布：当一个对象还没有构造完，就使它被其他线程所见
 * 安全发布对象的方式
 *
 * 在静态初始化函数中初始化一个对象引用。
 * 将对象的引用保存到volatile类型域或者AtomicReference对象中。
 * 将对象的引用保存到某个正确构造对象的final类型域中。
 * 将对象的引用保存到一个由锁保护的域中。
 *
 * ----------------------------------------------------------------------------------------------------------------------------------
 * 不安全的发布对象
 *
 * 1 方法返回一个数组的引用
 * 从而可以修改一个private 数组的属性
 *
 * 2 逸出错误
 * 外部类的构造函数中初始化内部类，而在内部类中使用外部类的属性
 *
 * ！！！对象未完全构造之前不能将其发布
 */
public class UnsafePublish {
    private int value ;

    /*
    构造函数调用内部类的构造函数，
    内部类的构造函数使用外部类的属性，（外部类的属性可能还没有正确的构造完成 this引用的逸出
     */


    public UnsafePublish(){
        new InnerClass();
    }
    private class InnerClass{
        public InnerClass(){
            System.out.println(UnsafePublish.this.value);
        }
    }

    private String[] names = {"Gao","Yh"};
    public String[] getNames(){
        return names;
    }

    public static void main(String[] args) {
        UnsafePublish unsafePublish = new UnsafePublish();

        System.out.println(Arrays.toString(unsafePublish.getNames()));
        unsafePublish.getNames()[0] = "XIII";

        System.out.println(Arrays.toString(unsafePublish.getNames()));
    }
}
