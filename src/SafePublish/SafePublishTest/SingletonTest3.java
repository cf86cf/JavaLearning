package SafePublish.SafePublishTest;

/**
 * 枚举模式  最安全的
 *
 * 相比懒汉模式 安全性能更有保证
 * 相比饿汉模式 在实际调用时才做最开始的初始化
 */
public class SingletonTest3 {
    private SingletonTest3(){

    }

    public static SingletonTest3 getInstance(){
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton{
        INSTANCE;
        private SingletonTest3 singleton;

        // JVM保证它只调用一次
        Singleton(){
            singleton = new SingletonTest3();
        }

        public SingletonTest3 getInstance(){
            return singleton;
        }
    }

}
