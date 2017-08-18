package com.rw.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * @author Administrator
 *2017年8月17日  下午5:03:28
 */
public class ReaderFileUtils {

	/**
	 * 2017年8月17日下午5:15:35
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static Reader InputStreamReaderFile(String path) throws Exception{
		File file=new File(path);
		if(file.exists()){
			file.createNewFile();
		}
		return new InputStreamReader(new FileInputStream(file));
	}
	/**
	 * 
	*2017年8月17日下午5:18:36
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static BufferedReader BufferedReaderFile(String path) throws Exception{
		File file=new File(path);
		if(file.exists()){
			file.createNewFile();
		}
		return new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));//此处使用转换流,指定了读取编码
	}
}
