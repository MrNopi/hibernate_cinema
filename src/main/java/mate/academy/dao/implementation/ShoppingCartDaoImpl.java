package mate.academy.dao.implementation;

import mate.academy.dao.ShoppingCartDao;
import mate.academy.model.ShoppingCart;
import mate.academy.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class ShoppingCartDaoImpl implements ShoppingCartDao {
    SessionFactory sessionFactory;

    @Override
    public ShoppingCart add(ShoppingCart shoppingCart) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Long id = (Long) session.save(shoppingCart);
            transaction.commit();
            shoppingCart.setId(id);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Unable to create shopping cart", e);
        }
        return shoppingCart;
    }

    @Override
    public ShoppingCart getByUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            Long userId = user.getId();
            Query<ShoppingCart> query = session.createQuery("SELECT cart FROM shopping_cart cart "
                    + "join fetch cart.tickets WHERE cart.id = :id", ShoppingCart.class);
            query.setParameter("id", userId);
            return query.uniqueResult();
        }
    }

    @Override
    public ShoppingCart getByUserId(Long userId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(ShoppingCart.class, userId);
        }
    }

    @Override
    public void update(ShoppingCart shoppingCart) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(shoppingCart);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Unable to update shopping cart", e);
        }
    }
}
