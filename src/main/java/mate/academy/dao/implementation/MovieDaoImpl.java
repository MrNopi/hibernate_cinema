package mate.academy.dao.implementation;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import mate.academy.dao.MovieDao;
import mate.academy.lib.Dao;
import mate.academy.model.Movie;
import org.hibernate.Session;
import org.hibernate.Transaction;
import mate.academy.util.HibernateUtil;

@Dao
public class MovieDaoImpl implements MovieDao {
    @Override
    public Movie add(Movie movie) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Long movieId = (Long)session.save(movie);
            transaction.commit();
            movie.setMovieId(movieId);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't insert movie entity", e);
        }
        return movie;
    }

    @Override
    public List<Movie> getAll() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaQuery<Movie> criteriaQuery = session.getCriteriaBuilder()
                    .createQuery(Movie.class);
            criteriaQuery.from(Movie.class);
            return session.createQuery(criteriaQuery).getResultList();
        }
    }
}
