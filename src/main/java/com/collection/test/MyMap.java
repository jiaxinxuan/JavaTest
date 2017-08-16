package com.collection.test;

public class MyMap <K,V>{
	
	final static int DEFAULT_SIZE=16;//默认数组大小
	private int size;//初始化数组大小
	private Entry<K,V>[] table;
	
	@SuppressWarnings("unchecked")
	/**
	 * 创建对象的时候,初始化table的大小
	 */
	public MyMap(){
		this.table=new Entry[DEFAULT_SIZE];
		this.size=DEFAULT_SIZE;
	}
	
	/**
	 * 获取数组长度
	 * @return
	 */
	public int getSize() {
		return this.size;
	 }
	
	/**
	 * 求当前元素应该在table的位置
	 * @param h
	 * @param length 当前的表的大小
	 * @return
	 */
	static int indexFor(int h, int length) { 
		return h % (length - 1); 
	 } 
	
	
	/**
	 * 获取元素
	 * @param key
	 * @return
	 */
	public V get(Object key) { 
		if (key == null)
			return null;
		int hash = key.hashCode(); // key的哈希值,依据此值判断元素内容是否重复,hashcode不同,equals结果一定不同.
		int index = indexFor(hash, table.length);// 求key在数组中的下标
		/**
		 * 此处在根据计算得到的index-即table的下标.当table[index]所在的链表中有多个值的时候,一一遍历
		 */
		for (Entry<K, V> e = table[index]; e != null; e = e.next) { 
				Object k = e.key; 
				if (e.key.hashCode() == hash && (k == key || key.equals(k))) 
					return e.value; 
		 } 
		return null; 
	 } 
	
	
	/**
	 * 添加元素,此处和get的实现差不多,要判断是否重复
	 * @param key
	 * @param value
	 * @return
	 */
	public V put(K key, V value) { 
		if (key == null) 
				return null; 
		int hash = key.hashCode(); 
		int index = indexFor(hash, table.length); //此处计算当前元素的数组下标
		// 如果添加的key已经存在，那么只需要修改value值即可
		for (Entry<K, V> e = table[index]; e != null; e = e.next) { 
				Object k = e.key; 
				if (e.key.hashCode() == hash && (k == key || key.equals(k))) { 
						V oldValue =e.value; 
						e.value = value; 
						return oldValue;
				}
		} 
		/**
		 * 新生成一个node对象存储在table对象的位置
		 */
		Entry<K, V> e = table[index];
		table[index] = new Entry<K, V>(hash,key, value, e);
		return null;
	 }
	
	public static void main(String[] args) { 
		 MyMap<String, Integer> map = new MyMap<String, Integer>(); 
		 map.put("a", 90); 
		 map.put("b", 95);
		 map.put("c", 85); 
		 System.out.println(map.get("a")); 
		 System.out.println(map.get("b")); 
		 System.out.println(map.get("c")); 
		 System.out.println(map.get(null));
		 }
}
