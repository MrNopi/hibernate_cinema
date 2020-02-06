package mate.academy.dao;

import java.time.LocalDate;
import java.util.List;
import mate.academy.model.MovieSession;

public interface MovieSessionDao {
    MovieSession add(MovieSession movieSession);

    List<MovieSession> findAvailableSessions(Long movieId, LocalDate date);
}
