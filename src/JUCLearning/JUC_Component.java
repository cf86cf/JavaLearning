package JUCLearning;


/**
 *
 * @author   Gaoooyh
 * @version  7/16/2019
 * @see      java.util.concurrent;
 * @since    JDK1.8
 *
 * Todo : 介绍一下 J.U.C 的构成 (tools locks atomic collections executor)5 部分
 *
 * ----------------------------------------------------------------------------------------------------------------------------------
 * AQS  AbstractQueuedSynchronizer
 *
 * 使用Node实现FIFO队列，可以用于构建锁或者其他同步装置的基础框架
 *
 * 利用一个int类型的表示状态
 *
 * 通过继承使用
 * 子类通过继承并实现它的方法（acquire 和 release）方法操纵状态
 *
 * 可以同时实现排他锁和共享锁模式（独占和共享功能）
 *
 * AQS 同步组件
 * CountDownLatch  闭锁，通过一个计数 保证线程是否需要阻塞
 * Semaphore  能保证同一时间并发线程的个数
 * CyclicBarrier  能够阻塞线程
 * 【ReentrantLock】 （lock 的使用）
 * Condition 的使用
 * FutureTask (和Runnable Callable 比较）
 *
 */
public class JUC_Component {
}
