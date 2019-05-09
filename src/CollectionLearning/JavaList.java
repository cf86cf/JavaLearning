package CollectionLearning;

import java.util.*;

/**
 * List 主要学习
 * ListIterator
 * ArrayList 和 Vector
 * LinkedList
 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * List额外增加了ListIterator()迭代器，
 * 返回一个ListIterator 对象
 *
 * 与Iterator比较，ListIterator 增加了
 * add(E e)  #向List中插入数据，插入位置为迭代器当前位置之前
 * set(E e)：从列表中将next()或previous()返回的最后一个元素更改为指定元素e
 *
 * hasPrevious()    #与 hasNext() 相对应，判断迭代器前面是否还有元素
 * previous()   #与 next() 相对应，返回列表中ListIterator指向位置前面的元素
 *
 * nextIndex()  #返回列表中ListIterator所需位置后面元素的索引
 * previousIndex()  #返回列表中ListIterator所需位置前面元素的索引
 *
 * List、ArrayList、LinkedList和Vector 可以使用ListIterator
 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *
 * ArrayList 和 Vector
 * 动态扩展的 数组
 * 初始值默认为 10
 * private static final int DEFAULT_CAPACITY = 10;
 *
 * ArrayList 非线程安全
 * Vector 线程安全
 *
 * Vector 性能比ArrayList差
 *
 * ArrayList 遍历方式有 三种
 * 迭代器遍历，for循环，随机访问  （随机访问效率最高
 *
 *  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *
 *  LinkedList 以链表的形式保存元素
 *  在插入，删除时效率高
 *  随机访问元素的性能较差
 */
public class JavaList {
    public static void main(String[] args){
        List<String> list = new LinkedList<>();
        list.add("Gaoooyh");
        list.add("Hiawons");
        list.add("Snowyman");
        ListIterator<String> listIterator = list.listIterator();
        //向后遍历
        System.out.println("ListIterator get next and nextIndex");
        while (listIterator.hasNext()){
            System.out.print(listIterator.nextIndex() + "." + listIterator.next() + "  " );
        }
        listIterator.set("SNOWYMAN");
        //向list 中添加一个 新元素
        System.out.println("\nAdd a string and get previous");
        listIterator.add("Lcc's cat");

        /*
         * 使用previous 向前遍历,
         * snowman 已经被修改了
         * 新加了 Lcc's cat
         */
        while (listIterator.hasPrevious()){
            System.out.print(listIterator.previousIndex() + "." + listIterator.previous() + "  ");
        }

        //修改元素
        listIterator.set("GAOOOYH");
        System.out.println("\n"+listIterator.next());
         //* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

        // i 值较小时，遍历所用时间 差异太小
        ArrayList<Integer> arrayList = new ArrayList<>();
        for(int i=0;i<1000000;i++){
            arrayList.add((int)(Math.random()*100));
        }


//        for(int i=0;i<10;i++)
//            System.out.print(arrayList.get(i) + " ");
//            System.out.println();

        long start,end;  //比较三种遍历方式的用时
        //遍历方式
        System.out.println("1.Iterator遍历:");
        Iterator<Integer> iterator = arrayList.iterator();
        start = System.currentTimeMillis();
        while (iterator.hasNext()){
//            System.out.print( iterator.next() + " ");
            iterator.next();
        }
        end = System.currentTimeMillis();
        System.out.println("运行时间：" + (end - start) + "毫秒");


        System.out.println("2.for循环遍历:");
        start = System.currentTimeMillis();
        for(int i:arrayList) {
//            System.out.print(i + " ");
        }
        end = System.currentTimeMillis();
        System.out.println("运行时间：" + (end - start) + "毫秒");


        System.out.println("3.随机访问遍历:");
        start = System.currentTimeMillis();
        for(int i=0;i<arrayList.size();i++){
//            System.out.print(arrayList.get(i) + " ");
        }
        end = System.currentTimeMillis();
        System.out.println("运行时间：" + (end - start) + "毫秒");
        //* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

        LinkedList<Integer> linkedList = new LinkedList<>();

    }
}