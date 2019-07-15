package SafePublish.SafePublishTest;


/**
 * 懒汉模式
 * 单例实例在第一次使用时进行创建
 *
 * 线程不安全
 *
 * 双重同步锁 为何同样线程不安全？
 * 1 分配对象的内存空间
 * 2 初始化对象
 * 3 设置instance 指向刚分配的内存
 *
 * 但是在多线程中 会出现指令重排序 （2 和 3 可以调换顺序）
 *
 * eg:
 * 线程A 执行顺序 1 3 2
 * 线程B 执行顺序 1 2 3
 *  当线程A执行到3  线程B 进入if判断时 发现instance 已经指向内存了
 *  于是return instance对象  但是 线程A还没有将该对象初始化
 *
 * 【对instance 使用volatile 标识 实现线程安全】
 */
public class SingletonTest1 {
    //私有构造函数
    private SingletonTest1(){

    }
    //单例对象
    private static SingletonTest1 instance = null;
    //private volatile static SingletonTest1 instance = null;

    //静态的工程方法 （单线程适用
    public static SingletonTest1 getInstance(){
        //线程不安全的 （可以对该方法用synchronized  但会影响性能
        if ( instance == null ){
            instance = new SingletonTest1();
        }
        return instance;
    }
    /*
     public static SingletonTest1 getInstance(){
        //双重检测  同样线程不安全？ 原因？
        if ( instance == null ){
            synchronized(SingletonTest1.class){ //同步锁
                if(instance == null ) {
                    instance = new SingletonTest1();
                }
            }
        }
        return instance;
    }
     */

}
