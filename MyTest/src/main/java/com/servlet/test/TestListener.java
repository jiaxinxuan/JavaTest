package com.servlet.test;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

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
    	//HelloVelocity.printVolecity();
    }
	
}
