package com.loader.test;

public class Long {
    /**
     * 说明，.getClassLoader方法可以获取到类加载器，也就是谁把我加载到内存的那个谁。
     * https://www.cnblogs.com/wang-meng/p/5574071.html可参考这个博文
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Hi, i am here");
        //获取类加载器sun.misc.Launcher$AppClassLoader@18b4aac2
        ClassLoader classLoader=Long.class.getClassLoader();
        System.out.println(classLoader);
        //获取父类加载器sun.misc.Launcher$ExtClassLoader@2dda6444
        System.out.println(classLoader.getParent());
        //获取父类加载器的父类加载器null
        System.out.println(classLoader.getParent().getParent());
    }
}