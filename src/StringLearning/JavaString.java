package StringLearning;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 字符串对象的创建
 *
 * 面试题：String str4 = new String(“abc”) 创建多少个对象？
 *
 * 在常量池中查找是否有“abc”对象
 *
 * 有则返回对应的引用实例
 *
 * 没有则创建对应的实例对象
 *
 * 在堆中 new 一个 String("abc") 对象
 *
 * 将对象地址赋值给str4,创建一个引用
 *
 * 所以，常量池中没有“abc”字面量则创建两个对象，否则创建一个对象，以及创建一个引用
 *
 * 根据字面量，往往会提出这样的变式题：
 *
 * String str1 = new String("A"+"B") ; 会创建多少个对象?
 * String str2 = new String("ABC") + "ABC" ; 会创建多少个对象?
 *
 * str1：
 * 字符串常量池："A","B","AB" : 3个
 * 堆：new String("AB") ：1个
 * 引用： str1 ：1个
 * 总共 ： 5个
 *
 * str2 ：
 * 字符串常量池："ABC" : 1个
 * 堆：new String("ABC") ：1个
 * 引用： str2 ：1个
 * 总共 ： 3个
 *
 */
public class JavaString {

    public static void main(String [] args){
        String string1 = "hello";
        String string2 = "hello";
        System.out.println("常量池比较");
        //下面输出中有 “ == ” 但是不会输出，因为 string1== string2 返回false
        System.out.println(" == " + string1== string2);  //在常量池中， 两个String 指向相同的常量池地址
        System.out.println(" equals " + string1.equals(string2));
        System.out.println("please input  'hello'");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        try {
            string2 = bufferedReader.readLine();
            String string3 = string2.intern();
            System.out.println(" == " + string1 == string2);   //对比是否完全相同，分配有相同的地址空间， 此时分配到堆栈区
            System.out.println(" equals " + string1.equals( string2));//内容相同

            // 使用intern 函数指向字符串池中的对象
            // string1 和 string3 指向常量池中同一块地址
            System.out.println(string1 == string3);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
