package mate.academy.service;

import java.util.List;
import mate.academy.model.Order;
import mate.academy.model.Ticket;
import mate.academy.model.User;

public interface OrderService {
    Order completeOrder(List<Ticket> tickets, User user);

    List<Order> getOrderHistory(User user);
}
