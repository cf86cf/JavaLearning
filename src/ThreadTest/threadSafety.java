package ThreadTest;

/**
 * 线程安全：
 * 当多个线程访问一个对象时，如果不用考虑这些线程在运行时环境下的调度和交替执行，
 * 也不惜用进行额外的同步，或者在调用方进行任何其他的协调操作，调用这个对象的行为都可以获得正确的结果
 * 那么这个对象是线程安全的
 *
 * 操作共享的数据分为5类，不可变 绝对线程安全 相对线程安全 线程兼容 线程对立
 *
 * 不可变 final 修饰，不存在对象状态被修改的情况，从而杜绝了不一致的状态
 *
 * 绝对线程安全 （Java中给出的线程安全大多数都不是绝对安全的，
 * vector 是一个线程安全的容器，它的 add get size 方法都被synchronized修饰，
 * 会出现 先判断了size， 然后删掉了该值， 又尝试get该值 从而抛出异常
 *
 * 需要额外的同步块才能保证其正确执行
 *
 * 相对线程安全  vector  hashtable collectios的synchronizedCollection方法包装的集合
 *
 * 线程兼容  对象本身不是线程安全的，但在调用时使用同步手段保证对象在并发的环境中可以安全的使用
 *
 * 线程对立 无论是否采取同步措施，都不能在多线程中并发使用
 * （Thread类的 suspend挂起 和resume继续执行
 *  如果suspend 挂起的线程是即将执行resume的线程，那 肯定会产生死锁  (因为这个原因，这两个方法已经@Deprecated了
 *  System.setIn  System.setOut
 *
 */
public class threadSafety {
}
