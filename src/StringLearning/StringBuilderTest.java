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
 * TODO 检测StringBuilder是一个线程不安全的类
 *
 * 传入clientThread 请求5000次，每次append 一个字符
 * 输出结果 小于5000次
 *
 * 优点 单线程性能更优 计算速度更快
 */
public class StringBuilderTest {
    public static int clientTotal  = 5000;

    public static int threadTotal = 200;

    public static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    addchar();
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
        System.out.println("StringBuilder.length " + stringBuilder.length());
    }
    public static void addchar(){
        stringBuilder.append("G");
    }
}
