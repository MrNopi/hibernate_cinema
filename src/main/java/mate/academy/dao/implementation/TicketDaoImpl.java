package mate.academy.dao.implementation;

import mate.academy.dao.TicketDao;
import mate.academy.model.Ticket;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TicketDaoImpl implements TicketDao {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Ticket add(Ticket ticket) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Long id = (Long) session.save(ticket);
            transaction.commit();
            ticket.setId(id);
            return ticket;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Unable to add ticket", e);
        }
    }
}
