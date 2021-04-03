package org.zyf.myspring;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author ZYF
 * @create 2021-4-2 22:45
 */
public class MyAnnotationConfigApplicationContext {

    private Map<String, Object> ioc = new HashMap<>();

    public MyAnnotationConfigApplicationContext(String pack) {
        // 1、遍历包找到目标类（原材料）
        Set<BeanDefinition> beanDefinitions = findBeanDefinitions(pack);
        // 2、根据BeanDefinition创建bean
        createObject(beanDefinitions);
        // 3、自动装载
        autowiredObject(beanDefinitions);
    }

    /**
     * 根据BeanDefinition自动装载
     *
     * @param beanDefinitions BeanDefinition集合
     */
    public void autowiredObject(Set<BeanDefinition> beanDefinitions) {
        for (BeanDefinition beanDefinition :
                beanDefinitions) {
            Class clazz = beanDefinition.getBeanClass();
            for (Field declaredField : clazz.getDeclaredFields()) {
                if (declaredField.getAnnotation(Autowired.class) != null) {
                    Qualifier qualifier = declaredField.getAnnotation(Qualifier.class);
                    if (qualifier != null) {
                        // byName
                        try {
                            String beaName = qualifier.value();
                            // 从IoC容器中找到对应类
                            Object bean = getBean(beaName);
                            String fieldName = declaredField.getName();
                            String methodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                            // 获取setter方法
                            Method method = clazz.getMethod(methodName, declaredField.getType());
                            // 获取装配的目标类
                            Object object = getBean(beanDefinition.getBeanName());
                            // 将bean装配到目标类
                            method.invoke(object, bean);
                        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                    else {
                        // byType
                        try {
                            String fieldName = declaredField.getType().getName();
                            String packageName = declaredField.getType().getPackage().getName();
                            String beanName = fieldName.replaceAll(packageName + ".", "").toLowerCase();
                            String methodName = "set" + beanName.substring(0, 1).toUpperCase() + beanName.substring(1);
                            // 获取setter方法
                            Method method = clazz.getMethod(methodName, declaredField.getType());
                            // 从IoC容器中找到对应类
                            Object bean = getBean(beanName);
                            // 获取装配的目标类
                            Object object = getBean(beanDefinition.getBeanName());
                            // 将bean装配到目标类
                            method.invoke(object, bean);
                        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public Object getBean(String beanName) {
        return ioc.get(beanName);
    }

    /**
     * 根据BeanDefinition创建bean
     *
     * @param beanDefinitions BeanDefinition集合
     */
    public void createObject(Set<BeanDefinition> beanDefinitions) {
        for (BeanDefinition beanDefinition : beanDefinitions) {
            Class clazz = beanDefinition.getBeanClass();
            String beanName = beanDefinition.getBeanName();
            try {
                Object object = clazz.getConstructor().newInstance();
                // 对带@Value注解的属性赋值
                for (Field declaredField : clazz.getDeclaredFields()) {
                    Value valueAnnotation = declaredField.getAnnotation(Value.class);
                    if (valueAnnotation != null) {
                        String value = valueAnnotation.value();
                        String fieldName = declaredField.getName();
                        String methodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                        Method method = clazz.getMethod(methodName, declaredField.getType());
                        // 得到的value是String类型的，需要将其转换成属性对应的类型
                        Object val = null;
                        switch (declaredField.getType().getName()) {
                            case "java.lang.Integer":
                                val = Integer.parseInt(value);
                                break;
                            case "java.lang.Long":
                                val = Long.parseLong(value);
                                break;
                            case "java.lang.Short":
                                val = Short.parseShort(value);
                                break;
                            case "java.lang.String":
                                val = value;
                                break;
                            case "java.lang.Float":
                                val = Float.parseFloat(value);
                                break;
                            case "java.lang.Double":
                                val = Double.parseDouble(value);
                                break;
                            case "java.lang.Character":
                                val = value.toCharArray()[0];
                                break;
                        }
                        method.invoke(object, val);
                    }
                }
                // 存入IoC容器
                ioc.put(beanName, object);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 遍历包找到带注解的类
     *
     * @param pack 包名
     * @return 带注解的类集合
     */
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
                    beanName = className.substring(0, 1).toLowerCase() + className.substring(1);
                }
                // 3、将这些类封装成BeanDefinition，放入集合
                beanDefinitions.add(new BeanDefinition(beanName, clazz));
            }
        }

        return beanDefinitions;
    }
}
