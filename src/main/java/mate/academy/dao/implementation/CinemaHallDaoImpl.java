package mate.academy.dao.implementation;

import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import mate.academy.dao.CinemaHallDao;
import mate.academy.model.CinemaHall;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CinemaHallDaoImpl implements CinemaHallDao {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {

        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Long movieId = (Long) session.save(cinemaHall);
            transaction.commit();
            cinemaHall.setId(movieId);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't insert movie entity", e);
        }
        return cinemaHall;
    }

    @Override
    public List<CinemaHall> getAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaQuery<CinemaHall> criteriaQuery = session.getCriteriaBuilder()
                    .createQuery(CinemaHall.class);
            criteriaQuery.from(CinemaHall.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Unable to get all cinema halls", e);
        }
    }
}
