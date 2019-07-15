package SafePublish.SafePublishTest;

/**
 * 饿汉模式
 * 单例实例在类装载时进行创建
 *
 * 线程安全
 */
public class SingletonTest2 {

    private static SingletonTest2 instance = new SingletonTest2();

    /*
    另一种写法
    private static SingletonTest2 instance = null;
    static {
        instance = new SingletonTest2();
    }
     */
    //类构造时如果有过多的处理，导致加载时会特别慢
    private SingletonTest2(){

    }

    public static SingletonTest2 getInstance(){
        return instance;
    }
}
