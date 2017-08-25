package com.socket.test;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client {
	 public static void main(String [] args)
	   {
	      String serverName = "127.0.0.1";
	      int port =8080;
	      try
	      {
	         System.out.println("连接到主机：" + serverName + " ，端口号：" + port);
	         Socket client = new Socket(serverName, port);//客户端实例化一个套接字对象
	         System.out.println("远程主机地址：" + client.getRemoteSocketAddress());
	         //向服务器发送消息
//	         BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(client.getOutputStream(),"UTF-8"));
//	         bw.write("你好啊,服务器!" + client.getLocalSocketAddress());
//	         bw.flush();
	         //接收服务器消息
	     	BufferedReader br=new BufferedReader(new InputStreamReader(client.getInputStream(),"UTF-8"));
			System.out.println(br.readLine());
			br.close();
//	        bw.close();
	        client.close();
	      }catch(IOException e)
	      {
	         e.printStackTrace();
	      }
	   }
}
