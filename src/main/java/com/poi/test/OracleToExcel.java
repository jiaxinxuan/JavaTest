package com.poi.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 贾新轩
 * @time 17-12-7
 */
public class OracleToExcel {

    private static Pattern pattern=Pattern.compile("[a-zA-z]+://p.bokecc.com[^\\s]*",1);

    public static void main(String[] args) {
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet result = null;
        List<News> list=new ArrayList<>(500000);
        try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("开始尝试连接数据库！");
            String url = "jdbc:oracle:" + "thin:@172.20.200.181:1521:caijing";
            String user = "caixincms";
            String password = "LSdn882DdEDs";
            con = DriverManager.getConnection(url, user, password);
            System.out.println("连接成功！");
            String sql = "select a.ENTITY_ID id,a.ENTITY_DESC title,a.ENTITY_TIME time,b.NEWS_CONTENT text,b.NEWS_VIDEOS video from CMS_ENTITY a LEFT join CMS_NEWS  b  ON a.ENTITY_ID=b.ENTITY_ID";
            pre = con.prepareStatement(sql);
            result = pre.executeQuery();
            Integer num=0;
            while (result.next()){
                News news=new News();
                news.setId(result.getInt("id"));
                news.setTitle(result.getString("title"));
                news.setTime(result.getString("time"));
                Blob text=result.getBlob("text");
                BufferedReader reader=new BufferedReader(new InputStreamReader(text.getBinaryStream()));
                char[] buff = new char[2048];
                String content="";
                String video=result.getString("video");
                while (reader.read(buff)!=-1){
                    content+=new String(buff);
                }
                Matcher matcher=pattern.matcher(content+video);
                if (matcher.find()){
                   news.setVideo(matcher.group());
                   list.add(news);
                }
                System.out.println(num++);
                if(num==10){
                    break;
                }
            }
            LinkedHashMap<String, String> headMap = new LinkedHashMap<String, String>();
            headMap.put("id", "标识");
            headMap.put("title", "标题");
            headMap.put("text", "正文");
            headMap.put("time", "时间");
            headMap.put("video", "视频代码");
            //将匹配的结果导入excel
            new ExportExcel().toExcel("CMS视频信息","/home/jia/cms.xlsx",headMap,list);
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
                try{

                    if (result != null) {
                        result.close();
                    }
                    if (pre != null) {
                        pre.close();
                    }
                    if (con != null) {
                        con.close();
                    }
                    System.out.println("数据库连接已关闭！");
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
        }
    }


    public static class News{
        private Integer id;
        private String title;
        private String text;
        private String video;
        private String time;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getVideo() {
            return video;
        }

        public void setVideo(String video) {
            this.video = video;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        @Override
        public String toString() {
            return "news{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    ", text='" + text + '\'' +
                    ", video='" + video + '\'' +
                    ", time='" + time + '\'' +
                    '}';
        }
    }
}