package JUCLearning;

/**
 *
 * @author   Gaoooyh
 * @version  7/16/2019
 * @see      java.util.concurrent.CyclicBarrier;
 * @since    JDK1.8
 *
 * @brief: 专门测试一下CyclicBarrier 类的使用
 *
 * 【CyclicBarrier】的使用方法：
 * 和countDownlatch 的使用方法相像
 *
 * 初始化时给定一个线程数
 *  通过对线程调用await方法  让线程进入等待（计数器执行加一）
 *  当计数器的值达到CyclicBarrier设置的初始值后，所有await的线程继续执行后续操作
 *
 * 释放线程后可以重用 （被称为循环屏障）
 *
 * await()  一直等待
 * await(long timeout, TimeUnit unit)  等待timeout时长后继续执行
 * （防止出现凑不够指定数量的线程 而出现死等待的情况
 * （如果线程数量13 ， CyclicBarrier初始值为5  使用await()则最后3个线程一直不能继续执行）
 *
 * CyclicBarrier 和 CountDownLatch的【区别】
 *
 * 1 ：CyclicBarrier 可以通过reset 重置，
 *     CountDownLatch只能使用一次
 *
 * 2 ：CyclicBarrier 描述的是多个线程间相互等待，直到所有线程满足条件后，才执行后续操作
 *     CountDownLatch 描述的是一个/多个线程等待其他线程的关系
 *
 * 3 ：CyclicBarrier 给出了更多方法， getNumberWaiting 获取当前阻塞的线程数
 *
 */

import java.util.concurrent.*;

public class CyclicBarrierTest {

    private final static int threadCount = 13;

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5);


    public static void main(String[] args) throws Exception{
        ExecutorService exec = Executors.newCachedThreadPool();



        for (int i = 0; i < threadCount; i++) {
            final int threadNum = 1+i;
            Thread.sleep(1000);
            exec.execute(()->{
                try{
                    race(threadNum);
                } catch (Exception e){
                    System.out.println("exception "+ e);
                }
            });
        }

        exec.shutdown();
    }
    private static void race (int threadNum){

        try{
//            Thread.sleep(1000);
            System.out.println("threadNum : " +threadNum + " is ready");

            //等待时间结束仍没有达到屏障初始值，从而跳过屏障继续执行
            //TimeoutException
            //BrokenBarrierException
            cyclicBarrier.await(4000,TimeUnit.MILLISECONDS);
        } catch (BrokenBarrierException e){
            System.out.println("BrokenBarrierException " + e);
        } catch (InterruptedException e) {
            System.out.println("InterruptedException " + e);
        } catch (TimeoutException e) {
            System.out.println("TimeoutException " + e);
        }

        System.out.println("threadNum : " + threadNum + " continue...");

    }
}
