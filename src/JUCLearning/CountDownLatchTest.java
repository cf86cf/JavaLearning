package JUCLearning;

/**
 *
 * @author   Gaoooyh
 * @version  7/16/2019
 * @see      java.util.concurrent.CountDownLatch;
 * @since    JDK1.8
 *
 * @brief: 专门测试一下CountDownLatch 类的使用
 *
 * countDownlatch 的使用方法
 *
 * 初始化时给定一个线程数
 * 每次使用时执行countDown 函数
 * 当count 值减为0 后才能继续执行后面的线程
 *
 * await 方法保证了当前线程必须执行完才能继续后面的线程
 *
 * await(time, time_type)
 * countDownLatch.await(10,TimeUnit.MILLISECONDS);
 *
 * 等待时间结束后继续执行下面的过程
 *
 * 加上时间参数的另一个好处， 防止因为没有写countDown 而出现死等待的情况
 */

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CountDownLatchTest {

    private final static int threadCount = 200;

    public static void main(String[] args) throws Exception{
        ExecutorService exec = Executors.newCachedThreadPool();

        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            exec.execute(()->{
                try{
                    test(threadNum);
                } catch (Exception e){
                    System.out.println("exception "+ e);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        //能够看到finish先输出 （等待时间结束后就继续执行下面的内容
        countDownLatch.await(10,TimeUnit.MILLISECONDS);

        //finish会在200个线程全部执行完后才输出
//        countDownLatch.await();

        System.out.println("Finish");

        exec.shutdown();
    }
    private static void test (int threadNum) throws Exception{
        Thread.sleep(100);
        System.out.println("threadNum : " +threadNum);
        Thread.sleep(100);
    }
}
