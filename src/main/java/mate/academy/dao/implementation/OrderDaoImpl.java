package mate.academy.dao.implementation;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import mate.academy.dao.OrderDao;
import mate.academy.lib.Dao;
import mate.academy.model.Order;
import mate.academy.model.User;
import mate.academy.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class OrderDaoImpl implements OrderDao {
    @Override
    public Order add(Order order) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Long orderId = (Long)session.save(order);
            transaction.commit();
            order.setId(orderId);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't insert order entity", e);
        }
        return order;
    }

    @Override
    public List<Order> getOrderHistory(User user) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            Root<Order> root = criteriaBuilder.createQuery().from(Order.class);
            CriteriaQuery<Order> query = criteriaBuilder.createQuery(Order.class);
            criteriaBuilder.createQuery(Order.class)
                    .select(root)
                    .where(criteriaBuilder.equal(root.get("user_id"), user.getId()));
            return session.createQuery(query).getResultList();
        }
    }
}
