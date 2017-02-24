package com.lucence.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlParse {
	
	/**
	 * 解析html文件
	 * @param path
	 */
	public static void parseHtml(String path){
		File file=new File(path);
		try {
			FileInputStream fis=new FileInputStream(file);
			String content=null;
			BufferedReader br=new BufferedReader(new InputStreamReader(fis));
			while(br.read()>=0){
				content=content+br.readLine();
				content=content+"\r\n";
			}
			System.out.println(getContent(content));
			System.out.println(getTile(content));
		} catch (Exception e) {
			System.out.println("无法解析该文件！");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 获取网页的标题
	 * @param content
	 * @return
	 */
	private static String getTile(String content){
		 final String regex="<title>.*?</title>";
		 List<String> titleList=new ArrayList<String>();
		 Pattern pattern=Pattern.compile(regex, Pattern.CANON_EQ);
		 Matcher matcher=pattern.matcher(content);
		 while(matcher.find()){
			 titleList.add(matcher.group());
		 }
		 
		 String title=null;
		 for (Iterator iterator = titleList.iterator(); iterator.hasNext();) {
			 title = (String) iterator.next();
		}
		return title.replaceAll("<.*?>", "");
	}
	
	/**
	 * 获取网页的一些正文内容
	 */
	private static String getContent(String content){
		  final StringBuilder sb=new StringBuilder();
		  final Pattern pa = Pattern.compile("[\u4e00-\u9fa5]", Pattern.DOTALL);
		  final Matcher ma = pa.matcher(content);
		  while (ma.find())
		  {
		   sb.append(ma.group());
		  }
		  String temp = sb.toString();
		  temp = temp.replaceAll("(<br>)+?", "\n");// 转化换行
		  temp = temp.replaceAll("<p><em>.*?</em></p>", "");// 去图片注释
		return temp;
	}
}
