import java.util.PriorityQueue;

public class PriorityQueueTest {

    /*
    构造器
    PriorityQueue()                                      默认容量为11
    PriorityQueue(int initialCapacity)                  指定容量

    PriorityQueue(Comparator<? super E> comparator)     自定义比较器
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b-a);

    PriorityQueue(Collection<? extends E> c)           用已有集合构造一个优先队列
     */



    /*
    常用方法
    boolean offer(E e)	向队列添加元素（推荐使用）
    boolean add(E e)	同 offer，但超出容量可能抛异常

    E peek()	查看队头元素，不删除，若为空返回 null

    E poll()	获取并移除队头元素，若为空返回 null
    E remove()	获取并移除队头元素，队列为空抛 NoSuchElementException
    boolean remove(Object o)	删除指定元素，若存在返回 true

    int size()	返回队列元素个数
    boolean isEmpty()	判断队列是否为空
    void clear()	清空队列

    boolean contains(元素)

    Object[] toArray()	转换为数组
     */









}
