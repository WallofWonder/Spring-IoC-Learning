package org.zyf.myspring;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ZYF
 * @create 2021-4-2 23:58
 */
@Target(ElementType.TYPE)  // 类注解
@Retention(RetentionPolicy.RUNTIME) // 运行时读取注解
public @interface Component {
    String value() default "";
}
