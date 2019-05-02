package it.uniroma3.sagaparticipants;

import io.eventuate.tram.commands.common.Success;
import io.eventuate.tram.commands.consumer.CommandWithDestination;
import io.eventuate.tram.sagas.simpledsl.CommandEndpoint;
import io.eventuate.tram.sagas.simpledsl.CommandEndpointBuilder;
import it.uniroma3.ApproveOrderCommand;
import it.uniroma3.OrderServiceChannel;
import it.uniroma3.RejectOrderCommand;
import it.uniroma3.sagas.CreateOrderSagaState;
import org.springframework.stereotype.Service;
import static io.eventuate.tram.commands.consumer.CommandWithDestinationBuilder.send;

@Service
public class OrderServiceProxy {

   /* public final CommandEndpoint<RejectOrderCommand> reject= CommandEndpointBuilder
            .forCommand(RejectOrderCommand.class)
            .withChannel(OrderServiceChannel.orderServiceChannel)
            .withReply(Success.class)
            .build();
    public final CommandEndpoint<ApproveOrderCommand> approve = CommandEndpointBuilder
            .forCommand(ApproveOrderCommand.class)
            .withChannel(OrderServiceChannel.orderServiceChannel)
            .withReply(Success.class)
            .build();
*/
   public CommandWithDestination reject(CreateOrderSagaState data) {
       return send(new RejectOrderCommand(data.getOrderId()))
               .to(OrderServiceChannel.orderServiceChannel)
               .build();
   }

    private CommandWithDestination approve(CreateOrderSagaState data) {
        return send(new ApproveOrderCommand(data.getOrderId()))
                .to(OrderServiceChannel.orderServiceChannel)
                .build();
    }

}
