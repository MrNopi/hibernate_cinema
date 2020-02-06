package mate.academy.dao.implementation;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import mate.academy.dao.CinemaHallDao;
import mate.academy.lib.Dao;
import mate.academy.model.CinemaHall;
import mate.academy.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class CinemaHallDaoImpl implements CinemaHallDao {
    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
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
        //Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaQuery<CinemaHall> criteriaQuery = session.getCriteriaBuilder()
                    .createQuery(CinemaHall.class);
            criteriaQuery.from(CinemaHall.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
          //  if (transaction != null) {
//                transaction.rollback();
//            }
            throw new RuntimeException("Unable to get all cinema halls", e);
        }
    }
}
