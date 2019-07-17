package JUCLearning;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author   Gaoooyh
 * @version  7/17/2019
 * @see java.util.concurrent.locks.Condition;
 * @since    JDK1.5
 *
 * @brief Condition的 使用
 *
 * 基于ReentrantLock 的同步组件
 *
 * AQS从头到尾顺序唤醒线程，直到等待队列中的线程被执行完毕结束
 *
 * 每个线程只能存在于同步队列或等待队列中的一个
 */
public class ConditionTest {
    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();

        //线程A
        new Thread(()->{
            try {
                reentrantLock.lock();   //获取锁，加入到AQS 同步队列中
                System.out.println("wait signal");//1
                condition.await();  //从AQS队列中移除，（锁释放），加入到condition的等待队列中
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("get signal");//4
            reentrantLock.unlock();
        }).start();

        //线程B
        new Thread(()->{
            reentrantLock.lock();   //由于线程A释放锁 而被唤醒,
                        // 判断为AQS同步队列头节点且同步状态为0,  从而线程B 获取锁
            System.out.println("get lock");//2
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            condition.signalAll();  //发送信号 线程一重新加入到AQS同步队列中（线程并未唤醒
            System.out.println("send signal");//3
            reentrantLock.unlock();     //释放锁，线程A被唤醒, 线程A成为AQS首节点并且同步状态可获取，继续执行
        }).start();
    }
}
