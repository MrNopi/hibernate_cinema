package mate.academy.dao.implementation;

import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import mate.academy.dao.MovieDao;
import mate.academy.model.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MovieDaoImpl implements MovieDao {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Movie add(Movie movie) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
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
        try (Session session = sessionFactory.openSession()) {
            CriteriaQuery<Movie> criteriaQuery = session.getCriteriaBuilder()
                    .createQuery(Movie.class);
            criteriaQuery.from(Movie.class);
            return session.createQuery(criteriaQuery).getResultList();
        }
    }

    @Override
    public Movie getById(Long movieId) {
        try(Session session = sessionFactory.openSession()) {
            return session.get(Movie.class, movieId);
        }
    }
}
