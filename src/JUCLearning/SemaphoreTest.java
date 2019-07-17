package JUCLearning;

import java.util.concurrent.*;

/**
 *
 * @author   Gaoooyh
 * @version  7/16/2019
 * @see      java.util.concurrent.Semaphore;
 * @since    JDK1.8
 *
 * @brief: 专门测试一下Semaphore 类的使用 （以及如何舍弃没有获得信号量的线程）
 *
 * semaphore 初始化时 设置允许的信号量的个数
 * 当 个数为 1 时，和单线程 就很相似了
 *
 * semaphore 主要用到两个方法
 * acquire 和release
 * acquire（int permit)  获得许可
 * release (int permit)  释放许可
 *
 * 可以一次性申请获得/释放多个许可
 * acquire(3) / release(3)
 *
 * 这段代码在其他测试中使用过很多次，故不再执行
 *                     semaphore.acquire();//获取一个许可
 * //                    semaphore.acquire(3);
 *                     test(threadNum);
 *                     semaphore.release();//释放一个许可
 * //                    semaphore.release(3);
 *
 * 【如何舍弃一个没有获得信号量的线程（不等待信号量的释放）】
 * 使用tryAcquire()
 *
 * tryAcquire() 尝试获取一个许可
 * tryAcquire(int permits)  尝试获取多个许可
 * tryAcquire(long timeout, TimeUnit unit)  等待时间内尝试获取许可
 * tryAcquire(int permits, long timeout, TimeUnit unit) 等待时间内尝试获取多个许可
 */
public class SemaphoreTest {
    private final static int threadCount = 20;

    public static void main(String[] args) throws Exception{
        ExecutorService exec = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < threadCount; i++) {
            final int threadNum = 1+i;
            exec.execute(()->{
                try{
                    if(semaphore.tryAcquire(15,TimeUnit.MILLISECONDS)){  //尝试获取一个许可
                        test(threadNum);
                        semaphore.release();
                    }

                } catch (Exception e){
                    System.out.println("exception "+ e);
                }
            });
        }

        exec.shutdown();
    }
    private static void test (int threadNum) throws Exception{
        System.out.println("threadNum : " +threadNum);
        Thread.sleep(10);
    }
}
