package se.iths.storemanagementsystem.jms.sender;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import se.iths.storemanagementsystem.entity.UserEntity;
import se.iths.storemanagementsystem.jms.config.JmsConfig;
import se.iths.storemanagementsystem.jms.model.MessageObject;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class Sender {

    private final JmsTemplate jmsTemplate;

    public Sender(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    //@Scheduled(fixedRate = 2000)
    public void sendMessage(UserEntity userEntity) {

        System.out.println("Sending message...");

        MessageObject messageObject =
                new MessageObject(UUID.randomUUID(), "User with id " + userEntity.getId() + " created",
                        userEntity.getEmail(), LocalDateTime.now());
        jmsTemplate.convertAndSend(JmsConfig.JU20DEC_QUEUE, messageObject);

        System.out.println("Message sent!");

    }
}
