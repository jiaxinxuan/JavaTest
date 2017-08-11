package com.freemarker.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.junit.Test;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreeMarkerUtils {

	/**
	 * 获取free marker模板
	 */
	public  Template  getTemplate(String fileName){
		try {
			//通过freemarker的配置类加载模板
			Configuration configuration=new Configuration();
			//指定模板位置
//			configuration.setClassForTemplateLoading(FreeMarkerUtils.class, "/");
			configuration.setDirectoryForTemplateLoading(new File("E:/"));//此处指定模板位置为本机，若是web环境中，需指定为类加载根目录
		    return 	configuration.getTemplate(fileName,"UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	 /**
     * 控制台输出
     * 
     * @param name
     * @param root
     */
    public void print(String name, Map<String, Object> map) {
        try {
            // 通过Template可以将模板文件输出到相应的流
            Template temp = this.getTemplate(name);
            temp.process(map, new PrintWriter(System.out));//指定输出位置
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 输出HTML文件
     * 
     * @param name
     * @param root
     * @param outFile
     */
    public void fprint(String name, Map<String, Object> map, String outFile) {
        FileWriter out = null;
        try {
            // 通过一个文件输出流，就可以写到相应的文件中，此处用的是绝对路径
            out = new FileWriter(new File("E:/" + outFile));
            Template temp = this.getTemplate(name);
            temp.process(map, out);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null)
                    out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    @Test
    public void fTest(){
    	FreeMarkerUtils util = new FreeMarkerUtils();
    	Map<String,Object> map=new HashMap<String ,Object>();
    	map.put("username", "贾新轩");
    	util.print("ftl1.ftl", map);
    	util.fprint("ftl1.ftl", map, "test.html");
    }
    
}
