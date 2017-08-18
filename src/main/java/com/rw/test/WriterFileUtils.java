package com.rw.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
/**
 * 
 * @author Administrator
 *2017年8月17日  下午5:27:25
 */
public class WriterFileUtils {
	
	/**
	 * 
	 * 2017年8月18日上午10:58:43
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static BufferedWriter BufferedWriterFile(String path) throws Exception{
		File file=new File(path);
		if(file.exists()){
			file.createNewFile();
		}
		return 	new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"UTF-8"));//此处使用转换流指定了写入时的编码格式
	}
	/**
	 * 
	 * 2017年8月18日上午10:58:51
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static Writer OutputStreamWriterFile(String path) throws Exception{
		File file=new File(path);
		if(file.exists()){
			file.createNewFile();
		}
		return 	new OutputStreamWriter(new FileOutputStream(file));
	}
}
