package com.servlet.test;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.lucence.test.WebCrawlerDemo;
import com.velocity.test.HelloVelocity;

/**
 * Application Lifecycle Listener implementation class TestListener
 *
 */

/**
 * servlet的监听器
 * @author caixin
 *
 */
public class TestListener implements ServletContextListener {

	private Logger log=Logger.getLogger(TestListener.class);
    /**
     * Default constructor. 
     */
    public TestListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     * 监听服务器的停止
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     * 监听服务器的启动
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    	log.info("监听器启动");
    	try {
    		HelloVelocity.printVolecity();
    		log.info("文件输出完毕");
		} catch (Exception e) {
			log.info("文件加载失败");
		}
    	
    	//WebCrawlerDemo.main(null);
    }
	
}
