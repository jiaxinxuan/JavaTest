package com.socket.test;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * InetAddress类表示互联网协议(IP)地址
 * @author 贾新轩
 * 2017年8月23日  下午6:17:52
 */
public class InetAddressTest {
	
	public static void main(String[] args) {
		try {
			InetAddress inetAddress=InetAddress.getByName("www.caixin.com");
			System.out.println(inetAddress.getHostName());
			System.out.println(inetAddress.getHostAddress());
			System.out.println(inetAddress.getAddress());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}
