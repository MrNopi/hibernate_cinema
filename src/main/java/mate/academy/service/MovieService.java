package mate.academy.service;

import java.util.List;
import mate.academy.lib.Service;
import mate.academy.model.Movie;

@Service
public interface MovieService {
    Movie add(Movie movie);

    List<Movie> getAll();
}
