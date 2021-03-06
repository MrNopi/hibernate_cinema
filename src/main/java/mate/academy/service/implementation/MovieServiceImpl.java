package mate.academy.service.implementation;

import java.util.List;
import mate.academy.dao.MovieDao;
import mate.academy.model.Movie;
import mate.academy.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieDao movieDao;

    @Override
    public Movie add(Movie movie) {
        return movieDao.add(movie);
    }

    @Override
    public List<Movie> getAll() {
        return movieDao.getAll();
    }

    @Override
    public Movie getMovieById(Long movieId) {
        return movieDao.getById(movieId);
    }
}
