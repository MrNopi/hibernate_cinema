package mate.academy.service.implementation;

import java.time.LocalDate;
import java.util.List;
import mate.academy.dao.MovieSessionDao;
import mate.academy.model.MovieSession;
import mate.academy.service.MovieSessionService;
import org.springframework.beans.factory.annotation.Autowired;

public class MovieSessionServiceImpl implements MovieSessionService {
    @Autowired
    private MovieSessionDao movieSessionDao;

    @Override
    public MovieSession add(MovieSession movieSession) {
        return movieSessionDao.add(movieSession);
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        return movieSessionDao.findAvailableSessions(movieId, date);
    }
}
