package mate.academy.service;

import java.time.LocalDate;
import java.util.List;
import mate.academy.model.MovieSession;
import org.springframework.stereotype.Service;

@Service
public interface MovieSessionService {
    MovieSession add(MovieSession movie);

    List<MovieSession> findAvailableSessions(Long movieId, LocalDate date);
}
