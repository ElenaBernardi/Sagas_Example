package it.uniroma3;

import io.eventuate.tram.commands.common.Command;

public class RejectOrderCommand implements Command {
    private Long orderId;

    private RejectOrderCommand() {
    }

    public RejectOrderCommand(long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
