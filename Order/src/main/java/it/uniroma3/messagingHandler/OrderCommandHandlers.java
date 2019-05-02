package it.uniroma3.messagingHandler;

import io.eventuate.tram.commands.consumer.CommandHandlers;
import io.eventuate.tram.commands.consumer.CommandMessage;
import io.eventuate.tram.messaging.common.Message;
import io.eventuate.tram.sagas.participant.SagaCommandHandlersBuilder;

import it.uniroma3.OrderServiceChannel;
import org.springframework.beans.factory.annotation.Autowired;
import it.uniroma3.ApproveOrderCommand;
import it.uniroma3.RejectOrderCommand;
import it.uniroma3.domain.OrderService;
import org.springframework.stereotype.Service;

import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withSuccess;
@Service
public class OrderCommandHandlers {
    @Autowired
    private OrderService orderService;

    public CommandHandlers commandHandlers() {
        return SagaCommandHandlersBuilder
                .fromChannel(OrderServiceChannel.orderServiceChannel)
                .onMessage(ApproveOrderCommand.class, this::approveOrder)
                .onMessage(RejectOrderCommand.class, this::rejectOrder)
                .build();

    }
    public Message approveOrder(CommandMessage<ApproveOrderCommand> cm){
        Long orderId = cm.getCommand().getOrderId();
        orderService.approveOrder(orderId);
        return withSuccess();
    }

    public Message rejectOrder(CommandMessage<RejectOrderCommand> cm){
        Long orderId = cm.getCommand().getOrderId();
        orderService.rejectOrder(orderId);
        return withSuccess();
    }
}
