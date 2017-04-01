package com.mail.test;
import java.util.ArrayList;  
import java.util.List;  
  
import org.junit.Test;  
import org.junit.runner.RunWith;  
import org.slf4j.Logger;  
import org.slf4j.LoggerFactory;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.test.context.ContextConfiguration;  
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;  
  
 /**
  * 测试邮件发送类
  * @author Administrator
  *
  */
@RunWith(SpringJUnit4ClassRunner.class)//@RunWith(SpringJUnit4ClassRunner.class) 来启动 Spring 对测试类的支持  
@ContextConfiguration("/spring-mail.xml")//@ContextConfiguration 注释标签来指定 Spring 配置文件或者配置类的位置  
public class MailTest {  
    private static final Logger log = LoggerFactory.getLogger(MailTest.class);  
      
    @Autowired  
    private MailUtil mailUtil;  
    /**
     * 单发测试
     */
    @Test  
    public void sendSingleTest(){  
        log.info("sendSingleTest");  
        mailUtil.send("xinxuanjia@caixin.com", "This is a test single mail", "Hello Single!");  
    }  
     /**
      * 群发测试 
      */
    @Test  
    public void sendMassTest(){  
        log.info("sendMassTest");  
        List<String> recipients=new ArrayList<String>();  
        recipients.add("xx@sina.com");  
        recipients.add("xx@qq.com");  
        mailUtil.send(recipients, "This is a test mass mail", "Hello Mass!");  
    }  
}  