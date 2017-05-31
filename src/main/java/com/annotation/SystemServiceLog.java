package com.annotation;  
  
import java.lang.annotation.*;  
  
/** 
 *自定义注解 拦截service 
 */  
  
@Target({ElementType.PARAMETER, ElementType.METHOD})  //标识该注解的是用于描述参数和方法
@Retention(RetentionPolicy.RUNTIME) //标识该注解运行时有效 
@Documented  //@Documented用于描述其它类型的annotation应该被作为被标注的程序成员的公共API，因此可以被例如javadoc此类的工具文档化。Documented是一个标记注解，没有成员。
public  @interface SystemServiceLog {  
  
    String description()  default "";  
  
  
}  