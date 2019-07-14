package StringLearning;

/**
 * 有趣的题目：
 * String str1 = new StringBuilder("ja").append("va").toString();
 * System.out.println(str1.intern() == str1);
 *
 * String str2 = new StringBuilder("Gaooo").append("yh").toString();
 * System.out.println(str2.intern() == str2);
 * 结果返回的是 false true
 *
 * JDK 1.7中 intern() 不复制实例, 只在常量池中记录首次出现的实例引用
 *
 * gaoooyh 在常量池中首次出现, 故intern()返回的引用和由StringBuilder创建的字符串实例是同一个
 *
 * 而java 在执行StringBuilder.toString()之前就已经出现过,字符串常量池中有它的引用,不是"首次出现"
 */
public class ConstantPool {

    public static void main(String[] args){
        String str1 = new StringBuilder("ja").append("va").toString();
        System.out.println(str1.intern() == str1);
        String str2 = new StringBuilder("gaooo").append("yh").toString();
        System.out.println(str2.intern() == str2);

    }
}
