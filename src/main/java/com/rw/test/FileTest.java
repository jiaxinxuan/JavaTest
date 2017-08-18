package com.rw.test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;

import org.apache.lucene.analysis.ru.RussianLightStemFilterFactory;


/**
 * 文件读写练习
 * @author Administrator
 *2017年8月17日  下午5:30:02
 */
public class FileTest {
	
	/**
	 * 字符流
	 * 写入文件
 	 * 2017年8月18日下午2:24:22
	 * @see
	 * @param path
	 */
	public static void WriterFile(String path){
		BufferedWriter writer=null;
		try {
			writer=WriterFileUtils.BufferedWriterFile(path);
			writer.write("我日,你们真是太厉害了!");
			writer.newLine();//此处专门为不同操作系统的换行符提供的一个方法
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 字符流
	 * 读取文件
	 * @see BufferedReader
	 * @see 
	*2017年8月18日下午2:24:36
	 * @param path
	 */
	public static void ReaderFile(String path){
		BufferedReader reader=null;
		try {
			reader=ReaderFileUtils.BufferedReaderFile(path);
			char [] arr=new char[10];
			reader.read(arr);
			System.out.println(arr);
	/*			String string=reader.readLine();
			while(string!=null){
				System.out.println(string);
				 string=reader.readLine();
			}*/
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 字符流
	 * 控制台输入,保存文件
	 * @see InputStreamReader 
	 * @see BufferedReader
	*2017年8月18日下午2:24:44
	 * @param args
	 */
	public static void ConsolePrintSave(String path){
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		Writer writer=null;
		try {
			writer=WriterFileUtils.BufferedWriterFile(path);
			int i=0;
			while(i++!=10){
				writer.append(br.readLine());
				writer.write("\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				br.close();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 字符流
	 * 使用FileWriter类写入,此类中没有newLine方法
	 * 2017年8月18日下午2:39:40
	 * @param path
	 */
	public static void WriterFiles(String path){
		try {
			FileWriter fw=new FileWriter(path);
			fw.write("FileWriter类写入!");
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		String path="E:/files.txt";
		System.out.println(path);
//		WriterFile(path);
		ReaderFile(path);
//		ConsolePrintSave(path);
//		WriterFiles(path);
	}
}
