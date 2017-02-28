package com.lucence.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
/**
 * 下载网页的类，根据传入的URL连接，下载网页
 * @author caixin
 */
public class GetUrlContent {
	
	public static String[] URL={"http://www.caixin.com"};
	
	public static void main(String[] args ){
		
		for(String str:URL){
			downloadHTML(str);
		}
		
	}	
	public static void downloadHTML(String str){
		URL url = null; 
		BufferedReader reader=null;
		BufferedWriter writer=null;
		String path=null;
		try {
			 url=new URL(str);
			 HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	         connection.setRequestMethod("GET");
	         connection.setConnectTimeout(2000);//设置连接时间
	         connection.setReadTimeout(2000);//设置读取时间
	         if(connection.getResponseCode()==200){//正常访问状态
	       	  reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));;//获取文档流
	       	  Date date=new Date();
	       	  path="E:\\"+date.getTime()/1000+".html";
	       	  File file=new File(path);
	          file.createNewFile();
	       	  writer=new BufferedWriter(new FileWriter(file));
	       	  String content=reader.readLine();
	       	  while(content!=null){
	       		  writer.write(content);
	       		  content=reader.readLine();
	       	  	}
	         }
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				reader.close();
				writer.flush();
				writer.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			cpFile(path,"E:\\1233.html");
		}
     }
	
	/**
	 * 复制文章
	 * @param srcFile
	 * @param desFile
	 */
	public static void cpFile(String srcFile,String desFile){
		File srcf=new File(srcFile);
		File desf=new File(desFile);
		BufferedReader reader=null;
		BufferedWriter writer=null;
		try {
			reader =new BufferedReader(new InputStreamReader(new FileInputStream(srcf)));
			writer =new BufferedWriter(new OutputStreamWriter(new FileOutputStream(desf)));
			String content=reader.readLine();
			while(content!=null){
				writer.write(content);
				writer.newLine();
				content=reader.readLine();
			}
		} catch (Exception e) {
			String exString=e.getMessage();
			System.out.println(exString);
		}
		finally{
			try {
				reader.close();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
