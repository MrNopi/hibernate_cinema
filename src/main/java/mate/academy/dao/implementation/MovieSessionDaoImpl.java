package mate.academy.dao.implementation;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<MovieSession> criteriaQuery = builder
                    .createQuery(MovieSession.class);
            Root<MovieSession> root = criteriaQuery
                    .from(MovieSession.class);
            criteriaQuery
                    .select(root)
                    .where(builder.greaterThanOrEqualTo(root
                            .get("showTime").as(LocalDate.class), date));
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Unable to find available session", e);
        }
    }
}
