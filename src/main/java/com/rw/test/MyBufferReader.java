package com.rw.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * 基于i/o流的装饰模式练习
 * @author Administrator
 * 2017年8月18日  下午4:32:00
 */
public class MyBufferReader extends Reader{
	
     private Reader r;  
     MyBufferReader(Reader r){  
          this.r  = r;  
     } 
	@Override
	public int read(char[] cbuf, int off, int len) throws IOException {
		return  r.read(cbuf,off,len);  
		
	}

	@Override
	public void close() throws IOException {
		 r.close();  
	}
   //一次读一行数据的方法,这里时增强的方法,  
   public String myReaderline()  throws IOException {  
       //定义一个临时容器，原BufferReader封装的是字符数组。  
       //为了演示方便。定义一个StringBuilder容器。最终要将数据变成字符串  
       StringBuilder sb = new StringBuilder();  
       int ch = 0;  
       while((ch = r.read()) != -1)  
       {  
           if(ch == '\r')   
               continue;  
           if(ch == '\n')                    //遇到换行符\n,返回字符串  
               return sb.toString();  
           else  
           sb.append((char)ch);  
       }  
       if(sb.length()!=0)                    //当最后一行不是以\n结束时候，这里需要判断  
           return sb.toString();  
       return null;  
   }  
   
public static void main(String[] args) throws Exception {
	   MyBufferReader myReader=new MyBufferReader(new InputStreamReader(new FileInputStream("E:/files.txt")));
	   System.out.println(myReader.myReaderline());
	   myReader.close();
}
}
