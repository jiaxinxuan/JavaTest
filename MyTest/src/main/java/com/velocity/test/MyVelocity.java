package com.velocity.test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.junit.Test;

/**
 * velocity测试类
 * @author caixin
 *
 */
public class MyVelocity {

	public static void printVelocity(){
		//新建velocity引擎
		VelocityEngine VMEngine=new VelocityEngine();
		 //设置引擎的环境变量
		VMEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		VMEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		VMEngine.init();//初始化引擎
		Template t =VMEngine.getTemplate("Student.vm");
		//新建Velocity上下文
		VelocityContext VC =new VelocityContext();
		VC.put("name", "贾新轩");
		VC.put("age", 23);
		VC.put("home", "河南省信阳市");
		StringWriter writer=new StringWriter();
		t.merge(VC, writer);
		System.out.println(writer.toString());
	}
	@Test
	public void velocityTest(){
		printVelocity();
	}
}
