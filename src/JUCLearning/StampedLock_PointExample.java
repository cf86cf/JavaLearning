package JUCLearning;

import java.util.concurrent.locks.StampedLock;

/**
 *
 * @author   Gaoooyh
 * @version  7/17/2019
 * @see java.util.concurrent.locks.StampedLock;
 * @since    JDK1.8
 *
 * @brief StampedLock 学习
 *
 * 提供了三种模式的读写控制
 * 使用一个票据stamp(long)  加锁时获取stamp, 解锁时stamp作为参数
 *
 * 写锁 writeLock
 * 悲观读锁 readLock
 * 乐观读锁 tryOptimisticRead
 *
 */
public class StampedLock_PointExample {
    //Class Point
    private double x,y;
    private final StampedLock stampedLock = new StampedLock();

    void move(double deltax,double deltay){
        long stamp = stampedLock.writeLock();
        try {
            x +=deltax;
            y +=deltay;
        } finally {
            stampedLock.unlockWrite(stamp);
        }
    }
    //乐观读锁
    double distanceFromOrigin(){
        long stamp = stampedLock.tryOptimisticRead(); //获得一个乐观读锁
        double currentX = x, currentY = y;  //两个字段读入本地局部变量

        if(!stampedLock.validate(stamp)){   //检查获得乐观读锁后是否有其他写锁发生
            stamp = stampedLock.readLock();  //获得一个悲观锁
            try{
                currentX = x;   //两个字段读入本地局部变量
                currentY = y;
            } finally {
                stampedLock.unlockRead(stamp);  //释放悲观锁
            }
        }
        return Math.sqrt(currentX*currentX+currentY*currentY);
    }

    //悲观读锁
    void moveIfAtOrigin(double newX, double newY) { // upgrade
        // Could instead start with optimistic, not read mode
        long stamp = stampedLock.readLock();
        try {
            while (x == 0.0 && y == 0.0) {  //检查当前状态释放符合
                long ws = stampedLock.tryConvertToWriteLock(stamp); //将读锁转为写锁
                if (ws != 0L) { //确认转为写锁是否成功
                    stamp = ws;  //如果成功，替换票据
                    x = newX;   //x,y 进行状态改变
                    y = newY;
                    break;
                }
                else {     //如果不能转为写锁
                    stampedLock.unlockRead(stamp);  //显式的释放读锁
                    stamp = stampedLock.writeLock();    //显式的直接进行写锁，然后再通过循环再试
                }
            }
        } finally {
            stampedLock.unlock(stamp);  //释放读锁或写锁
        }
    }

}


