package it.uniroma3;

import io.eventuate.tram.commands.common.Command;

public class ApproveOrderCommand implements Command{
    public Long orderId;

    public ApproveOrderCommand(){}
    public ApproveOrderCommand(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
