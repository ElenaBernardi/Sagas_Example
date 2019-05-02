package it.uniroma3.sagas;

import io.eventuate.tram.commands.consumer.CommandWithDestination;
import io.eventuate.tram.sagas.orchestration.SagaDefinition;
import io.eventuate.tram.sagas.simpledsl.SimpleSaga;

import it.uniroma3.*;
import it.uniroma3.sagaparticipants.ConsumerServiceProxy;
import org.springframework.stereotype.Component;
import it.uniroma3.sagaparticipants.OrderServiceProxy;

import static io.eventuate.tram.commands.consumer.CommandWithDestinationBuilder.send;

@Component
public class CreateOrderSaga implements SimpleSaga<CreateOrderSagaState> {

    private SagaDefinition<CreateOrderSagaState> sagaDefinition;

    public CreateOrderSaga(OrderServiceProxy orderService, ConsumerServiceProxy consumerService) {
        System.out.println("######### ENTRATO #########");
        this.sagaDefinition =
                step()
                        .withCompensation(this::reject)
                        .step()
                        .invokeParticipant(this::validateOrder)
                        .step()
                        .invokeParticipant(this::approve)
                        .build();

                        /*
                        .step()
                        .invokeParticipant(kitchenService.create, CreateOrderSagaState::makeCreateTicketCommand)
                        .onReply(CreateTicketReply.class, CreateOrderSagaState::handleCreateTicketReply)
                        .withCompensation(kitchenService.cancel, CreateOrderSagaState::makeCancelTicketCommand)
                        .step()
                        .invokeParticipant(kitchenService.confirmCreate, CreateOrderSagaState::makeConfirmCreateTicketCommand)
                        .step()
                        .invokeParticipant(orderService.approve, CreateOrderSagaState::makeApproveOrderCommand)
                        .build();*/

    }

    public CommandWithDestination reject(CreateOrderSagaState data) {
        System.out.println("############## Invio evento Reject");
        return send(new RejectOrderCommand(data.getOrderId()))
                .to(OrderServiceChannel.orderServiceChannel)
                .build();
    }

    private CommandWithDestination approve(CreateOrderSagaState data) {
        System.out.println("invio evento approvato");
        return send(new ApproveOrderCommand(data.getOrderId()))
                .to(OrderServiceChannel.orderServiceChannel)
                .build();
    }
    private CommandWithDestination validateOrder(CreateOrderSagaState data) {
        long orderId = data.getOrderId();
        Long customerId = data.getOrderDetails().getConsumerId();
        return send(new ValidateOrderByConsumer(customerId, orderId))
                .to(ConsumerServiceChannel.consumerServiceChannel)
                .build();
    }
    @Override
    public SagaDefinition<CreateOrderSagaState> getSagaDefinition() {
        return sagaDefinition;
    }

}
