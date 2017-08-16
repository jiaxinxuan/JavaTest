package com.collection.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;
import java.util.WeakHashMap;


public class HashSetTest {

	private String name;
	private Integer age;
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Integer getAge() {
		return age;
	}


	public void setAge(Integer age) {
		this.age = age;
	}
   

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((age == null) ? 0 : age.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	public HashSetTest(Integer age) {
		super();
		this.age = age;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HashSetTest other = (HashSetTest) obj;
		if (age == null) {
			if (other.age != null)
				return false;
		} else if (!age.equals(other.age))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}


	public static void main(String[] args) {
		
		/**
		 * set接口测试
		 */
		Set<HashSetTest> hashSet=new HashSet<HashSetTest>();
		Set<HashSetTest> treeSet=new TreeSet<HashSetTest>();
		Set<HashSetTest> linkedHashSet=new LinkedHashSet<HashSetTest>();
		/**
		 * hashset是按照对象的hashcode和equals方法来排序
		 */
		HashSetTest user1=new HashSetTest(1);
		HashSetTest user2=new HashSetTest(2);
		HashSetTest user3=new HashSetTest(3);
		HashSetTest user4=new HashSetTest(4);
		hashSet.add(user1);
		hashSet.add(user2);
		hashSet.add(user3);
		hashSet.add(user4);
		for (HashSetTest user : hashSet) {
			System.out.println(user.getAge());
		}
		/**
		 * list接口测试
		 */
		List arrayList=new ArrayList<HashSetTest>();
		List linkedList=new LinkedList<HashSetTest>();
		//线程安全
		Vector vector=new Vector<HashSetTest>();
		Stack stack=new Stack<HashSetTest>();
		/**
		 * map接口测试
		 */
		//table中的结构为哈希表加单向链表,元素无序
		Map hashMap=new HashMap<String,Object>();
		//table中的结构为哈希表加双向循环链表,元素可指定顺序为插入顺序和输出顺序
		Map linkedHashMap=new LinkedHashMap<String,Object>();
		
		
		
		Map treeMap=new TreeMap<String,Object>();
		Map weakHashMap=new WeakHashMap<String,Object>();
		//线程安全
		Map hashtable=new Hashtable<String,Object>();
		
		
		
	}
}
