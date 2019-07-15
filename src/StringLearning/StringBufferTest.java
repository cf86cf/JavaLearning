package StringLearning;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 *
 * @author   Gaoooyh
 * @version  7/15/2019
 * @see      java.util.concurrent.Executors;
 * @since    JDK1.8
 *
 * TODO 检测StringBuffer是一个线程安全的类
 *
 * stringBuffer 内实现时加入了synchronized关键字
 *
 * 优点：支持多线程
 */
public class StringBufferTest {
    public static int clientTotal  = 5000;

    public static int threadTotal = 200;

    public static StringBuffer stringBuffer = new StringBuffer();

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);


        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    add();
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
        System.out.println("StringBuilder.length " + stringBuffer.length());
    }
    public static void add(){
        stringBuffer.append("G");
    }
}
