package it.uniroma3.web;

import lombok.Data;

import java.util.List;

@Data
public class CreateOrderRequest {

    private Long consumerId;
    private List<LineItem> lineItems;


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
}