package mate.academy.dao.implementation;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import mate.academy.dao.MovieSessionDao;
import mate.academy.lib.Dao;
import mate.academy.model.MovieSession;
import mate.academy.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class MovieSessionDaoImpl implements MovieSessionDao {
    @Override
    public MovieSession add(MovieSession movieSession) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Long id = (Long) session.save(movieSession);
            transaction.commit();
            movieSession.setId(id);
            return movieSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Unable to create movie session", e);
        }
    }

    @Override
    public List<MovieSession> getAll() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaQuery<MovieSession> criteriaQuery = session.getCriteriaBuilder()
                    .createQuery(MovieSession.class);
            criteriaQuery.from(MovieSession.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Unable to get all movie sessions", e);
        }
    }
}
