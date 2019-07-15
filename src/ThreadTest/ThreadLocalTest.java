package ThreadTest;

/**
 * 线程封闭 ThreadLocal
 *
 * 线程封闭的方法
 * Ad-hoc 程序控制实现，最糟糕 忽略
 * 堆栈封闭 ： 局部变量 无并发问题
 * ThreadLocal 线程封闭 (特别好的封闭方法)
 *
 */

public class ThreadLocalTest {
    private final static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
    public static void add(int id){
        threadLocal.set(id);
    }
    public static int getId(){
        return threadLocal.get();
    }
    public static void remove(){
        threadLocal.remove();
    }

    public static void main(String[] args) {

    }
}
