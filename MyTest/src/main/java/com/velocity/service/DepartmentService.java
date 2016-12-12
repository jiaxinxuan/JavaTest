package com.velocity.service;
 
import java.util.ArrayList;
import java.util.List;
 
import org.springframework.stereotype.Service;
 
@Service
public class DepartmentService {
 
   public List<Department> listDepartment() {
       List<Department> list = new ArrayList<Department>();
 
       list.add(new Department(1, "Operations", "Chicago"));
       list.add(new Department(2, "HR", "Hanoi"));
       return list;
   }
}