package com.http.clitent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
/**
 * 基于jdk 实现的get和post请求实例
 * @author 贾新轩
 * 2017年8月25日  上午11:29:29
 */
public class HttpUtils {
	
	/**
	 * 测试方法
	 * 2017年8月25日下午2:47:49
	 * @param args
	 */
	public static void main(String[] args) {
		
		
	}
	
	/**
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);//拼接url串
            // 获取URLConnection连接对象
            URLConnection connection = realUrl.openConnection();
            // 设置连接通用的请求属性,
            connection.setRequestProperty("accept", "*/*");//浏览器可以支持的mime的类型,即是浏览器可以支持的格式,如jpg,text,html等
            connection.setRequestProperty("connection", "Keep-Alive");//是否需要持久连接
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");//标识浏览器的类型不同浏览器的值不同
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 获取URLConnection连接对象
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);//表示连接到服务器后需要向其传输数据
            conn.setDoInput(true);//表示连接到服务器后需要返回数据,就是可以获取连接的输入输出流
            // 获取URLConnection对象对应的输出流,必须在connect()或者getInputStream()方法之前调用getOutputStream()返回输出流，并且写入数据，数据的格式为：name1=value1&name2=value2。
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }    
    
   
    /**
     * 
     * 自定义post请求实现
     */
    public void testPost(){
    	BufferedWriter bw=null;
		BufferedReader br=null;
		try {
			URL url=new URL("http://gateway.caixin.com/api/extapi/homeInterfacetest.jsp");
			String params="subject=101121220&start=4&count=3&picdim=_145_97&type=2";
			URLConnection urlCon=url.openConnection();
			//设置连接通用属性,不要忘记设置连接属性哦!!!!
			urlCon.setRequestProperty("accept", "*/*");//浏览器可以支持的mime的类型,即是浏览器可以支持的格式,如jpg,text,html等
			urlCon.setRequestProperty("connection", "Keep-Alive");//是否需要持久连接
			urlCon.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");//标识浏览器的类型不同浏览器的值不同
			urlCon.setDoOutput(true);
			urlCon.setDoInput(true);
			bw=new BufferedWriter(new OutputStreamWriter(urlCon.getOutputStream()));
			bw.write(params);
			bw.flush();
			 br=new BufferedReader(new InputStreamReader(urlCon.getInputStream(), "UTF-8"));
			String result=br.readLine();
			String content="";
			//获取响应内容
			while(result!=null){
				content+=result;
				result=br.readLine();
			}
			System.out.println(content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				bw.close();
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
    }
}
