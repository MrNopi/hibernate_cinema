package mate.academy;

import java.time.LocalDateTime;
import mate.academy.lib.Injector;
import mate.academy.model.CinemaHall;
import mate.academy.model.Movie;
import mate.academy.model.MovieSession;
import mate.academy.service.CinemaHallService;
import mate.academy.service.MovieService;
import mate.academy.service.MovieSessionService;

public class Main {
    private static Injector injector = Injector.getInstance("mate.academy");
    public static void main(String[] args) {
        // Movie test
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);

        Movie movie = new Movie();
        movie.setTitle("Fast and furious");
        movieService.add(movie);

        Movie movie2 = new Movie();
        movie2.setTitle("Red dead redemption");
        movieService.add(movie2);

        //Cinema hall test
        CinemaHallService cinemaHallService = (CinemaHallService) injector.getInstance(CinemaHallService.class);
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(100);
        cinemaHall.setDescription("Red hall");
        cinemaHallService.add(cinemaHall);

        CinemaHall cinemaHall2 = new CinemaHall();
        cinemaHall2.setCapacity(200);
        cinemaHall2.setDescription("Green hall");
        cinemaHallService.add(cinemaHall2);

        // Movie session test
        MovieSessionService movieSessionService = (MovieSessionService) injector.getInstance(MovieSessionService.class);
        MovieSession movieSession = new MovieSession();
        movieSession.setCinemaHall(cinemaHall);
        movieSession.setMovie(movie);
        movieSession.setShowTime(LocalDateTime.now());
        movieSessionService.add(movieSession);

        MovieSession movieSession2 = new MovieSession();
        movieSession2.setCinemaHall(cinemaHall2);
        movieSession2.setMovie(movie2);
        movieSession2.setShowTime(LocalDateTime.now());
        movieSessionService.add(movieSession2);

        System.out.println("Movies: \n" + movieService.getAll());
        System.out.println("Halls: \n" + cinemaHallService.getAll());
        System.out.println("Sessions: \n" + movieSessionService.getAll());
    }
}
