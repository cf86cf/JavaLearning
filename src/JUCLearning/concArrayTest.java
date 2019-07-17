package JUCLearning;

import java.util.List;
import java.util.concurrent.*;

/**
 *
 * @author   Gaoooyh
 * @version  7/16/2019
 * @see      java.util.concurrent.CopyOnWriteArrayList;
 * @since    JDK1.8
 *
 * @brief : 测试CopyOnWriteArrayList (并发容器)写入安全
 *
 * 适合读多 写少的场景
 * 当有新的写入操作时,复制原数组的内容到一个新数组,对新数组进行写入,再将地址指向新数组
 *
 * 不能做到实时读取 (能满足最终一致性)
 *
 * 读操作 直接在原数组上进行 get()方法没啥区别
 * 写操作 在副本上进行       add()方法有锁保护
 *
 * ----------------------------------------------------------------------------------------------------------------------------------
 * ArrayList - > CopyOnWriteArrayList
 * HashSet  - > CopyOnWriteArraySet
 * TreeSet - > ConcurrentSkipListSet
 *
 * HashMap - > ConcurrentHashMap (不允许空值)
 * TreeMap - > ConcurrentSkipListMap
 *
 */
public class concArrayTest {
    public static int clientTotal  = 5000;

    public static int threadTotal = 200;

    public static List<Integer> list = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);


        for (int i = 0; i < clientTotal; i++) {
            int finalI = i;
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    write(finalI);
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
        System.out.println("List.size() = " + list.size());
    }
    public static void write(int i){
        list.add(i);
    }
}
