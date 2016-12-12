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
	 VelocityEngine ve = new VelocityEngine();
	 ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
	 ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
	 
	 ve.init();
	 
	 Template t = ve.getTemplate("hellovelocity.vm");
	 VelocityContext ctx = new VelocityContext();
	 
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
	 
	 t.merge(ctx, sw);
	 
	 System.out.println(sw.toString()); 
 }
}