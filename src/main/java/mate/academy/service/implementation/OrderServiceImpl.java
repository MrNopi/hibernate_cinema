package mate.academy.service.implementation;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import mate.academy.dao.OrderDao;
import mate.academy.lib.Inject;
import mate.academy.lib.Service;
import mate.academy.model.Order;
import mate.academy.model.Ticket;
import mate.academy.model.User;
import mate.academy.service.OrderService;
import mate.academy.service.ShoppingCartService;
import mate.academy.util.HibernateUtil;
import org.hibernate.Session;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    OrderDao orderDao;
    @Inject
    ShoppingCartService shoppingCartDao;
    @Override
    public Order completeOrder(List<Ticket> tickets, User user) {
        Order order = new Order();
        order.setTickets(tickets);
        order.setUser(user);
        order = orderDao.add(order);
        shoppingCartDao.clear(shoppingCartDao.getByUser(user));
        return order;
    }

    @Override
    public List<Order> getOrderHistory(User user) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {

        }
        return null;
    }
}
