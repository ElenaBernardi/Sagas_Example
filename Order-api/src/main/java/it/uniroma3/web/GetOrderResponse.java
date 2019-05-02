package it.uniroma3.web;


import java.util.List;

public class GetOrderResponse {

    private Long orderId;
    private Long consumerId;
    private List<LineItem> lineItems;
    private String orderState;


    public GetOrderResponse() {
    }

    public GetOrderResponse(Long orderId, Long consumerId,List<LineItem> lineItems, String orderState) {
        this.orderId = orderId;
        this.consumerId = consumerId;
        this.lineItems = lineItems;
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

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }
}
