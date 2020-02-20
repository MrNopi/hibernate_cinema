package mate.academy.service;

import java.util.List;
import mate.academy.model.CinemaHall;
import org.springframework.stereotype.Service;

@Service
public interface CinemaHallService {
    CinemaHall add(CinemaHall movie);

    List<CinemaHall> getAll();

    CinemaHall getById(Long cinemaHallId);
}
