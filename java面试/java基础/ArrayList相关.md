**ArrayList扩容**  
- ArrayList() 会使用长度为零的数组
- ArrayList(int initialCapacity) 会使用指定容量的数组
- public ArrayList(Collection<? extends E> c) 会使用 c 的大小作为数组容量
- add(Object o) 首次扩容为 10，再次扩容为上次容量的 1.5 倍
- addAll(Collection c) 没有元素时，扩容为 Math.max(10, 实际元素个数)，有元素时为 Math.max(原容量 1.5 倍, 实际元素个数)

**Fail-Fast 与 Fail-Safe**
- ArrayList 是 fail-fast 的典型代表，遍历的同时不能修改，尽快失败
- CopyOnWriteArrayList 是 fail-safe 的典型代表，遍历的同时可以修改，原理是读写分离
  - 在底层遍历和修改操作的是两个集合(遍历的是旧集合,修改的是新集合)在遍历完成之后再打印该集合,发现集合已修改

**ArraysList和LinkedList的区别**  
LinkedList
- 基于双向链表，无需连续内存
- 随机访问慢（要沿着链表遍历）
- 头尾插入删除性能高
- 占用内存多

ArrayList
- 基于数组，需要连续内存
- 随机访问快（指根据下标访问）
- 尾部插入、删除性能可以，其它部分插入、删除都会移动数据，因此性能会低
- 可以利用 cpu 缓存，局部性原理