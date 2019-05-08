package CollectionLearning;

import java.util.*;

/**
 * Java集合类
 * map 和set的使用
 *
 * Map的使用 key-value
 * map直接遍历
 *
 * 使用Iterator遍历Collection中的元素
 * containsKey
 * containsValue
 *
 * HashSet和 LinkedHashSet的区别
 */
public class JavaCollection {
    public static void main(String[] args){
        //创建集合，添加元素
        int month[] = {31,28,31,30,31};
        String strmonth[] = {"Jan","Feb","Mar","Apr","May"};
        /**
         * HashSet 和LinkedHashSet 区别，
         * HashSet 按照 hashcode的值 进行存储数据
         * LinkedHashSet  保存了记录的插入顺序
         *
         *  同理适用于 HashMap
         */

        Map<String, Integer> map = new LinkedHashMap<>();
        //Map<String, Integer> map = new HashMap<>();   //使用HashMap 时的输出顺序为升序

        Collection<Month> setint = new HashSet<>();
        Collection<String> setString = new HashSet<>();

        for(int i=0;i<month.length;i++){
            Month smonth = new Month(month[i]);
            setint.add(smonth);
            setString.add(strmonth[i]);
            map.put(strmonth[i],month[i]);
        }

        //使用foreach 遍历map
        System.out.println("get all key and value");
        map.forEach((k,v)->{System.out.println(k + " " + v);});


        /**
         *  collection中 没有基本类型
         *  int 类型需要封箱成 Integer类型
         *  containsValue( int ) 返回 为false,
         *  containsValue( Integer ) 返回 为false,
         */
        System.out.println("HashMap.containsKey and containsValue ");
        System.out.println(map.containsKey("Jan"));   //key-value
        System.out.println(map.containsKey("31"));
        System.out.println(map.containsValue("Jan"));
        System.out.println(map.containsValue(31));
        System.out.println(map.containsValue(new Integer(31)));

        System.out.println("get value by map.key");
        Set<String> setmonth = map.keySet();  //获取key 集合
        for(String key :setmonth)
            System.out.println( key + " " + map.get(key));  //通过key 得到value 值


        //获取days集合的迭代器
        //因为通过hashcode的值 进行存储，所以取出数据时，为hashcode的存储顺序
        System.out.println("use Iterator get all months(int) and its hashcode");

        Iterator<Month> iterator = setint.iterator();
        while(iterator.hasNext()){//判断是否有下一个元素
            Month next = iterator.next();//取出该元素
            System.out.println(next.days+" hashcode " + next.hashCode() );
        }

        System.out.println("use Iterator get all months(string) and its hashcode");

        Iterator<String> iteratorstring = setString.iterator();
        while(iteratorstring.hasNext()){
            String stringnext = iteratorstring.next();
            System.out.println(stringnext + " hashcode " + stringnext.hashCode());
        }
        System.out.println(map);

    }

}

class Month{
    int days;
    Month(int days){
        this.days = days;
    }
}



