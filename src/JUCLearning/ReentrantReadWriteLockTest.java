package JUCLearning;

import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *
 * @author   Gaoooyh
 * @version  7/17/2019
 * @see java.util.concurrent.locks.ReentrantReadWriteLock;
 * @since    JDK1.8
 *
 * @brief ReentrantReadWriteLock的学习
 *
 * ReentrantReadWriteLock 有readLock 和writeLock
 *
 * 实现了悲观读取，但会有写入饥饿的问题（读多写少，写入锁一直等待）
 * 当对象没有任何读写锁时，才能取得对象的写入锁【core】
 *
 * 悲观锁：每次拿数据时认为其他线程会修改，从而对数据加锁
 * （其他线程想获取这个数据会一直阻塞直到拿到锁
 * 传统关系型数据库用到了很多，eg:行锁，表锁，读锁，写锁... 都是操作前加锁
 * synchronized 和 ReentrantLock 是悲观锁的实现
 *
 * 乐观锁：每次拿数据时认为其他线程不会修改，所以不上锁
 * 在更新的时候会判断在此期间其他线程是否更新了该数据，
 * 通过版本号机制（解决ABA问题） 和 CAS算法实现
 *
 * 乐观锁适用于【多读少写】应用类型，可以提高吞吐量
 * Java.util.concurrent.atomic包中的原子变量类使用的乐观锁的 CAS实现方式实现的。
 *
 *
 */
public class ReentrantReadWriteLockTest {
    private final Map<String, Date> map = new TreeMap<>();

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private final Lock readlock = lock.readLock();
    private final Lock writelock = lock.writeLock();

    public Date get(String key){
        readlock.lock();
        try {
//            System.out.println("reading...");
            return map.get(key);
        } finally {
            readlock.unlock();
        }
    }

    public Set<String> getAllKeys(){
        readlock.lock();
        try{
//            System.out.println("reading...");
            return map.keySet();
        } finally {
            readlock.unlock();
        }
    }

    public Date put(String key,Date value)  {
        writelock.lock();
        try {
            System.out.println("writing...");
            return map.put(key,value);
        } finally {
            writelock.unlock();
        }

    }
    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        ReentrantReadWriteLockTest map = new ReentrantReadWriteLockTest();

        map.put("gaoooyh",new Date(2019,7,17));
        map.put("xduzy",new Date(1998,8,19));

        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(()->{
            int count = 0;
            while ( count < 10000000) {
                count++;
                map.get("gaoooyh");
            }
            System.out.println(" finished getKey...");
        });
        exec.execute(()->{
            int count = 0;
            while ( count < 10000000) {
                count++;
                map.getAllKeys();
            }
            System.out.println(" finished getAllKey...");
        });

        exec.execute(()->{
            map.put("Yangminya",new Date(2018,5,1));
        });

        exec.shutdown();

    }
}

