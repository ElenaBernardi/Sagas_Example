package it.uniroma3.messagingHandlers;


import io.eventuate.tram.commands.consumer.CommandHandlers;
import io.eventuate.tram.commands.consumer.CommandMessage;
import io.eventuate.tram.messaging.common.Message;
import io.eventuate.tram.sagas.participant.SagaCommandHandlersBuilder;
import it.uniroma3.ConsumerServiceChannel;
import it.uniroma3.exception.ConsumerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import it.uniroma3.ValidateOrderByConsumer;
import it.uniroma3.domain.ConsumerService;
import org.springframework.stereotype.Service;

import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withFailure;
import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withSuccess;

@Service
public class ConsumerCommandHandlers {

    @Autowired
    private ConsumerService consumerService;

    public CommandHandlers commandHandlers() {
        System.out.println("############ son qua ##########");

        return SagaCommandHandlersBuilder
                .fromChannel(ConsumerServiceChannel.consumerServiceChannel)
                .onMessage(ValidateOrderByConsumer.class, this::validateOrderForConsumer)
                .build();
    }

    private Message validateOrderForConsumer(CommandMessage<ValidateOrderByConsumer> cm) {
        try {
            System.out.println("############ Entrato ##########");

            consumerService.validateOrderForConsumer(cm.getCommand().getConsumerId());
            System.out.println("############ Valido ##########");
            return withSuccess();
        } catch (ConsumerNotFoundException e) {
            System.out.println("############ Non Valido ##########");
            return withFailure();
        }
    }
}
