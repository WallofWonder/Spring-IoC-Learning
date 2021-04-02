package org.zyf.myspring;

import java.util.Iterator;
import java.util.Set;

/**
 * @author ZYF
 * @create 2021-4-2 22:45
 */
public class MyAnnotationConfigApplicationContext {
    public MyAnnotationConfigApplicationContext(String pack) {
        // 遍历包找到目标类（原材料）
        Set<BeanDefinition> beanDefinitions = findBeanDefinitions(pack);

    }

    public Set<BeanDefinition> findBeanDefinitions(String pack) {
        // 获取包下的所有类
        Set<Class<?>> classes = MyTools.getClasses(pack);
        for (Class<?> aClass : classes) {
            System.out.println(aClass);
        }
        // 遍历所有类，找到添加了注解的类

        // 将这些类封装成BeanDefinition，放入集合


        return null;
    }
}
