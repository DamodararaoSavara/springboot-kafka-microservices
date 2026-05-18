package net.javaguides.springboot.kafka;

import net.javaguides.dto.OrderEventDto;
//import net.javaguides.entity.OrderEmail;
//import net.javaguides.repository.EmailRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EmailConsumer {
/*
private static final Logger LOGGER = LoggerFactory.getLogger(EmailConsumer.class);

private EmailRepository emailRepository;
private EmailMapper emailMapper;

    public EmailConsumer(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    @KafkaListener(topics = "${spring.kafka.topic.name}",
                   groupId = "${spring.kafka.consumer.group-id}")
    public void consumer(OrderEventDto orderEventDto) {
        LOGGER.info(String.format("Order receive at Email consumer ---> %s", orderEventDto));

        OrderEmail orderEmail = EmailMapper.mapToOrderEmail(orderEventDto);

        emailRepository.save(orderEmail);
*/

//    }
}
