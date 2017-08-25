package com.socket.test;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 模拟服务器
 * @author 贾新轩
 * 2017年8月24日  上午10:02:11
 */
public class Server implements Runnable {
	

	private ServerSocket serverSocket;
	
	public Server(int port) throws Exception{
		serverSocket=new ServerSocket(port);
//		serverSocket.setSoTimeout(10000);
	}
	@Override
	public void run() {
		while(true){
			try {
				System.out.println("等待链接端口号为:"+serverSocket.getLocalPort());
				Socket server=serverSocket.accept();//服务器通过accpet方法接收到一个套接字对象
				System.out.println("连接上的远程地址"+server.getRemoteSocketAddress());
				//接收客户端的消息
//				BufferedReader br=new BufferedReader(new InputStreamReader(server.getInputStream(),"UTF-8"));
//				System.out.println(br.readLine());
	           
				//向客户端发送消息
				BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(server.getOutputStream()));
				bw.write("欢迎您的访问!\n");
				bw.flush();
				server.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
	try {
        ServerSocket ss = new ServerSocket(8080);  
          
        Socket s = ss.accept();  
          
        InputStream in = s.getInputStream();  
          
        byte[] buf = new byte[1024];  
          
        int len = in.read(buf);  
          
        System.out.println(new String(buf,0,len));  
          
        OutputStream out = s.getOutputStream();  
          
        out.write("<font color=red>欢迎访问Http服务器！</font>".getBytes());  
          
        s.close();  
        ss.close();  
	} catch (Exception e) {
		e.printStackTrace();
	}
	}
}
