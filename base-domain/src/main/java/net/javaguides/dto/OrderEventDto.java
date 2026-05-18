package net.javaguides.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.javaguides.enums.OrderStatus;


import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEventDto {

    private Long orderId;
    private String productName;
    private Integer quantity;
    private BigDecimal price;
    private OrderStatus status;

}
