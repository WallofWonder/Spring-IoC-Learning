package org.zyf.factory;

import org.zyf.dao.HelloDao;
import org.zyf.dao.Impl.HelloDaoImpl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author ZYF
 * @create 2021-4-2 11:35
 */
public class BeanFactory {

    private static Properties properties;

    private static Map<String, Object> cache = new HashMap<>();

    static {
        properties = new Properties();
        try {
            properties.load(BeanFactory.class.getClassLoader().getResourceAsStream("factory.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object getDao(String beanName) {
        // 先判断缓存中是否存在bean
        if (!cache.containsKey(beanName)) {
            // 加锁保证多线程单例
            synchronized (BeanFactory.class){
                if (!cache.containsKey(beanName)) {
                    // 将bean存入cache
                    // 反射机制创建对象
                    try {
                        String value = properties.getProperty(beanName);
                        Class clazz = Class.forName(value);
                        Object object = clazz.getConstructor().newInstance();
                        cache.put(beanName, object);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                }
            }

        }



        return cache.get(beanName);
    }
}
