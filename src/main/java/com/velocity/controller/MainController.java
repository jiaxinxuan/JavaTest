package com.velocity.controller;

import com.annotation.SystemControllerLog;
import com.test.entity.User;
import com.velocity.service.Department;
import com.velocity.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Controller
public class MainController {
 
   @Autowired
   private DepartmentService deptService;

   @RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
   @SystemControllerLog(description ="欢迎，热烈欢迎！")
   public String welcomePage(Model model) throws Exception {
       List<Department> list = deptService.listDepartment();
       model.addAttribute("departments", list);
       return "api/index";
   }

   @RequestMapping(value = { "/", "/exit" }, method = RequestMethod.GET)
   @SystemControllerLog(description ="再见，欢迎下次再来！")
   public String exitPage(Model model) {
       return "layouts/layout";
   }

   @RequestMapping("/login")
   public String login(HttpServletRequest request){
	   User user=new User();
	   user.setName("贾新轩");
	   HttpSession session=request.getSession();
	   session.setAttribute("user", user);
	   return "redirect:/test";
   }

    @RequestMapping("/test")
    public String Test(HttpServletRequest request) {
        //后台获取国际化的信息
        RequestContext requestContext = new RequestContext(request);
        String message = requestContext.getMessage("welcome");  //获取国际化信息
        System.out.println(message);
        System.out.println("aaaaaaaaaa");

        return "jsp/main";
    }
    @RequestMapping("/download")
    public void download(HttpServletRequest request, HttpServletResponse response) throws Exception{

        try {
            String downloadFilename = "中文.zip";//文件的名称
            downloadFilename = URLEncoder.encode(downloadFilename, "UTF-8");//转换中文否则可能会产生乱码
            response.setContentType("application/octet-stream");// 指明response的返回对象是文件流
            response.setHeader("Content-Disposition", "attachment;filename=" + downloadFilename);// 设置在下载框默认显示的文件名
            ZipOutputStream zos = new ZipOutputStream(response.getOutputStream());
            File file=new File("/data/www/images/7yh6");
            File[] files=null;
            if(file.exists()&&file.isDirectory()){
                files=file.listFiles();
            }
            for (int i=0;i<files.length;i++) {
                zos.putNextEntry(new ZipEntry(files[i].getName()+".jpg"));
                InputStream fis =new FileInputStream(files[i]);
                byte[] buffer = new byte[1024];
                int r = 0;
                while ((r = fis.read(buffer)) != -1) {
                    zos.write(buffer, 0, r);
                }
                fis.close();
            }
            zos.flush();
            zos.close();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}