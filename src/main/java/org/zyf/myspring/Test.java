package org.zyf.myspring;

/**
 * @author ZYF
 * @create 2021-4-2 23:30
 */
public class Test {
    public static void main(String[] args) {
        MyAnnotationConfigApplicationContext applicationContext = new MyAnnotationConfigApplicationContext("org.zyf.myspring");
        System.out.println(applicationContext.getBean("account"));
    }
}
