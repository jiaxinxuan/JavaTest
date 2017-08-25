package com.socket.test;

import java.net.URL;
/**
 * jdk之url练习,请注意URL和URI的区别
 * @author 贾新轩
 * 2017年8月25日  下午2:42:36
 */
public class UrlTest {
	public static void main(String[] args) {
		try {
			/**
			 * 构造方法说明:
			 * url类里面有个内部类,构造方法会调用该类中paser方法来解析传入的url,同时,url 类已经定义好一些列关于url的属性和方法
			 */
			URL url=new URL("http://gateway.caixin.com/api/extapi/getNewsContentKeyWord.jsp?beginday=2017-08-18&closeday=2018-08-23");
			url.openConnection();
			//打印url的信息
			System.out.println(url.getPath());
			System.out.println(url.getQuery());
			System.out.println(url.getHost());
			System.out.println(url.getPort());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
