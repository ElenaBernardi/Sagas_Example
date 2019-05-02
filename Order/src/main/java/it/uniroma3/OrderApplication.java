package it.uniroma3;

import io.eventuate.jdbckafka.TramJdbcKafkaConfiguration;
import io.eventuate.tram.commands.common.ChannelMapping;
import io.eventuate.tram.commands.common.DefaultChannelMapping;
import io.eventuate.tram.commands.consumer.CommandDispatcher;
import io.eventuate.tram.commands.producer.TramCommandProducerConfiguration;
import io.eventuate.tram.events.publisher.TramEventsPublisherConfiguration;
import io.eventuate.tram.sagas.orchestration.SagaOrchestratorConfiguration;
import io.eventuate.tram.sagas.participant.SagaCommandDispatcher;
import io.eventuate.tram.sagas.participant.SagaParticipantConfiguration;
import it.uniroma3.messagingHandler.OrderCommandHandlers;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import it.uniroma3.domain.OrderServiceConfiguration;


@SpringBootApplication
@Configuration
@Import({OrderServiceConfiguration.class, TramJdbcKafkaConfiguration.class, TramEventsPublisherConfiguration.class,         TramCommandProducerConfiguration.class,
        SagaOrchestratorConfiguration.class, TramCommandProducerConfiguration.class, SagaParticipantConfiguration.class
})
public class OrderApplication {
    @Bean
    public CommandDispatcher orderCommandDispatcher(OrderCommandHandlers target) {
        return new SagaCommandDispatcher("orderCommandDispatcher", target.commandHandlers());
    }
    @Bean
    public ChannelMapping channelMapping() {
        return new DefaultChannelMapping.DefaultChannelMappingBuilder().build();
    }
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

}
