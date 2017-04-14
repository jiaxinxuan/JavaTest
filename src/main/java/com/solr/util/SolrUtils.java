package com.solr.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;

public class SolrUtils {
	
	/**
	 * 创建SolrServer对象，获取solr的连接。 
	 * 获取SolrServer对象以后，可以设置更多的连接属性，具体看业务需求
	 * @param url Solr的core的URL
	 * @return
	 */
    public SolrServer createSolrServerUtil(String url) {  
        HttpSolrServer solr = null;  
        try {  
            solr = new HttpSolrServer(url); 
            //设置连接属性
            solr.setConnectionTimeout(100);  //超时等待时间
            solr.setDefaultMaxConnectionsPerHost(100);  
            solr.setMaxTotalConnections(100);  //最大连接数
        } catch (Exception e) {  
            System.out.println("请检查tomcat服务器或端口是否开启!");  
            e.printStackTrace();  
        }  
        return solr;  
    }  
    
    /**
     * Solr查询简单测试  
     */
    @Test
    public void SolrQuereyUtil() {  
    	SolrServer solrServer = createSolrServerUtil("http://120.25.96.145:8081/solr/new_core");  
        System.out.println("简单查询取出前二十个");  
        String dtStart = new SimpleDateFormat("yyyyMMddHHmmssSSS")  
                .format(new Date());  
        System.out.println("开始时间：" + dtStart + "\n");  
        try {  
            SolrQuery query = new SolrQuery();// 查询  
            query.setQuery("*:*");  
            query.setRows(20);  
            SolrDocumentList docs = solrServer.query(query).getResults();  
            for (SolrDocument sd : docs) {  
               System.out.println(sd.getFieldValue("title"));  
               System.out.println(sd.getFieldValue("baseUrl"));  
            }  
            solrServer.shutdown();  
            String dtEnd = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());  
            System.out.println("开始时间：" + dtEnd + "\n");  
        } catch (SolrServerException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
    }  
}
