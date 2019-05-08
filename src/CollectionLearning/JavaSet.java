package CollectionLearning;

import java.util.*;

/**
 *
 * 简单对比HashSet 和 TreeSet的区别 和用法
 *
 * 面试题：
 *
 * 当经常使用添加、查询操作时，使用HashSet。
 * 当经常插入排序或使用删除、插入及遍历操作时，使用LinkedHashSet。
 *
 * 当需要一个特定排序的集合时，使用TreeSet集合。
 * 当需要保存枚举类的枚举值时，使用EnumSet集合。
 *
 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *
 * HashSet
 * 特点，不能保证元素的顺序，并且和加入顺序可能不同
 *
 * HashSet不同步，多线程访问同一个HashSet 需通过代码保证其同步
 * 集合元素值可以是null
 *
 * HashSet判断集合元素是否相同
 * 1. equals 返回 false   hashcode 不同
 *      HashSet将两个元素存到不同位置
 * 2. equals 返回 true  hashcode 不同
 *      HashSet将两个元素存到不同位置
 * 3. equals 返回 false  hashcode 相同
 *      HashSet将两个元素存到相同位置，以链表结构进行保存
 * 4. equals 返回 true   hashcode 相同
 *      HashSet判定两个元素相同，不予添加
 *
 *  Hash是典型的 用空间换取时间的数据结构
 *  加快查找速度
 *  通过计算Hashcode 值进行查找，使查找的时间复杂度达到 O(1)
 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *
 *  TreeSet 是SortedSet接口的实现，
 *  特点，保证数据有序
 *  通过集合中元素属性进行排序，
 *  支持 自然排序和定制排序
 *
 *  自然排序，调用Comparable接口中的compareTo(Object obj) 接口
 *
 *  定制排序,通过实现Comparator接口中的compare方法
 *  Comparator<People> comparator = new Comparator<People>() {
 *
 *             public int compare(People o1, People o2) {
 *                 //年龄越小的排在越后面
 *                 if(o1.age<o2.age){
 *                     return 1;
 *                 }else if(o1.age>o2.age){
 *                     return -1;
 *                 }else{
 *                     return 0;
 *                 }
 *
 *             }
 *         };
 *  TreeSet<People> set = new TreeSet<People>(comparator);
 *  调用带参构造器，传入comparator对象
 *
 *
 *  TreeSet 加入可变对象，并修改其值，导致大小顺序发生了变化，
 *  TreeSet不会再对其顺序做出改变
 *  所以 使用 TreeSet 时， 不要修改放入TreeSet集合中元素的关键实例变量
 *
 *  TreeSet非线程安全
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *
 *  有关线程/非线程安全
 *  synchronized  #同步
 *
 *  vector .add 声明中有 synchronized  保证其同步机制
 *  而 HashSet TreeSet 都没有该声明
 *  要保证多线程同步性, 采用如下方法
 *  synchronized (hashSet) {
 *     hashSet.add("233");
 * }
 */
public class JavaSet {
    public static void main(String []args){


        //HashSet   LinkedHashSet  TreeSet  对比存储顺序
        HashSet<Integer> hashSet = new HashSet<>();
        LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<>();
        TreeSet<Integer> treeSet = new TreeSet<>();
        Vector<Integer> vector = new Vector<>();
        int random;
        System.out.println("create 10 rand num between 1 to 100 :");
        for(int i=1;i <=10 ;i++){
            random = (int) (Math.random()*100);
            hashSet.add(random);
            linkedHashSet.add(random);
            treeSet.add(random);
            //vector.add(random);
            System.out.print(random + " ");
        }
        System.out.println("\nHashSet :");
        hashSet.forEach(integer -> {System.out.print(integer + " ");});

        System.out.println("\nLinkedHashSet :" );
        linkedHashSet.forEach(integer -> {System.out.print(integer + " ");});

        System.out.println("\nTreeSet :" );
        treeSet.forEach(integer -> {System.out.print(integer + " ");});



    }
}

//参考 https://www.jianshu.com/p/9081017a2d67
