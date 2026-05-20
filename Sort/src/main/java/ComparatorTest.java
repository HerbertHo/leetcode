import java.util.Comparator;

public class ComparatorTest {

    /*
    比较器：
    返回负数 -> 第一个参数在前    比如默认的升序排序 ： list.sort((a,b) -> a-b)
    返回正数 -> 第二个参数在前    比如降序排序 ： list.sort((a,b) -> b-a)
    返回0   -> 无所谓
     */
}


class AgeComparator implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2) {
        return p1.age-p2.age;
    }
}


class Person{
    public String name;
    public int age;

    public Person(String name,int age){
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}





