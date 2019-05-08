package CollectionLearning;

import java.util.HashMap;

/**
 * equals 是 Object类中的方法，
 * public boolean equals(Object obj) {
 *        return (this == obj);
 *    }
 * 如果不重写，是判断两个对象是否相同，等同于  == ，
 *
 * 重写equals时必须重写 hashcode()
 * hashCode 方法的常规协定，
 * 该协定声明相等对象必须具有相等的哈希码
 *
 * 如果两个对象相等，则hashcode一定也是相同的
 * 两个对象相等,对两个对象分别调用equals方法都返回true
 * 两个对象有相同的hashcode值，它们也不一定是相等的
 * 因此，equals 方法被覆盖过，则 hashCode 方法也必须被覆盖
 *
 * hashCode() 的默认行为是对堆上的对象产生独特值。如果没有重写 hashCode()，则该 class 的两个对象无论如何都不会相等（即使这两个对象指向相同的数据）
 */
class People{
    public int age;
    private String name;

    public People(String name,int age) {
        this.name = name;
        this.age = age;
    }


    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
   //     if (obj instanceof People) {  //不能使用instance 关键字，因为它不能判断是否为该类的子类
        if(obj != null && obj.getClass() == this.getClass()){   //使用getClass进行类型判断
            People obj1 = (People) obj;
            if (obj1.name == null || name == null) {
                return false;
            }
            return this.name.equals(obj1.name) && this.age == obj1.age;
        }
        return  false;
    }

    @Override
    public int hashCode(){

        return name.hashCode()*37+age;
    }
}

public class JavaEquals {

    public static void main(String[] args) {

        People p1 = new People("Jack", 12);
        System.out.println(p1.hashCode());
        System.out.println((new People("Jack",12)).hashCode());
        HashMap<People, Integer> hashMap = new HashMap<People, Integer>();
        hashMap.put(p1, 1);
        System.out.println(p1.equals(new People(null,12)));
        System.out.println((new People("Jack",12)).equals(p1));
        System.out.println(hashMap.get(new People("Jack", 12)));
    }
}