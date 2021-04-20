# **HashMap**

------

HashMap是由数组和链表组成，传入key后通过hsah算法得出index数组索引，再将kv键值对以node节点的形式插入链表

节点保存key，value，hsah，下个节点

**链表插入节点在java8之前是头插法，java8之后使用尾插法**

起初使用头插法的原因是因为作者认为后插入的会先被使用，所以头插法可以优化查找性能

## **HashMap的扩容机制：**

当当前长度大于容量*负载因子时，进行扩容。

扩容分为两步：

1. 创建新的Node数组，长度是之前的两倍
2. 遍历原数组，将所有的节点重新hash的新数组

重新hash而不是直接复制的原因是hash算法与数组长度有关，长度翻倍后通过原来的hash算法计算出的索引结果不一样

使用头插法会改变链表的原有顺序，多线程情况下在扩容时可能导致出现循环链表，而尾插法不会改变原有顺序所以不会出现这种情况

虽然java8后解决了死循环的问题，但是putget方法并没有加同步锁，所以仍然无法保证线程安全

**HsahMap的初始长度是16，建议hashmap的长度是2的幂**，是为了实现HashMap的均匀分布

因为这样可以方便做位运算，在通过key计算index时，首先是hash，然后是进行位运算，公式是：

```
index = HashCode（Key） & （Length- 1）
```

因为2的幂-1二进制全为1，所以位运算后的index等于hashcode的后几位，只要hashcode均匀，hash算法的结果就是均匀的



## 为什么重写equals时需要重写hashcode（有疑问）[^1]

在未重写情况下，equals比较的时两个对象的内存地址。对于值对象==比较两者的值，引用对象比较两个对象的地址

## 如何保证线程安全

* 使用Collections.synchronizedMap(Map)创建线程安全的Map集合
* HashTable
* ConcurrentHashMap

HashTable直接在在方法上上锁简单粗暴，但是一次只允许一个线程访问，并发度很低

synchronizedMap有一个Map对象和一个互斥锁对象，构造方式有两种。在操作map是会对方法上锁，判断互斥锁的状态。

## 为什么链表长度超过8或者数组长度超过64时就会转换成红黑

## 树

根据泊松分布，在负载因子在0.75的情况下，链表长度为8的可能性极小，且链表长度等于8时，红黑树的平均查找长度时3，链表的平均查找长度时4，而取6和8的原因是因为在中间留一个7可以作为过渡，避免增加删除造成的频繁转换。



# **ConcurrentHashMap**

---

## ConcurrentHashMap底层数据结构（java1.7版本，1.8稍有不同）

![img](https://img-blog.csdnimg.cn/20190510161549544.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2NjI1NzU3,size_16,color_FFFFFF,t_70)

是由Segment数组和HashEntry组成仍然是数组加链表（数组+数组+链表）

Segment数组是ConcurrentHashMap的内部类，有HashEntry数组，count，modCount，大小和负载因子这些参数

HashEntry和HashMap的节点类似，区别在于使用了violate去修饰value和next

## volatile特性[^2]

* 保证不同线程对该变量进行操作时的可见性，即一个线程对变量进行修改，修改后的值对其他线程是立即可见的（实现可见性）
  volatile的属性修改时会修改主存，并将工作内存中的对应副本全部无效，需要时再次去主内存获取

  ![这里写图片描述](https://img-blog.csdn.net/20170411090216333?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvaXRfZHg=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

* 禁止进行指令重排序（实现有序性），volatile操作前不可以到后，后不可以到前

* volatile 只能保证对单次读/写的原子性。i++ 这种操作不能保证**原子性**

**volatile的实现原理**：

1. 可见性

   CPU为了提高处理速度，会将内存数据读取到缓存处理，但写回时间无法保证。而volatile关键字修饰的属性被操作时回想处理器先发一个Lock前缀指令，确保属性的更新被写回内存。

   另一边，各处理器（线程）之间为了保持缓存数据一致，会通过嗅探检验当前数据是否过期，过期则将数据无效化，需要使用时再去内存中获取

2. 有序性

   Lock前缀指令相当于一个内存栅栏，他确保指令重排序时，volatile之前的指令不会到后面，后面的指令不会到前面

## ConcurrentHashMap高并发原因

从原理上，ConcurrentHashMap采用了分段锁，其中Segment继承了ReentrantLock，不会对所有putget操作做同步处理，理论上ConcurrentHashMap支持CurrencyLevel（Segment数组大小）的线程并发。

就是说如果容量是16，则最多支持16个线程并发且保证线程安全。

put过程每个Segment支持并发访问，get方法直接访问即可，因为volatile具有可见性每次获取的都是新值

但是1.7依然使用数组加链表，查询时需要遍历数组，效率比较低

## ConcurrentHashMap的put，get方法是怎样实现的(有疑问)[^ 4]

## ConcurrentHashMap结构（jdk1.8）

![img](https://img-blog.csdnimg.cn/20190516134218361.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2NjI1NzU3,size_16,color_FFFFFF,t_70)

抛弃原有的Segment分段锁，使用CAS+synchronized方式实现线程安全，使用volatile修饰 值和next，保证了可见性。并且引入了红黑树和链表的互相转换，提升了查询性能。

结构与HashMap基本一致，原理上在操作数据时对每个index的第一个节点使用synchronized上锁而不是直接对整个hashMap上锁，在并发性能上有显著提升，理论上最大支持Node数组长度的并发。

## JDK1.8为什么使用内置锁synchronized来代替重入锁ReentrantLock？

* 因为粒度降低了，在相对而言的低粒度加锁方式，synchronized并不比ReentrantLock差，在粗粒度加锁中ReentrantLock可能通过Condition来控制各个低粒度的边界，更加的灵活，而在低粒度中，Condition的优势就没有了
* JVM的开发团队从来都没有放弃synchronized，而且基于JVM的synchronized优化空间更大，使用内嵌的关键字比使用API更加自然
* 在大量的数据操作下，对于JVM的内存压力，基于API的ReentrantLock会开销更多的内存，虽然不是瓶颈，但是也是一个选择依据

## jdk1.8中ConcurrentHashMap的put操作

put方法首先判断数组是否为空，如果为空则进行初始化（由CAS保证初始化线程安全），然后判断key进行hash和位运算后得出的index节点是否为空，如果为空就新建节点使用CAS插入链表，如果第一个节点的hash为-1则说明有现成正在进行扩容本线程会帮助数据迁移，如果已存在就使用synchronized对第一个节点上锁然后执行更新

迁移数据机制：

原数组长度为n，那么拆分成n个任务，多个线程一起执行就可以实现帮助迁移，而作者使用了步长stride，为了分配哪个线程执行那个任务，使用了transferIndex，最先存储原数组最后的位置，然后第一个线程迁移stride个数据，transferIndex指向新的位置。transfer()方法迁移数据，依次只能迁移stride个长度的数据，所以transfer()一次不能迁移完所有数据，需要由tryPresize()方法的控制，tryPresize()方法中当transferIndex <= 0时，表示迁移完毕，其中while就会跳出循环，整个数据的迁移工作也就完成了。

## CAS和自旋是什么

CAS（compare and swap），通过乐观锁的方式实现线程安全，在更新数据前后会获取和校验期望值，如果两者不一致说明数据已经被其他线程更新，本次更新失败，但是允许继续重试直至成功。

自旋是一种锁优化机制，在失败时自动重试。

## ABA是什么

v本来为A，线程1将A取出准备更新，此时线程2更新数据为B，又更新数据为A，此时线程1更新数据，CAS操作成功，单线程并不安全。通过版本号就可以解决这个问题（**AtomicStampedReference**）；

## synchronized锁升级

jvm锁有无锁、偏向锁、轻量级锁、重量级锁

**偏向锁**会在对象头中MarkWord内标记持有锁的线程，同线程无需上锁，不同线程时会查看标记线程是否存在，不存在或存在但不持有锁对象标记为无锁；，存在且持有锁对象则暂停标记线程，撤销偏向锁，升级为轻量级锁。

为避免重复自旋，轻量级锁一旦升级无法降级，轻量级也无法降级到偏向锁

| 锁状态   | 优点                                                   | 缺点                                             | 适用场景                                                   |
| -------- | ------------------------------------------------------ | ------------------------------------------------ | ---------------------------------------------------------- |
| 偏向锁   | 加锁无需太多额外消耗，与不加锁效率基本差不多           | 在多个线程竞争大场景下会导致撤销锁这样的额外开销 | 基本没有线程竞争的同步场景                                 |
| 轻量级锁 | 竞争的线程无需阻塞，使用自选，减少了切换状态带来的开销 | 如果长时间无法获取锁，自旋会导致大量的CPU开销    | 少量线程竞争锁，且线程持有锁的时间不长，追求响应速度的场景 |
| 重量级锁 | 不使用自旋，不会因为CPU持续空转造成过多CPU开销         | 线程阻塞会导致响应时间较长                       | 很多线程同时竞争锁，线程持有锁时间较长，追求吞吐量的场景   |

**锁粗化**
按理来说，同步块的作用范围应该尽可能小，仅在共享数据的实际作用域中才进行同步，这样做的目的是为了使需要同步的操作数量尽可能缩小，缩短阻塞时间，如果存在锁竞争，那么等待锁的线程也能尽快拿到锁。 
但是加锁解锁也需要消耗资源，如果存在一系列的连续加锁解锁操作，可能会导致不必要的性能损耗。 
锁粗化就是将多个连续的加锁、解锁操作连接在一起，扩展成一个范围更大的锁，避免频繁的加锁解锁操作。

**锁消除**

Java虚拟机在JIT编译时(可以简单理解为当某段代码即将第一次被执行时进行编译，又称即时编译)，通过对运行上下文的扫描，经过逃逸分析，去除不可能存在共享资源竞争的锁，通过这种方式消除没有必要的锁，可以节省毫无意义的请求锁时间

## jdk1.8中ConcurrentHashMap的get操作

get方法首先会到获取头节点，为空返回null，然后会判断hash是否小于0，小于0说明正在扩容，则回去nextTable中查找，不然就会直接遍历链表查询。

# **HashTable**

---

HashTable相较HashMap是线程安全的，可在多线程情况下使用，但是在对数据进行操作时都会上锁，所以效率低下，线程并发度低。

## HashTable和HashMap的区别

* HashTable相较HashMap是线程安全的

* HashTable的KV都不可以是null，HashMap的KV都可以

* 实现方式不同HashTable继承了Dictionary类，HashMap继承了AbstractMap类

* 初始化容量不同，HashMap时16，HashTable是11

* 扩容机制不同，当当前容量大于总容量*负载因子时，HashMap翻倍，HashTable翻倍+1

* 迭代器不同，HashTable的时Enumerator不是fail-fast，HashMap的Iterator是fail-fast的。所以，当其他线程改变了HashMap 的结构，如：增加、删除元素，将会抛出ConcurrentModificationException 异常，而 Hashtable 则不会。

  **快速失败（fail-fast）**是java集合中的一种机制，在用迭代器遍历一个集合对象时，如果对集合内数据进行了修改（增删改），则会抛出Concurrent Modification Exception（并发修改异常）。迭代器在遍历时直接访问集合内容，并使用modCount变量，集合在遍历期间被修改就会改变modCount，迭代器遍历每个元素时都会校验modCount是否为expectedmodCount值，是的话就返回遍历；否则抛出异常，终止遍历。这里异常的抛出条件是检测到 modCount！=expectedmodCount 这个条件。如果集合发生变化时修改modCount值刚好又设置为了expectedmodCount值，则异常不会抛出。因此，不能依赖于这个异常是否抛出而进行并发操作的编程，这个异常只建议用于检测并发修改的bug。

## 为什么HashTable的KV都不可以是null，HashMap的KV都可以[^ 3]

因为HashTable在put（null）时会报空指针异常，而hashMap在key为null时会直接放入0号链表不需要hashcode

这是因为Hashtable使用的是**安全失败机制（fail-safe）**[^ 5]，这种机制会使你此次读到的数据不一定是最新的数据。

如果你使用null值，就会使得其无法判断对应的key是不存在还是为空，因为你无法再调用一次contain(key）来对key是否存在进行判断，ConcurrentHashMap同理。

**安全失败机制（fail-safe）**

采用安全失败机制的集合容器，在遍历时不是直接遍历集合，而是遍历拷贝，所以不会出现异常，但是数据也不是最新的

# ArrayList

---

ArrayList底层由数组实现，查询效率高，增删效率低，线程不安全。在不涉及增删的情况下，ArrayList在查询速度上较快，如果频繁增删，则LinkedList更为合适，需要线程安全的话则使用Vector。Vector就是把所有的方法通通加上synchronized（Collections.synchronizedList同理）

## ArrayList如何控制长度

ArrayList有无参构造和有参构造，无参构造在创建ArrayList时会先创建长度为0的空数组，在第一次add时将长度设置为10，有参构造则直接将长度设置为指定值。

当数组新增数据发现数组已经满了时会新建一个长度为length + length/2长度的数组，然后把数据复制过去，最后把数组原地址改为新地址。

jdk1.8及以后扩容使用位运算，效率更高，1.8之前时3/2+1 ，之后直接3/2

## ArrayList初始化1.7和1.8的区别

1.7之前初始化会调用this（10），1.7及以后会先初始化0，第一次add初始化为10

带参初始化虽然初始化了，但是size依然是0

## ArrayList如何增删

有指定index新增，也有直接新增，在新增之前会校验长度，不够则执行扩容。指定位置新增就是先将数组复制，index之前直接复制，index之后右移一个，再将值填入index

## ArrayList插入删除一定慢么？

取决于你删除的元素离数组末端有多远，ArrayList拿来作为堆栈来用还是挺合适的，push和pop操作完全不涉及数据移动操作。

## ArrayList不适合做队列，但是数组适合

比如ArrayBlockingQueue内部实现就是环形队列，他是一个定长队列，内部是用定长数组来实现的

## ArrayList和LinkedList遍历性能哪个比较好？

ArrayList遍历要比LinkedList快很多，因为ArrayList内存连续，CPU缓存会读取连续的内存片段，大大降低内存的开销





疑问

[^1]: 为什么重写equals时需要重写hashcode
[^2]: violate特性
[^3]: 为什么HashTable的KV都不可以是null，HashMap的KV都可以
[^4]: ConcurrentHashMap的put，get方法
[^5]:安全失败机制（fail-safe） ↩
[^6]:

