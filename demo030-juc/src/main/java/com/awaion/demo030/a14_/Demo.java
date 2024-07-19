package com.awaion.demo030.a14_;

public class Demo {
    // ArrayBlockingQueue 先进先出阻塞队列,常用于生产者-消费者模式,如果生产速度远远大于消费速度,则会导致队列填满,大量生产线程被阻塞
    // LinkedBlockingQueue
    // LinkedTransferQueue Since:1.7
    // PriorityBlockingQueue
    // SynchronousQueue 容量为0,不存储任何元素,生产者将数据放入SynchronousQueue,消费者线程会等待数据的到来并消费它
    // ConcurrentHashMap 线程安全KV映射对象,1.7前使用分段锁(Segment)机制,1.8使用链表(Node)+红黑树(TreeNode)(链表长度为8时转变)
    // if (binCount >= TREEIFY_THRESHOLD) treeifyBin(tab, i);
    // ConcurrentLinkedDeque Since:1.7 线程安全的,无界的,基于链表的双端队列,适合多生产,多消费场景(双端生产消费)
    // LinkedBlockingDeque Since:1.6
    // ConcurrentLinkedQueue 无界线程先进先出安全队列
    // ConcurrentSkipListMap Since:1.6 通过维护多个指向其他节点的链接,实现快速查找,插入和删除操作,适用于需要在多线程环境下保持排序和数据一致性的场景
    // ConcurrentSkipListSet Since:1.6 对 ConcurrentSkipListMap 的一个简单封装,适用于需要安自然顺序或自定义顺序存储元素的集合
    // CopyOnWriteArrayList 核心思想是写时复制,保证了读操作不会受到写操作的阻塞,适用于线程安全ArrayList
    // CopyOnWriteArraySet 核心思想是写时复制,保证了读操作不会受到写操作的阻塞,适用于线程安全HashSet
    // CountDownLatch 它允许一个或多个线程等待其他线程完成操作,await()
    // CountedCompleter Since:1.8 主要用于处理那些与数量有关的任务,特别是当任务可以被分解为多个子任务,并且这些子任务的完成数量会影响整个任务的完成状态时
    // CyclicBarrier 实现一组线程等待至某个状态(屏障点)之后再全部同时执行,适合需要对各子线程的执行结果做聚合计算的场景
    // DelayQueue 没有边界的BlockingQueue实现,用于实现定时任务调度系统,任务在特定的时间点被执行,缓存系统中的过期机制,限制某一操作在指定时间段内的执行频率
    // Exchanger 生产者-消费者之间使用Exchanger进行数据交换,遗传算法交配,数据校对
    // Executors 线程池创建工具类
    // Flow Since:9 Publisher,Subscriber,Subscription,Processor 响应式编程异步数据流操作,发布-订阅模型
    // ForkJoinPool Since:1.7 解决那些可以通过分治策略(Divide-and-Conquer)来并行处理的问题
    // ForkJoinTask Since:1.7 Fork/Join框架的核心组件
    // RecursiveAction ForkJoinTask 实现
    // Helpers 辅助工具类,封装了一些常用的功能方法,转换成String
    // Phaser Since:1.7 多个线程分阶段共同完成任务的同步问题
    // ScheduledThreadPoolExecutor 定时调度线程池
    // Semaphore 信号量 通过一个计数器来限制对某个资源的并发访问数量
    // SubmissionPublisher Since:9 用于实现响应式编程中的发布者(Publisher)角色
    // ThreadLocalRandom Since:1.7 Math.random() 的并发升级类,它解决了在多线程环境下使用 java.util.Random 产生的实例时可能遇到的线程安全问题
    // TimeUnit 用于表示时间单位并提供跨单位转换以及在这些单位中执行计时和延迟操作的实用方法
}
