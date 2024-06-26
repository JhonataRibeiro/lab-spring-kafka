package com.jra.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${spring.kafka.producer.topic}")
    private String topicName;

    private static Integer i = 0;

    public void sendMessage(String msg) {
        kafkaTemplate.send(topicName, msg + " " + i);
        i++;
    }
}