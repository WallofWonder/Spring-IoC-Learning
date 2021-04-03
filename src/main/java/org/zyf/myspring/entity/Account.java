package org.zyf.myspring.entity;

import lombok.Data;
import org.zyf.myspring.Autowired;
import org.zyf.myspring.Component;
import org.zyf.myspring.Qualifier;
import org.zyf.myspring.Value;

/**
 * @author ZYF
 * @create 2021-4-2 23:30
 */
@Data
@Component
public class Account {
    @Value("1")
    private Integer id;
    @Value("WonderWall")
    private String name;
    @Value("22")
    private Integer age;
    @Autowired
    @Qualifier("myOrder")
    private Order order;
    @Autowired
    private Article article;
}
