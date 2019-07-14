package ThreadTest;


import jdk.nashorn.internal.objects.annotations.Getter;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;


/**
 * 原子性操作
 * Atomic 包 部分类的使用
 * AtomicInteger
 * getAndIncrement()方法
 * for (;;) {
 *         int current = get();
 *         int next = current + 1;
 *         if (compareAndSet(current, next))
 *             return current;
 *     }
 *
 * incrementAndGet() 方法返回的是 next的值,  (唯一区别)
 *
 *
 * LongAdder 类 基本和AtomicInteger相同, 同样能够实现原子性的加一操作
 *
 * Atomic 中 加一操作是 一直循环 直到符合条件才进行加一操作,
 *  在线程任务很多的时候 性能不好,  但是 它实现简单, 更直接,
 *  在线程任务简单时使用  (性能稍微高一点)
 *
 * LongAdder 多任务时 通过分散提高性能,  优先使用高并发
 *  但是 如果统计时有并发更新 ,它的统计信息可能会出现误差
 * ----------------------------------------------------------------------------------------------------------------------------------
 *
 * AtomicBoolean 中 有一个compareAndSet (boolean expect , boolean update)
 * 用于多线程时 当实现某个条件时 修改flag位操作
 * ----------------------------------------------------------------------------------------------------------------------------------
 *
 * AtomicIntegerFieldUpdater 需要传入 类 和访问的字段名称
 * AtomicIntegerFieldUpdater <Integer> update  = AtomicIntegerFieldUpdater.newUpdater(AtomicTest.class , "count");
 *
 * @Getter
 * private volatile int count = 100;  //必须有 volatile
 *
 * ----------------------------------------------------------------------------------------------------------------------------------
 *
 * semaphore(int permits)  信号量 (permits 允许线程的数量
 * require 和 release
 *
 * countDownLatch(int count)  //等待count个线程工作完  countDown 计数减一
 * ----------------------------------------------------------------------------------------------------------------------------------
 * AtomicStampReference 解决 ABA 问题
 * ABA问题, 某个值 从 A修改到了B  又修改回了A, 此时再有其他线程使用该值时, 会认为该值没有被修改
 *
 * AtomicStampReference 类
 * 对值加一个stamp属性  当值发生改变时,stamp ++ , 这样就算再改回原值 也能判断其发生过改变
 * ----------------------------------------------------------------------------------------------------------------------------------
 */
public class AtomicTest {
    public static int threadTotal = 50;
    public static int taskTotal = 2000;

    public static AtomicInteger atomicInteger = new AtomicInteger(  0);

    private static AtomicIntegerFieldUpdater<AtomicTest> update  = AtomicIntegerFieldUpdater.newUpdater(AtomicTest.class , "count");


    public volatile int count = 100;  //必须有 volatile

    public static AtomicTest test = new AtomicTest();


    public static void main(String[] args){

        final  Semaphore semaphore = new Semaphore(threadTotal);

        atomicInteger.compareAndSet(1,5); //no
        atomicInteger.compareAndSet(0,5); //yes
        atomicInteger.compareAndSet(0,6); //no
        atomicInteger.compareAndSet(5,8); //yes
        System.out.println(atomicInteger.get());

        atomicInteger.set(0);

        CountDownLatch countDownLatch = new CountDownLatch(taskTotal);
        ExecutorService executorService = Executors.newCachedThreadPool();

        for(int i=0;i < taskTotal;i++) {
            executorService.execute(() -> {
                try{
                    semaphore.acquire();
                    add();
                    semaphore.release();

                } catch (Exception e){
                    System.out.println("Error " + e);
                }
                countDownLatch.countDown();
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        System.out.println(atomicInteger.get());


        System.out.println("------------------------------------------------");

        if(update.compareAndSet(test,100,500)){
            System.out.println("Change the count success "+ test.count);
        }
        if(update.compareAndSet(test,100,600)){
            System.out.println("Change the count success "+ test.count);
        }
        else{
            System.out.println("Change the count faild "+ test.count);
        }
    }
    public static void add(){
        atomicInteger.incrementAndGet();
        //两个方法作用相似
//        atomicInteger.getAndIncrement();
    }
}
