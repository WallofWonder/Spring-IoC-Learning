package org.zyf.myspring;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;

/**
 * @author ZYF
 * @create 2021-4-2 22:45
 */
public class MyAnnotationConfigApplicationContext {
    public MyAnnotationConfigApplicationContext(String pack) {
        // 遍历包找到目标类（原材料）
        Set<BeanDefinition> beanDefinitions = findBeanDefinitions(pack);
        for (BeanDefinition beanDefinition : beanDefinitions) {
            System.out.println(beanDefinition);
        }

    }

    public Set<BeanDefinition> findBeanDefinitions(String pack) {
        // 1、获取包下的所有类
        Set<Class<?>> classes = MyTools.getClasses(pack);
        Set<BeanDefinition> beanDefinitions = new HashSet<>();
        // 2、遍历所有类，找到添加了注解的类
        for (Class<?> clazz : classes) {
            Component componentAnnotation = clazz.getAnnotation(Component.class);
            if (componentAnnotation != null) {
                // 获取Component注解的值(beanName)
                String beanName = componentAnnotation.value();
                // 若注解没有值
                if ("".equals(beanName)) {
                    // 获取首字母小写的类名
                    String className = clazz.getName().replaceAll(clazz.getPackage().getName() + ".", "");
                    beanName = className.substring(0,1).toLowerCase() + className.substring(1);
                }
                // 3、将这些类封装成BeanDefinition，放入集合
                beanDefinitions.add(new BeanDefinition(beanName, clazz));
            }
        }

        return beanDefinitions;
    }
}
