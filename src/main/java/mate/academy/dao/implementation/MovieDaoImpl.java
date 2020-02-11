package mate.academy.dao.implementation;

import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import mate.academy.dao.MovieDao;
import mate.academy.lib.Dao;
import mate.academy.model.Movie;
import mate.academy.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaQuery<Movie> criteriaQuery = session.getCriteriaBuilder()
                    .createQuery(Movie.class);
            criteriaQuery.from(Movie.class);
            return session.createQuery(criteriaQuery).getResultList();
        }
    }
}
