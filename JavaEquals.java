import java.util.HashMap;

/**
 * 重写equals时必须重写 hashcode()
 * hashCode 方法的常规协定，
 * 该协定声明相等对象必须具有相等的哈希码
 */
class People{
    private String name;
    private int age;

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