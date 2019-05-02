package it.uniroma3.web;


public class CreateOrderResponse {

    private Long orderId;
    private Long consumerId;
    private String orderState;

    public CreateOrderResponse() {
    }

    public CreateOrderResponse(Long orderId, Long consumerId, String orderState) {
        this.orderId = orderId;
        this.consumerId = consumerId;
        this.orderState = orderState;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(Long consumerId) {
        this.consumerId = consumerId;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }
}