package org.zyf.myspring;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author ZYF
 * @create 2021-4-2 23:09
 */
@Data
@AllArgsConstructor
public class BeanDefinition {
    private String beanName;
    private Class beanClass;
}
