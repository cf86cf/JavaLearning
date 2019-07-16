package ThreadTest;

import java.util.Iterator;
import java.util.Vector;

/**
 *
 * @author   Gaoooyh
 * @version  7/16/2019
 * @see      java.util.Vector;
 * @since    JDK1.8
 *
 * TODO 使用三种方法foreach iterator for 遍历vector并移除数据 (单线程下异常)
 *
 * 其他容器类似
 *
 * 推荐使用 for循环操作 包含增删操作的遍历
 *
 * 或者另外两种, 将准备增删的位置 做好标记,
 * 遍历结束后再进行增删操作
 *
 */

public class VectorRemoveTest {

    //java.util.ConcurrentModificationException
    public static void remove1(Vector<Integer> vector) {  //foreach
        for (Integer i:vector ) {
            if(i.equals(5)){
                vector.remove(i);
            }
        }
    }

    //java.util.ConcurrentModificationException
    public static void remove2(Vector<Integer> vector){  //iterator
        Iterator iterator = vector.iterator();
        while (iterator.hasNext()){
            Integer i  = (int) iterator.next();
            if(i.equals(5)){
                vector.remove(i);
            }
        }
    }

    //success
    public static void remove3(Vector<Integer> vector){  //for
        for (int i = 0; i < vector.size(); i++) {
            if(vector.get(i).equals(5)){
                vector.remove(i);
            }
        }
    }

    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);
        vector.add(4);
        vector.add(5);
//        remove1(vector);
//        remove2(vector);
        remove3(vector);

    }
}
