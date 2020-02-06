package mate.academy.service;

import java.util.List;
import mate.academy.lib.Service;
import mate.academy.model.CinemaHall;

@Service
public interface CinemaHallService {
    CinemaHall add(CinemaHall movie);

    List<CinemaHall> getAll();
}
