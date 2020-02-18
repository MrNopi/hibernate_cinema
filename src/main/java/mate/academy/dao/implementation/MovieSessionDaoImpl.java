package mate.academy.dao.implementation;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import mate.academy.dao.MovieSessionDao;
import mate.academy.model.MovieSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MovieSessionDaoImpl implements MovieSessionDao {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public MovieSession add(MovieSession movieSession) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
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
        try (Session session = sessionFactory.openSession()) {
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
