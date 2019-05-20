package ThreadTest;

import static java.lang.Thread.sleep;

/**
 * Java 创建线程
 *
 * 1 继承 thread 类 并重写 run 方法, 通过start()方法启动
 * 2 实现Runnable 接口 并作为参数传给Thread 变量并start
 *
 * Java 只允许继承一个类 但是可以实现多个接口
 * Thread 类实现了Runnable 接口
 *
 * 线程状态： 创建（new  ,就绪（runnable , 运行（running  ,
 * 阻塞（blocked ） ,time waiting(睡眠或者等待一定的事件） , waiting（等待被唤醒） ,消亡(dead)
 */
public class JavaThread {

    static class ThreadTest extends Thread{
        private  static int num=0;
        public ThreadTest(){
            num++;
        }

        @Override
        public void run(){
            System.out.println("num " + num +"  --thread id: " + Thread.currentThread().getId());
        }
    }

    static class RunnableTest implements Runnable{
        private static int num=0;
        public RunnableTest(){
            num++;
        }
        @Override
        public void run() {
            System.out.println("num " + num +"  --thread id: " + Thread.currentThread().getId());
        }
    }
    public static void main(String [] args){
        ThreadTest threadTest;
        System.out.println("extends  * * * * * * * * * * * * * * * * *");
        /*对比 start 和 run 的区别
            start 是创建新的线程并执行 run
            （新线程创建 不会阻塞主线程的执行
            run 是在主线程中直接执行run  (与普通方法调用无差别
         */
        //输出结果显然
        for (int i=0;i <50;i++){
            threadTest= new ThreadTest();
            threadTest.start();
        }
        for (int i=0;i <50;i++){
            threadTest= new ThreadTest();
            threadTest.run();
        }

        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("implements * * * * * * * * * * * * * * * * *");

        //runnable 的使用方法， 把RunnableTest 中的run 方法作为新线程对象的run方法
        RunnableTest runnableTest;
        for (int i=0;i <50;i++){
            runnableTest= new RunnableTest();
            Thread thread = new Thread(runnableTest);
            thread.start();
        }


    }
}

