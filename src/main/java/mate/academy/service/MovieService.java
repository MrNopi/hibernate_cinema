package mate.academy.service;

import java.util.List;
import mate.academy.model.Movie;
import org.springframework.stereotype.Service;

@Service
public interface MovieService {
    Movie add(Movie movie);

    List<Movie> getAll();
}
