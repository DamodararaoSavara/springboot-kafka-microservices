package net.javaguides.springboot.kafka;

import net.javaguides.dto.OrderEventDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class StockConsumer {
private static final Logger LOGGER = LoggerFactory.getLogger(StockConsumer.class);

    @KafkaListener(topics = "${spring.kafka.topic.name}",
                   groupId = "${spring.kafka.consumer.group-id}")
    public void consumer(OrderEventDto orderEventDto) {
        LOGGER.info(String.format("Order receive at Stock consumer ---> %s", orderEventDto));
    }
}
