package ThreadTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 *
 * @author   Gaoooyh
 * @version  7/16/2019
 * @see      java.util.concurrent.Executors;
 * @since    JDK1.8
 *
 * TODO 检测Collections.synchronizedList是一个线程安全的类
 *
 * Collections.synchronizedXXX (list set map  )
 */

public class syncTest {
    public static int clientTotal  = 5000;

    public static int threadTotal = 200;

    public static List <Integer>list = Collections.synchronizedList(new ArrayList());
    //public static List <Integer>list = new ArrayList();  这是线程不安全的，其结果总是小于5000

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);


        for (int i = 0; i < clientTotal; i++) {
            int finalI = i;
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    add(finalI);
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
        System.out.println("List.size() " + list.size());
    }
    public static void add(int i){
        list.add(i);
    }
}
