package net.javaguides.springboot.controller;

import net.javaguides.enums.OrderStatus;
import net.javaguides.springboot.entity.Order;
import net.javaguides.springboot.kafka.OrderProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/product")
public class OrderController {

    private final OrderProducer orderProducer;

    public OrderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    @PostMapping("/order")
    public String placeOrder(@RequestBody Order order){
        order.setProductId(UUID.randomUUID().toString());
        order.setStatus(OrderStatus.CREATED);
        orderProducer.sendMessage(order);
        return "Order sent successfully....";
    }
}
