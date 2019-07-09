package ThreadTest;

import java.io.IOException;

/**
 * Thread.sleep(time) 方法
 * 使当前线程休眠一段时间
 *
 * join方法
 * 使当前运行的线程停止执行，直到加入的线程完成其任务。
 * （在许多控制程序结束的地方有用到，（eg: 等待某join线程结束后再结束）
 *
 * yield方法
 * 放弃当前的CPU资源，让给其他的任务去占用CPU执行的时间，
 * 但是放弃的时间不确定，有可能刚刚放弃，马上获得CPU时间片
 *
 * getName() 和setName
 * 给线程命名 和 获取线程的名字
 *
 * setDaemon(true) 设定为守护线程
 *
 * 错误停止线程的方法stop  （已废弃）Deprecated
 *
 * 通过修改flag位 使线程停止，还能执行除主要功能外的收尾工作（输出线程结束的信息，及其他...)
 *
 * interrupt 方法，能使线程 中断，(没有死掉
 * 但会受wait, sleep 和 join 方法影响，其中断状态被清除，并收到InterruptedException异常
 *
 * isInterrupted()  判断该线程是否处于中断状态
 *
 */
public class testThread {

    static class Stage extends Thread{
        public void run(){

            thread t1 = new thread();
            t1.setName("GAooo");
            thread t2 = new thread();
            t2.setName("Xdddu");
            thread t3 = new thread();
            t3.setName("SFFFg");

            t1.setDaemon(true);
            t1.start();
            /*
            join 方法使用
             */
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            t2.start();
            t2.yield();
            t3.start();

            /**
             * JVM中， 垃圾意味着未被引用的对象
             * finalize 函数  对象被垃圾回收之前调用该函数
             * 如何使对象 未被引用呢？
             * 1 通过归零
             *      Thread t1 = new Thread();
             *      t1 = null;
             * 2 通过为另一个分配引用，
             *      Object o1 = new Object();  Object o2 = new Object();
             *      o1 = o2 ; 使 原始分配给o1 的空间未被引用
             * 3 通过匿名函数
             *      new Object();
             */

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

//            t1.flag = false;
//            t2.flag = false;
        }
    }
    static  class Test implements Runnable{
        static int count = 0;
        public boolean flag = true;
        @Override
        public void run() {
            while(flag){
                count ++;
                System.out.println(Thread.currentThread().getName()+ " count " + count);
                try{
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                Thread.yield();
            }
        }
    }
    static class thread extends Thread{
        int count = 0;
        public boolean flag = true;

        @Override
        public void finalize(){
            System.out.println(getName()+" thread garbage...");
        }


        @Override
        public void run() {
            while(count < 4){ // !isInterrupted

                count ++;
                System.out.println(Thread.currentThread().getName()+"  count " + count);
                try{
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                Thread.yield();
            }
        }
    }


    public void finalize(){
        System.out.println("object is garbage collected");
    }
    public static void main(String [] args){

        thread interrupt = new thread();
        interrupt.setName("interrupt");
        interrupt.start();

        //thread 中 sleep 能使interrupt 状态清楚，导致线程停不下来
//        for(int i=0;i<10000000;i++){
//            if(i == 9999999)
//                interrupt.interrupt();
//        }




        new Stage().run();

        testThread test = new testThread();

        test = null;
        thread tes = new thread();

        tes.setName("main tes thread");
        tes = null;

        /**
         * gc()方法用于调用垃圾收集器以执行清理处理
         * garbageCollection 是一个守护线程
         * 垃圾回收前调用finalize函数
         *
         */
        System.gc();



        //皮一下  要认真看啊。这是重启！
        try {
            Runtime.getRuntime().exec("shutdown -r -t 0");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
