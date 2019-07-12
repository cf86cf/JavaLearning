package ThreadTest;

import java.util.Random;
import java.util.concurrent.*;

/**
 * ----------------------------------------------------------------------------------------------------------------------------------
 * 创建线程池  ExecutorService es = Executors.new*******();
 *
 * newFixedThreadPool(int nThreads) 创建固定数目线程的线程池
 * newCachedThreadPool() 创建一个可缓存的线程池，调用execute将重用以前构造的线程（如果线程可用）。
 * 如果现有线程没有可用的，则创建一个新线程并添加到池中。终止并从缓存中移除那些已有 60 秒钟未被使用的线程
 *
 * newSingleThreadExecutor() 创建一个单线程化的Executor
 * newScheduledThreadPool(int corePoolSize) 创建一个支持定时及周期性的任务执行的线程池，多数情况下可用来替代Timer类
 *
 * ----------------------------------------------------------------------------------------------------------------------------------
 * 自定义线程池
 *  public ThreadPoolExecutor(int corePoolSize,int maximumPoolSize,long keepAliveTime,TimeUnit unit,BlockingQueue<Runnable> workQueue);
 *
 * corePoolSize 核心池大小
 * maximumPoolSize 线程池最大大小
 * 线程池中空闲线程所能持续的最长时间 KeepAliveTime
 * 持续时间的单位
 * 任务缓存队列  （capacity 队列大小）
 *
 * 如果当前线程池中的线程数目小于corePoolSize，则每来一个任务，就会创建一个线程去执行这个任务；
 *
 * 如果当前线程池中的线程数目>=corePoolSize，则每来一个任务，会尝试将其添加到任务缓存队列当中，
 * 若添加成功，则该任务会等待空闲线程将其取出去执行；若添加失败（一般来说是任务缓存队列已满），则会尝试创建新的线程去执行这个任务；
 *
 * 如果当前线程池中的线程数目达到maximumPoolSize，则会采取任务拒绝策略进行处理；
 *
 * 如果线程池中的线程数量大于corePoolSize时，如果某线程空闲时间超过keepAliveTime，线程将被终止，
 * 直至线程池中的线程数目不大于corePoolSize；如果允许为核心池中的线程设置存活时间，那么核心池中的线程空闲时间超过keepAliveTime，线程也会被终止。
 *
 * ----------------------------------------------------------------------------------------------------------------------------------
 * 排队策略
 *
 * 直接提交。缓冲队列采用 SynchronousQueue，它将任务直接交给线程处理而不保持它们。
 * 如果不存在可用于立即运行任务的线程（即线程池中的线程都在工作），则试图把任务加入缓冲队列将会失败，
 * 因此会构造一个新的线程来处理新添加的任务，并将其加入到线程池中。
 * 直接提交通常要求无界 maximumPoolSizes（Integer.MAX_VALUE） 以避免拒绝新提交的任务。newCachedThreadPool 采用的便是这种策略。
 *
 * 无界队列。使用无界队列（典型的便是采用预定义容量的 LinkedBlockingQueue，理论上是该缓冲队列可以对无限多的任务排队）
 * 导致在所有 corePoolSize 线程都工作的情况下将新任务加入到缓冲队列中。
 * 这样，创建的线程就不会超过 corePoolSize，也因此，maximumPoolSize 的值也就无效了。
 * 当每个任务完全独立于其他任务，即任务执行互不影响时，适合于使用无界队列。newFixedThreadPool采用的便是这种策略。
 *
 * 有界队列。当使用有限的 maximumPoolSizes 时，
 * 有界队列（一般缓冲队列使用 ArrayBlockingQueue，并制定队列的最大长度）有助于防止资源耗尽，
 * 但是可能较难调整和控制，队列大小和最大池大小需要相互折衷，需要设定合理的参数。
 *
 * ----------------------------------------------------------------------------------------------------------------------------------
 * callable 和runnable的区别
 * runnable中有run方法 没有返回值  不能抛出异常
 * callable中有call方法 有返回值  可以抛出异常
 *
 * 运行Callable任务可拿到一个Future对象， Future表示异步计算的结果。 
 * Callable提供了检查计算是否完成的方法，以等待计算的完成，并检索计算的结果。 
 * 通过Future对象可了解任务执行情况，可取消任务的执行，还可获取任务执行的结果。 
 * Callable是类似于Runnable的接口，实现Callable接口的类和实现Runnable的类都是可被其它线程执行的任务
 */
public class ThreadExecutor {

     static class MyTask implements Runnable {
        private int taskNum;

        public MyTask(int num) {
            this.taskNum = num;
        }

        @Override
        public void run() {
            System.out.println("正在执行task "+taskNum);
            try {
                Thread.currentThread().sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task "+taskNum+"执行完毕");
        }
    }




    public static void main(String[] args) {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(5));

        for(int i=0;i<15;i++){
            MyTask myTask = new MyTask(i);
            executor.execute(myTask);
            System.out.println("线程池中线程数目："+executor.getPoolSize()+"，队列中等待执行的任务数目："+
                    executor.getQueue().size()+"，已执行完的任务数目："+executor.getCompletedTaskCount());
        }
        /*
        当 线程池中的线程数量达到corePoolSize时，新任务进入缓存队列，
        当 缓存队列 达到 最大capacity 时， 给新任务分配额外线程 （maximumPoolSize 中额外的线程）
        当 线程池中的线程数量达到maximumPoolSize时，采取 任务拒绝策略
         */
        executor.shutdown();
        //----------------------------------------------------------------------------------------------------------------------------------
        //callable简单使用

        Callable<Integer> callable = new Callable<Integer>() {
            public Integer call() throws Exception {
                return new Random().nextInt(100);
            }
        };
        FutureTask<Integer> future = new FutureTask<Integer>(callable);
        new Thread(future).start();
        try {
            System.out.println("callable返回的值是 " + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}


