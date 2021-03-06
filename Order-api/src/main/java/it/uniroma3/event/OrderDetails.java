package it.uniroma3.event;

import java.util.List;

public class OrderDetails {

    private List<LineItem> lineItems;
    private Long consumerId;

    //constructs

    public OrderDetails(){}

    public OrderDetails(List<LineItem> lineItems, Long consumerId) {
        this.lineItems = lineItems;
        this.consumerId = consumerId;
    }

    //getter and setter

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public Long getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(Long consumerId) {
        this.consumerId = consumerId;
    }
}
