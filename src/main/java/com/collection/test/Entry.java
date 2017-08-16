package com.collection.test;
import java.util.Map;
import java.util.Objects;
/**
 * 模拟实现map的节点node
 * @author Administrator
 *
 * @param <K>
 * @param <V>
 */
public class Entry<K, V> implements Map.Entry<K,V> {
/**
*节点对象的属性，创建对象的时候，初始化
*/
       final int hash;//当前结点的hash值，留存以便和新的节点作比较
       final K key;
       V value;
       Entry<K,V> next;//此处表示下一个节点的引用
/**
构造方法
*/
       Entry(int hash, K key, V value, Entry<K,V> next) {
           this.hash = hash;
           this.key = key;
           this.value = value;
           this.next = next;
           
       }
    public K getKey() {
        return this.key;
    }
    public V getValue() {
        return this.value;
    }
    /**
     * 设置新的值时返回旧的值
     */
    public V setValue(V newValue) {
           V oldValue = value;
           value = newValue;
           return oldValue;
    }
    /**
     * 重写equals方法时,一定不要忘记重写hashcode方法,要保持hashcode方法和equals方法在逻辑上的一致性
     */
    public final boolean equals(Object o) {
          if (o == this)
              return true;
          if (o instanceof Map.Entry) {
              Map.Entry<?,?> e = (Map.Entry<?,?>)o;
              if (Objects.equals(key, e.getKey()) &&
                  Objects.equals(value, e.getValue()))
                  return true;
          }
          return false;
      }
      public final int hashCode() { 
          return (key==null ? 0 : key.hashCode()) ^ (value==null ? 0 : value.hashCode()); 
           }
      public final String toString() { 
      return getKey() + "=" + getValue(); 
       } 
}