package org.zyf.myspring.entity;

import lombok.Data;
import org.zyf.myspring.Component;
import org.zyf.myspring.Value;

/**
 * @author ZYF
 * @create 2021-4-2 16:00
 */
@Data
@Component("myOrder")
public class Order {
    @Value("xx123")
    private String orderId;
    @Value("2100.0")
    private Float price;
}
