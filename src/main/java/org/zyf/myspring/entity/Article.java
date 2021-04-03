package org.zyf.myspring.entity;

import lombok.Data;
import org.zyf.myspring.Component;
import org.zyf.myspring.Value;

/**
 * @author ZYF
 * @create 2021-4-3 11:18
 */
@Data
@Component
public class Article {
    @Value("101")
    Integer articleID;
    @Value("SpringIoC")
    String title;
}
