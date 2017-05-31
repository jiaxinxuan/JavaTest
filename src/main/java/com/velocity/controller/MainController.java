package com.velocity.controller;
 
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.annotation.SystemControllerLog;
import com.test.entity.User;
import com.velocity.service.Department;
import com.velocity.service.DepartmentService;
 
@Controller
public class MainController {
 
   @Autowired
   private DepartmentService deptService;
 
   @RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
   @SystemControllerLog(description ="欢迎，热烈欢迎！")
   public String welcomePage(Model model) throws Exception {
       List<Department> list = deptService.listDepartment();
       model.addAttribute("departments", list);   
       return "views/index";
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
	   return "redirect:/welcome";
   }
}