package com.http.clitent;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.poi.util.SystemOutLogger;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
public class Snippet {
	    public static String getWeChatUserInfo(){
	        String urlNameString = "http://172.20.200.83:10002/clearCache";
	        String result="";
	          try {
	                HttpGet request = new HttpGet(urlNameString);
	                HttpClient httpClient = new DefaultHttpClient();
	                HttpResponse response = httpClient.execute(request);
	                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
	                    result= EntityUtils.toString(response.getEntity(),"utf-8");
	                } 
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        return result;
	    }
	    
public static void main(String[] args) {
	System.out.println(getWeChatUserInfo());
}
}

