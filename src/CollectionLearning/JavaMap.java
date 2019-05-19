package CollectionLearning;

import java.util.*;

/**
 * HashMap 和 TreeMap
 * 一般场景尽可能多考虑使用HashMap，因为其为快速查询设计
 * 需要特定的排序时，考虑使用TreeMap
 * 仅仅需要插入的顺序时，考虑使用LinkedHashMap
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * HashMap 和 Hashtable 的区别
 *
 * Hashtable 线程安全， HashMap 非线程安全
 * Hashtable 不允许 null 作为key 或 value  （NullPointerException 异常
 * HashMap  允许 null 作为key  或 value
 *
 * key 判断的标准，与HashSet 类似，判断 equals 返回 true ,并且 hashCode值相等
 * value 判断的标注，只要equals 返回 true
 *
 * 注：HashMap 中 key 集合元素不能重复，value集合元素可以重复
 *
 * 不要修改hashMap 的key值
 * （修改key值会导致hashCode()和equals 的比较结果发生变化，无法访问到该key
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *
 * HashMap 通过拉链法 实现的哈希表（Node类型）
 * 哈希表， 由数组和链表组成
 * 通过hashCode 确定元素在数组中的位置，对于相同hashCode的值 使用链表存储
 * table , size , threshold , loadFactor
 *
 * table是Node[] 数组类型，
 * Node 是一个单向链表， key-value 键值对 都是存在 Node 数组中的
 * size 是HashMap 的大小， 记录保存的键值对的数量
 * threshold 是HashMap 的阈值， 用于判断是否需要调整HashMap 的容量
 * threshold = 容量*加载因子
 * 哈希表中的条目数超出了加载因子与容量的乘积时，要对该哈希表进行 resize操作
 *
 * capatity 哈希表的容量
 * loadFactor 加载因子
 *  loadFactor 默认为0.75
 *  （是时间和空间成本的一种折中方案，既能加快时间，又能保证较高的空间利用率
 *
 * 哈希表是 用空间换取时间的一种策略， 故需要保证其空间足够大
 * // 默认构造函数。
 * HashMap()
 *
 * // 指定“容量大小”的构造函数
 * HashMap(int capacity)
 *
 * // 指定“容量大小”和“加载因子”的构造函数
 * HashMap(int capacity, float loadFactor)
 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * LinkedHashMap 保留了元素的插入顺序   (用双向链表来维护key-value对的次序
 *
 * 链表存储使得 迭代访问Map里的全部元素时有较好的性能
 * LinkedHashMap=散列表+循环双向链表
 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *
 * TreeMap 是 SortedMap接口的实现类
 *
 * 通过红黑树实现  有序的 key-value 集合
 *
 * TreeMap 通过实现Comparable接口中的compareTo方法来存储
 * compareTo的返回结果为0 表示两个对象相等
 * 后put的对象 会把前面的覆盖掉
 *
 * TreeMap 判断Key 相同的标准， compareTo 返回值为 0
 * 判断 valie 相同的标准，equals 返回值为 true
 *
 * 如果使用自定义类作为TreeMap的key
 * 需 重写该类的equals()方法和compareTo()方法， 使其保持一致的返回结果
 * （下面例子，已在JavaCollection Month中加入了 compareTo 和 equals
 *
 * 参考
 * https://www.jianshu.com/p/0580eb808eea
 *  https://www.cnblogs.com/dreamroute/p/3867039.html
 */
public class JavaMap {
    public static void main(String[] args){
        HashMap map = new HashMap();
        map.put("Gaoooyh",336);
        map.put("Hiawons",58);
        map.put("Lcc's cat",354);


        /*
         * 下面为几种遍历方式，
         * 此外 在Collection中，还使用了 Map.keySet遍历key，通过get(key) 得到value
         */
        //用entrySet()获取HashMap的Set集合并用Iterator遍历
        System.out.println("go through key and value");
//        Set set = map.entrySet();
//        Iterator iterator = set.iterator();
        Iterator iterator = map.entrySet().iterator(); //等同于上面两行内容
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        //用keySet()获取 key 集合
        System.out.println("go through all the key");
        Iterator keyIterator = map.keySet().iterator();
        while (keyIterator.hasNext()){
            System.out.println(keyIterator.next());
        }

        //用values() 获取value 集合
        System.out.println("go through all the value");
        for(Object value:map.values()){
            System.out.print(value + "  ");
        }


        //TreeMap 的使用
        TreeMap<Month,String> treeMap = new TreeMap<>();
        treeMap.put(new Month(31),"Jan");
        treeMap.put(new Month(28),"Feb");
        treeMap.put(new Month(31),"Mar");
        treeMap.put(new Month(30),"Apr");

        //输出顺序由 Month 类中的 comepareTo 方法返回值决定
        System.out.println("\ngo through the treemap");
        Iterator treeIterator = treeMap.entrySet().iterator();
        while (treeIterator.hasNext()){
            System.out.println(treeIterator.next() );
        }





    }
}
