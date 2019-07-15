package ThreadTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * synchronized 用法：
 * 1 修饰代码块，（大括号括起来的代码 作用于调用的对象）
 * 2 修饰方法  整个方法 作用于调用的对象
 * 3 修饰静态方法 整个静态方法，作用于所有的对象
 * 4 修饰类 括号括起来的部分，作用于所有的对象
 *
 * synchronized  不可中断锁，适合竞争不激烈时   可读性好
 *
 * （lock, 可中断锁， 多样化同步 竞争激烈时能维持常态
 * （Atomic 竞争激烈时能维持常态，性能比lock好， 但是只能同步一个值
 *----------------------------------------------------------------------------------------------------------------------------------
 * synchronized 可见性
 *
 * 线程解锁时，将工作内存中的 共享变量的最新值 写回到主存中
 *
 * 线程加锁时，清空工作内存中 共享变量的值，使用共享变量时读取主存中最新的值
 *----------------------------------------------------------------------------------------------------------------------------------
 * 修饰代码块和方法  作用相似
 * 修饰静态方法和修饰类  作用相似
 *
 * 同一个对象中不同的方法(同步块修饰） 先后顺序执行
 * 不同对象 相同方法可以交替执行（不同对象互相不影响）
 *
 * 类中静态方法，如果包含了同步块，
 * 不同对象 相同方法顺序执行 （不会交替）
 *
 * ----------------------------------------------------------------------------------------------------------------------------------
 * 如果一个子类继承父类，
 * 子类继承到的父类中包含synchronized 的方法 不包含synchronized 特性
 *
 * 如果子类想将该方法设成同步代码，需要再显示的添加synchronized
 *
 *
 */


public class synchronizedTest {

    public void test1(){
        synchronized (this){   //
            for(int i=0;i < 10;i ++){
                System.out.println(Thread.currentThread().getName()+ " Test1 - "+i);
            }
        }
    }

    public synchronized  void test2(){
        for(int i=0;i < 10;i ++){
            System.out.println(Thread.currentThread().getName()+ " Test2 - "+i);
        }
    }

    static class synchronizedClass{
        public static void test1(){
            synchronized (synchronizedClass.class){   //
                for(int i=0;i < 10;i ++){
                    System.out.println(Thread.currentThread().getName()+ " Test1 - "+i);
                }
            }
        }
        public static synchronized void test2(){
            for(int i=0;i < 10;i ++){
                System.out.println(Thread.currentThread().getName()+ " Test2 - "+i);
            }
        }
    }

    public static void main(String[] args){
        synchronizedTest example = new synchronizedTest();
        synchronizedTest example1 = new synchronizedTest();

        synchronizedClass classexample = new synchronizedClass();
        synchronizedClass classexample1 = new synchronizedClass();

        ExecutorService executorService = Executors.newCachedThreadPool();

        /*
        同一个类中不同的方法先后顺序执行， 不同类之间 可以交替执行（不同对象不影 响）
         */
        executorService.execute(()->{
            example.test2();
        });
//        executorService.execute(()->{
//            example.test1();
//        });

        executorService.execute(()->{
            example1.test2();
        });



        executorService.execute(()->{
            classexample.test2();
        });

        executorService.execute(()->{
            classexample1.test1();
        });

        executorService.shutdown();
    }
}
