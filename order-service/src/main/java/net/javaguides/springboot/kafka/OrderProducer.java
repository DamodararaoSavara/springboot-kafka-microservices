package net.javaguides.springboot.kafka;

import lombok.AllArgsConstructor;
import net.javaguides.enums.OrderStatus;
import net.javaguides.springboot.Mapper.OrderMapper;

import net.javaguides.dto.OrderEventDto;

import net.javaguides.springboot.entity.Order;
import net.javaguides.springboot.repository.OrderRepository;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderProducer.class);
    private NewTopic topic;
    private KafkaTemplate<String, OrderEventDto> kafkaTemplate;
    private OrderRepository orderRepository;
    private OrderMapper orderMapper;


    public void sendMessage(Order order){

        Order savedOrder =  orderRepository.save(order);
        OrderEventDto orderEventDto = orderMapper.toDto(order);
        LOGGER.info(String.format("Order event sent -->%s", orderEventDto.toString()));

        Message<OrderEventDto> message = MessageBuilder
                .withPayload(orderEventDto)
                .setHeader(KafkaHeaders.TOPIC,topic.name())
                .build();

        kafkaTemplate.send(message);
    }
}
