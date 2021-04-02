package org.zyf.spring.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author ZYF
 * @create 2021-4-2 15:42
 */
@Data
@Component
public class Account {
    @Value("1")
    private Integer id;
    @Value("张三")
    private String name;
    @Value("18")
    private Integer age;
}
