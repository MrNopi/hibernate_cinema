package mate.academy.service;

import java.time.LocalDate;
import java.util.List;
import mate.academy.lib.Service;
import mate.academy.model.Movie;
import mate.academy.model.MovieSession;

@Service
public interface MovieSessionService {
    MovieSession add(MovieSession movie);

    List<MovieSession> findAvailableSessions(Long movieId, LocalDate date);
}
