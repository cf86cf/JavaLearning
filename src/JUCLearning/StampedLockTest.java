package JUCLearning;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.StampedLock;

/**
 *
 * @author   Gaoooyh
 * @version  7/17/2019
 * @see java.util.concurrent.locks.StampedLock;
 * @since    JDK1.8
 *
 * @brief StampedLock 使用
 *
 * 当只有少量竞争者的时候，使用synchronized
 * 当竞争者有很多，而且线程增长的趋势是可以预估时，使用ReentrantLock
 *
 * synchronized  JVM会自动解锁，
 * 而其他的锁实现，如果没有对其解锁，可能造成死锁
 *
 *
 */
public class StampedLockTest {
    public static int clientTotal  = 5000;

    public static int threadTotal = 200;

    public static int count = 0;

    private final static StampedLock lock = new StampedLock();

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    addWriteLock();
                    semaphore.release();
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        System.out.println("count = " +count);
    }

    public static void addWriteLock(){
        long stamp = lock.writeLock();
        try{
            count++;
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            lock.unlock(stamp);
        }
    }

}
