package mate.academy.controller;

import javax.swing.text.DateFormatter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import javafx.util.converter.LocalDateStringConverter;
import javafx.util.converter.LocalDateTimeStringConverter;
import mate.academy.dao.CinemaHallDao;
import mate.academy.model.MovieSession;
import mate.academy.model.dto.MovieSessionDto;
import mate.academy.service.MovieService;
import mate.academy.service.MovieSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie-session")
public class MovieSessionController {
    @Autowired
    private MovieSessionService movieSessionService;
    @Autowired
    private MovieService movieService;
    @Autowired
    private CinemaHallDao cinemaHallDao;

    @PostMapping("/")
    public String add(@RequestBody MovieSessionDto movieSessionDto) {
        movieSessionService.add(convertFromDto(movieSessionDto));
        return "Success";
    }

    @GetMapping("/sessions")
    public List<MovieSessionDto> getAllSessions(@RequestParam Long movieId,
                                                @RequestParam String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M.dd.yyyy");
            LocalDate localDate = LocalDate.parse(date, formatter);
        return movieSessionService.findAvailableSessions(movieId, localDate)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private MovieSessionDto convertToDto(MovieSession movieSession) {
        MovieSessionDto movieSessionDto = new MovieSessionDto();
        movieSessionDto.setCinemaHallId(movieSession.getCinemaHall().getId());
        movieSessionDto.setMovieId(movieSession.getMovie().getMovieId());
        movieSessionDto.setShowTime(movieSession.getShowTime().toString());
        return movieSessionDto;
    }

    private MovieSession convertFromDto(MovieSessionDto dto) {
        MovieSession movieSession = new MovieSession();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M.dd.yyyy HH:mm");
        movieSession.setShowTime(LocalDateTime.parse(dto.getShowTime(), formatter));
        movieSession.setMovie(movieService.getMovieById(dto.getMovieId()));
        movieSession.setCinemaHall(cinemaHallDao.getById(dto.getCinemaHallId()));
        return movieSession;
    }
}
