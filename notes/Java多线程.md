# volatile

* 保证不同线程对该变量进行操作时的可见性，即一个线程对变量进行修改，修改后的值对其他线程是立即可见的（实现可见性）
  volatile的属性修改时会修改主存，并将工作内存中的对应副本全部无效，需要时再次去主内存获取

  ![这里写图片描述](https://img-blog.csdn.net/20170411090216333?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvaXRfZHg=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

* 禁止进行指令重排序（实现有序性），volatile操作前不可以到后，后不可以到前

* volatile 只能保证对单次读/写的原子性。i++ 这种操作不能保证**原子性**

## **volatile的实现原理**：

1. 可见性

   CPU为了提高处理速度，会将内存数据读取到缓存处理，但写回时间无法保证。而volatile关键字修饰的属性被操作时会向处理器先发一个Lock前缀指令，确保属性的更新被写回内存。

   另一边，各处理器（线程）之间为了保持缓存数据一致，会通过嗅探检验当前数据是否过期，过期则将数据无效化，需要使用时再去内存中获取

2. 有序性

   Lock前缀指令相当于一个内存栅栏，他确保指令重排序时，volatile之前的指令不会到后面，后面的指令不会到前面

# synchronized

## 锁升级

jvm锁有无锁、偏向锁、轻量级锁、重量级锁

**偏向锁**会在对象头中MarkWord内标记持有锁的线程，同线程无需上锁，不同线程时会查看标记线程是否存在，不存在或存在但不持有锁对象标记为无锁；，存在且持有锁对象则暂停标记线程，撤销偏向锁，升级为轻量级锁。

**轻量级锁**通过CAS竞争MarkWord将其指向Lock  Record，更新成功则获取锁成功，失败则自旋，自旋一定次数（默认是10次）就升级为重量级锁。
![img](https://user-gold-cdn.xitu.io/2020/5/17/17222ba7dc313cbc?imageView2/0/w/1280/h/960/format/webp/ignore-error/1)

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

## 用synchronized还是Lock呢？

**两者的区别：**

* synchronized是关键字，通过jvm实现；Lock是接口，通过JDK实现，有更丰富的API
* synchronized是非公平锁，Lock可以主动设置是否公平
* synchronized自动释放锁，Lock可以必须主动释放
* synchronized不可以中断，Lock可以中断也可以不中断
* synchronized无法知道是否获取锁，Lock可以
* synchronized可以锁住方法和代码块，Lock只能锁住代码块
* Lock可以使用读锁提高多线程读效率
* synchronized锁一旦升级无法降级

# ThreadLocal

ThreadLocal的主要作用是做数据隔离，变量的数据相对其他线程是隔离开的，在多线程情况下可以防止其他线程修改数据。

## ThreadLocal有什么用，使用场景是怎样的？

Spring中事务主要是依靠AOP和ThreadLocal实现的，每个线程自己的连接是吃用ThreadLocal保存的。

Session、Cookie的管理也可能需要使用使用ThreadLocal。

## ThreadLocal底层实现

每个线程Thread都维护了一个ThreadLocalMap。创建ThreadLocal其实都是添加到ThreadLocals里

```java
/* ThreadLocal values pertaining to this thread. This map is maintained
 * by the ThreadLocal class. */
ThreadLocal.ThreadLocalMap threadLocals = null;

```

```java
private T setInitialValue() {
    T value = initialValue();
    Thread t = Thread.currentThread();
    ThreadLocalMap map = getMap(t);
    if (map != null)
        map.set(this, value);
    else
        createMap(t, value);
    return value;
}
```

**ThreadLocalMap底层实现**

ThreadLocalMap内部的Entry<ThreadLocal,Object>继承了WeakReference，然后通过Entry数组存放ThreadLocal。使用线性探测法解决hash冲突。

## ThreadLocal的实例存放在哪里

栈内存属于每一个独立的线程，每个线程都会有一个栈帧，栈帧内数据只对该线程可见。所有线程共享的数据则存储在堆内存中。

但是ThreadLocal并不是通过jvm实现的，他依然是被实例化的类所持有，只是通过一些技巧将可见性修改成了线程可见。

## 如何实现共享线程的ThreadLocal

使用InheritableThreadLocal可以实现多线程共享的ThreadLocal，在主线程创建的InheritableThreadLocal可以在子线程中被使用。

实现方式就是在Thread中还有一个InheritableThreadLocals，在创建子线程的时候会判断是不是InheritableThreadLocal，如果是且父线程InheritableThreadLocals不为空，则将其复制过来。

## ThreadLocal导致内存泄漏 （有疑问）

ThreadLocal保存时会将自己作为key保存在ThreadLocalMap中，但使用的是弱引用所以在key会被回收，但是value使用的是强引用所以无法被回收，并且也无法被使用因为key已经被回收，所以造成了内存泄漏。在使用完后调用remove就可以解决。

并且key不设置成弱引用的话回造成和value一样的内存泄漏问题。

# 锁

## 公平锁与非公平锁

ReentrantLock实现了公平锁与非公平锁，默认非公平，构造方法传true则公平

# 创建线程的几种方式

## 1.继承Thread类创建线程类

1. 创建子类继承Thread类，并重写run方法
2. 创建子类实例化对象
3. 调用对象start方法

```java
public class Res1 {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread thread = new MyThread();
            thread.start();
        }
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("这是我创建的线程:" + getName());
        }
    }
}
```

## 2.通过Runnable接口创建线程类

1. 定义Runnable的实现了并实现run方法
2. 实例化实现类并作为Thread的构造函数的入参实例化Thread
3. 调用start方法

```java
public class Res2 {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Runnable myRunnable = new MyRunnable();
            Thread thread = new Thread(myRunnable);
            thread.start();
        }
    }

    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("这是我创建的线程:"+Thread.currentThread().getName());
        }
    }
}
```

## 3.通过Callable和Future创建线程

1. 创建Callable接口的实现类，实现call方法，有返回值
2. 实例化Callable对象，使用FutureTask包装Callable对象，该FutureTask封装了Callable对象的返回值
3. 使用FutureTask作为入参创建新线程
4. 调用FutureTask的get方法获得子线程返回值

```java
public class Res3 {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Callable myCallable = new MyCallable();
            FutureTask futureTask = new FutureTask(myCallable);
            Thread thread = new Thread(futureTask);
            thread.start();
//            try {
//                System.out.println(futureTask.get());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
        }
    }

    static class MyCallable implements Callable{

        @Override
        public String call() throws Exception {
            System.out.println("这是我创建的线程:"+Thread.currentThread().getName());
            return Thread.currentThread().getName();
        }
    }
}
```

# 线程池

## 线程池的优势

1. 降低系统资源消耗，通过重用已存在的线程，降低线程创建和销毁造成的消耗；
2. 提高系统响应速度，当有任务到达时，通过复用已存在的线程，无需等待新线程的创建便能立即执行；
3. 方便线程并发数的管控。因为线程若是无限制的创建，可能会导致内存占用过多而产生OOM，并且会造成cpu过度切换（cpu切换线程是有时间成本的（需要保持当前执行线程的现场，并恢复要执行线程的现场））。
4. 提供更强大的功能，延时定时线程池。

## 线程池的主要参数

```java
public ThreadPoolExecutor(int corePoolSize,
                          int maximumPoolSize,
                          long keepAliveTime,
                          TimeUnit unit,
                          BlockingQueue<Runnable> workQueue,
                          ThreadFactory threadFactory,
                          RejectedExecutionHandler handler); 
```

1. corePoolSize（线程池基本大小）：在线程池中线程数未达到线程池基本大小，即使当前有空闲线程也会创建新线程，直到大于或等于线程池基本大小。可以调用prestartCoreThread() 或 prestartAllCoreThreads() 方法来提前启动线程池中的基本线程。
2. maximumPoolSize（线程池最大大小）：控制可以创建的最大线程数
3. keepAliveTime（线程存活保持时间）：当线程池中线程数大于核心线程数时，线程空闲时间如果超过线程存活保持时间，则会被销毁
4. workQueue（任务队列）：用于传输和保持等待执行任务的阻塞队列
5. threadFactory（线程工厂）：用于创建新线程。threadFactory创建的线程也是采用new Thread()方式，threadFactory创建的线程名都具有统一的风格：pool-m-thread-n（m为线程池的编号，n为线程池内的线程编号）。
6. handler（线程饱和策略）：当线程池和队列都满了时，会执行此策略
7. unit：线程存活时间的计量单位

# 常见JUC

