package JUCLearning;

import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author   Gaoooyh
 * @version  7/17/2019
 * @see java.util.concurrent.locks.ReentrantLock;
 * @since    JDK1.8
 *
 * @brief ReentrantLock 通过与synchronized 对比进行学习
 *
 * 可重入性，（进入一次，锁的次数就自增1，锁的次数为0时才释放）
 *
 * 锁的实现， synchronized 是JVM实现的，ReentrantLock是JDK实现的
 *
 * 性能区别， synchronized优化后，性能相差不大，更好用，也容易使用
 *
 * 功能区别， ReentranLock 需要手动加锁和释放锁，更加灵活
 * ----------------------------------------------------------------------------------------------------------------------------------
 * (ReentrantLock 高级锁定类，除非使用到下面的高级功能，或者有明确性能差，否则尽量不用）
 * ReentrantLock 特有功能：
 * 可以指定是公平锁还是非公平锁（默认非公平锁）
 * 提供了一个Condition类，可以分组唤醒需要唤醒的线程
 *
 * 提供了能够中断等待锁的线程的机制，lock.lockInterruptibly()
 * ----------------------------------------------------------------------------------------------------------------------------------
 * ReentrantLock() 默认非共平锁
 * 或者ReentrantLock(boolean fair) 传入true 时构造公平锁
 *
 * tryLock  和前面学的tryAcquire 很相似
 * 尝试加锁，（同样可以传入时间参数）
 *
 * 还有很多其他的函数... 具体参见ReentrantLock实现
 * ----------------------------------------------------------------------------------------------------------------------------------
 * 公平锁（Fair）：加锁前检查是否有排队等待的线程，优先排队等待的线程，先来先得
 * 非公平锁（Nonfair）：加锁时不考虑排队等待问题，直接尝试获取锁，获取不到自动到队尾等待
 *
 * 非公平锁性能比公平锁高5~10倍，因为公平锁需要在多核的情况下维护一个队列
 */
public class ReentrantLockTest {
    public static int clientTotal  = 5000;

    public static int threadTotal = 200;

    public static int count = 0;

    private final static Lock lock = new ReentrantLock();

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    addLock();
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

    /*
    在add() 方法中执行对加锁
     */
    public static void addLock(){
        lock.lock();
        try{
            count++;
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
