# Stream

```java
System.out.println("使用 Java 8: ");
System.out.println("列表: " +strings);

count = strings.stream().filter(string->string.isEmpty()).count();
System.out.println("空字符串数量为: " + count);

count = strings.stream().filter(string -> string.length() == 3).count();
System.out.println("字符串长度为 3 的数量为: " + count);

filtered = strings.stream().filter(string ->!string.isEmpty()).collect(Collectors.toList());
System.out.println("筛选后的列表: " + filtered);

mergedString = strings.stream().filter(string ->!string.isEmpty()).collect(Collectors.joining(", "));
System.out.println("合并字符串: " + mergedString);

squaresList = numbers.stream().map( i ->i*i).distinct().collect(Collectors.toList());
System.out.println("Squares List: " + squaresList);
System.out.println("列表: " +integers);

IntSummaryStatistics stats = integers.stream().mapToInt((x) ->x).summaryStatistics();

System.out.println("列表中最大的数 : " + stats.getMax());
System.out.println("列表中最小的数 : " + stats.getMin());
System.out.println("所有数之和 : " + stats.getSum());
System.out.println("平均数 : " + stats.getAverage());
System.out.println("随机数: ");

random.ints().limit(10).sorted().forEach(System.out::println);

// 并行处理
count = strings.parallelStream().filter(string -> string.isEmpty()).count();
System.out.println("空字符串的数量为: " + count);
```

# Lambda表达式

# 方法引用

使用   ::

# 函数式接口

函数式接口(Functional Interface)就是一个有且仅有一个抽象方法，但是可以有多个非抽象方法的接口。

# 默认方法

Java 8 新增了接口的默认方法。

简单说，默认方法就是接口可以有实现方法，而且不需要实现类去实现其方法。

关键字default

```java
public interface Vehicle {
    default void print(){
        System.out.println("我是一辆车!");
    }
}
```

静态默认方法

```java
public interface Vehicle {
    default void print(){
        System.out.println("我是一辆车!");
    }
    // 静态方法
    static void blowHorn(){
        System.out.println("按喇叭!!!");
    }
}
```

# Optional 类

可以存入空值的类，通过isPresent()判断是否为空

# Nashorn, JavaScript 引擎

Java和JavaScript互相支持执行对方的片段

# Java 8 日期时间 API

在旧版的 Java 中，日期时间 API 存在诸多问题，其中有：

- **非线程安全** − java.util.Date 是非线程安全的，所有的日期类都是可变的，这是Java日期类最大的问题之一。
- **设计很差** − Java的日期/时间类的定义并不一致，在java.util和java.sql的包中都有日期类，此外用于格式化和解析的类在java.text包中定义。java.util.Date同时包含日期和时间，而java.sql.Date仅包含日期，将其纳入java.sql包并不合理。另外这两个类都有相同的名字，这本身就是一个非常糟糕的设计。
- **时区处理麻烦** − 日期类并不提供国际化，没有时区支持，因此Java引入了java.util.Calendar和java.util.TimeZone类，但他们同样存在上述所有的问题。

新的java.time包涵盖了所有处理日期，时间，日期/时间，时区，时刻（instants），过程（during）与时钟（clock）的操作。

- **Local(本地)** − 简化了日期时间的处理，没有时区的问题。
- **Zoned(时区)** − 通过制定的时区处理日期时间。

# 支持Base64

