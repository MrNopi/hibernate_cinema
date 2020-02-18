package mate.academy.service.implementation;

import java.util.List;
import mate.academy.dao.CinemaHallDao;
import mate.academy.model.CinemaHall;
import mate.academy.service.CinemaHallService;
import org.springframework.beans.factory.annotation.Autowired;

public class CinemaHallServiceImpl implements CinemaHallService {
    @Autowired
    private CinemaHallDao cinemaHallDao;

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        return cinemaHallDao.add(cinemaHall);
    }

    @Override
    public List<CinemaHall> getAll() {
        return cinemaHallDao.getAll();
    }
}
