package it.uniroma3.domain;

import io.eventuate.tram.sagas.orchestration.SagaManager;
import it.uniroma3.event.LineItem;
import it.uniroma3.event.OrderCreatedEvent;
import it.uniroma3.event.OrderDetails;
import it.uniroma3.sagas.CreateOrderSagaState;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService implements IOrderService {
    //@Autowired
    //CreateOrderSaga saga ;
    @Autowired
    private SagaManager<CreateOrderSagaState>  createOrderSagaManager;
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> findAll() {
        return (List<Order>) orderRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    /* Creazione di un nuovo ordine. */
    @Override
    public Order create(Long consumerId, List<OrderLineItem> orderLineItems) {
        //return createAsincrona(consumerId, restaurantId, orderLineItems);
        // return createSincrona(consumerId, restaurantId, orderLineItems);
        return createSaga(consumerId, orderLineItems);
    }
    private Order createSaga(Long consumerId, List<OrderLineItem> orderLineItems) {
        //crea e salva l'ordine
        Order order = Order.create(consumerId, orderLineItems);
        order = orderRepository.save(order);

        List<LineItem> lineItems = makeLineItem(order);
        OrderDetails orderDetails = new OrderDetails(lineItems, consumerId);
        CreateOrderSagaState data = new CreateOrderSagaState(order.getId(), orderDetails);
        //TODO togli commento
        System.out.println("##############prima del create");
        createOrderSagaManager.create(data, Order.class, order.getId());
        System.out.println("##############dopo del create");
        return order;
    }

    private List<LineItem> makeLineItem(Order order){
        List<LineItem> lineItems = order.getOrderLineItems()
                .stream()
                .map(x -> new LineItem(x.getMenuItemId(), x.getQuantity()))
                .collect(Collectors.toList());
        return lineItems;
    }
    private OrderCreatedEvent makeOrderCreatedEvent(Order order){
        List<LineItem> lineItems = order.getOrderLineItems()
                .stream()
                .map(x -> new LineItem(x.getMenuItemId(), x.getQuantity()))
                .collect(Collectors.toList());
        return new OrderCreatedEvent(order.getId(),order.getConsumerId(), lineItems);
    }


    public Order invalidateConsumer(Long orderId, Long consumerId) {
        Order order = findById(orderId);
        order.setOrderState(OrderState.INVALID);
        order = orderRepository.save(order);
        return order;
    }

    //SAGAS
    public void approveOrder(Long orderId){
        System.out.println("Approvato");
        Order order = orderRepository.findById(orderId).get();
        order.setOrderState(OrderState.APPROVED);
        orderRepository.save(order);
    }
    public void rejectOrder(Long orderId){
        Order order = orderRepository.findById(orderId).get();
        order.setOrderState(OrderState.INVALID);
        orderRepository.save(order);
    }
}
