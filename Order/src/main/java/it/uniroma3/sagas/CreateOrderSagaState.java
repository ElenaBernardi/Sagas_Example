package it.uniroma3.sagas;

import it.uniroma3.ValidateOrderByConsumer;
import org.springframework.stereotype.Component;
import it.uniroma3.ApproveOrderCommand;
import it.uniroma3.RejectOrderCommand;
import it.uniroma3.event.OrderDetails;

@Component
public class CreateOrderSagaState {

    private Long orderId;
    private OrderDetails orderDetails;

    public CreateOrderSagaState(){}

    public CreateOrderSagaState(Long orderId, OrderDetails orderDetails) {
        this.orderId = orderId;
        this.orderDetails = orderDetails;
    }

    //commands
    ValidateOrderByConsumer makeValdateOrderByConsumerCommand(){
        return new ValidateOrderByConsumer(orderDetails.getConsumerId(),orderId);
    }

    RejectOrderCommand makeRejectOrderCommand(){
        return new RejectOrderCommand(orderId);
    }

    ApproveOrderCommand makeApproveOrderCommand(){
        return new ApproveOrderCommand(orderId);
    }

    //getter and setter
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public OrderDetails getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(OrderDetails orderDetails) {
        this.orderDetails = orderDetails;
    }

}
