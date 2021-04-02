package org.zyf.factory;

import org.zyf.dao.HelloDao;
import org.zyf.dao.Impl.HelloDaoImpl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

/**
 * @author ZYF
 * @create 2021-4-2 11:35
 */
public class BeanFactory {

    private static Properties properties;

    static {
        properties = new Properties();
        try {
            properties.load(BeanFactory.class.getClassLoader().getResourceAsStream("factory.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object getDao() {
        String value = properties.getProperty("helloDao");

        // 反射机制创建对象
        try {
            Class clazz = Class.forName(value);
            Object object = clazz.getConstructor().newInstance();
            return object;
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

        return null;
    }
}
