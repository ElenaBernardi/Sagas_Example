package it.uniroma3;

import io.eventuate.tram.commands.common.ChannelMapping;
import io.eventuate.tram.commands.common.DefaultChannelMapping;
import io.eventuate.tram.commands.consumer.CommandDispatcher;
import io.eventuate.tram.commands.producer.TramCommandProducerConfiguration;
import io.eventuate.tram.events.publisher.TramEventsPublisherConfiguration;
import io.eventuate.tram.sagas.orchestration.SagaOrchestratorConfiguration;
import io.eventuate.tram.sagas.participant.SagaCommandDispatcher;
import io.eventuate.tram.sagas.participant.SagaLockManager;
import io.eventuate.tram.sagas.participant.SagaParticipantConfiguration;
import it.uniroma3.messagingHandlers.ConsumerCommandHandlers;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import io.eventuate.jdbckafka.TramJdbcKafkaConfiguration;


@SpringBootApplication
@Configuration
@Import({SagaParticipantConfiguration.class, TramEventsPublisherConfiguration.class,
        TramCommandProducerConfiguration.class,
        SagaOrchestratorConfiguration.class,
        TramJdbcKafkaConfiguration.class})
public class ConsumerApplication {
    @Bean
    public ChannelMapping channelMapping() {
        return new DefaultChannelMapping.DefaultChannelMappingBuilder().build();
    }

    @Bean
    public CommandDispatcher consumerCommandDispatcher(ConsumerCommandHandlers target,
                                                       SagaLockManager sagaLockManager) {

        return new SagaCommandDispatcher("customerCommandDispatcher", target.commandHandlers());
    }


        public static void main(String[] args) {
            SpringApplication.run(ConsumerApplication.class, args);
        }


}
