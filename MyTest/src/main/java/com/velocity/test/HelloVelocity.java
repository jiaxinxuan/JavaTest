package com.velocity.test;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

public class HelloVelocity {
 public static void main(String[] args) {
	 printVolecity();
 }
 
 public static void printVolecity(){
	 VelocityEngine ve = new VelocityEngine();//新建模板引擎
	 //设置引擎的环境变量
	 ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
	 ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
	 ve.init();//初始化
	 //加载vm文件
	 Template t = ve.getTemplate("Hellovelocity.vm");
	 //新建内容模板上下文，可以填入一系列数据，引擎在解析vm文件的时候，会自动加载。
	 VelocityContext ctx = new VelocityContext();
	 //添加数据
	 ctx.put("name", "velocity");
	 ctx.put("date", (new Date()).toString());
	 List<String> temp = new ArrayList<String>();
	 temp.add("1");
	 temp.add("2");
	 temp.add("2");
	 temp.add("2");
	 temp.add("2");
	 ctx.put("list", temp);
	 HashMap<String, String> test=new HashMap<String,String>();
	 test.put("1", "a");
	 test.put("2", "b");
	 test.put("3", "c");
	 test.put("4", "d");
	 test.put("5", "e");
	 ctx.put("map", test);
	 StringWriter sw = new StringWriter();
	 //将上下文中的数据合并到vm文件中，并获取输出
	 t.merge(ctx, sw);
	 
	 System.out.println(sw.toString()); 
 }
}