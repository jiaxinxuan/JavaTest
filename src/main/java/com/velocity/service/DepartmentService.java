package com.velocity.service;
 
import java.util.ArrayList;
import java.util.List;
 
import org.springframework.stereotype.Service;

import com.annotation.SystemServiceLog;
 
@Service
public class DepartmentService {
 
	@SystemServiceLog(description="查询当前用户数量")
   public List<Department> listDepartment() throws Exception {
       List<Department> list = null;
       list=new ArrayList<Department>();
       list.add(new Department(1, "Operations", "Chicago"));
       list.add(new Department(2, "HR", "Hanoi"));
       return list;
   }
}