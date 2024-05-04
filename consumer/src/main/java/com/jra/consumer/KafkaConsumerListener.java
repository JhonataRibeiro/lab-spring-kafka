package com.jra.consumer;

import com.jra.consumer.entity.Mensagem;
import com.jra.consumer.repository.MensagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerListener {

    @Autowired
    private MensagemRepository mensagemRepository;

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.group-id}")
    public void listen(String message) {
        Mensagem mensagem = new Mensagem();
        mensagem.setMensagem(message);

        // Simular um serviço demorando
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        mensagemRepository.save(mensagem);
        System.out.println(String.format("Received Messasge: [%s] ", message));
    }
}
