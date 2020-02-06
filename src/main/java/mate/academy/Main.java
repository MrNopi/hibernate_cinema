package mate.academy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import mate.academy.exception.AuthenticationException;
import mate.academy.lib.Injector;
import mate.academy.model.CinemaHall;
import mate.academy.model.Movie;
import mate.academy.model.MovieSession;
import mate.academy.model.User;
import mate.academy.service.AuthenticationService;
import mate.academy.service.CinemaHallService;
import mate.academy.service.MovieService;
import mate.academy.service.MovieSessionService;
import mate.academy.service.UserService;

public class Main {
    private static Injector injector = Injector.getInstance("mate.academy");
    public static void main(String[] args) {
        LocalDateTime time = LocalDateTime.now();

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
        movieSession.setShowTime(time);
        movieSessionService.add(movieSession);

        MovieSession movieSession2 = new MovieSession();
        movieSession2.setCinemaHall(cinemaHall2);
        movieSession2.setMovie(movie2);
        movieSession2.setShowTime(time);
        movieSessionService.add(movieSession2);

        // User test
        UserService userService = (UserService) injector.getInstance(UserService.class);
        AuthenticationService authenticationService =
                (AuthenticationService) injector.getInstance(AuthenticationService.class);
        User user = new User();

        user.setEmail("test@gmail.com");
        user.setPassword("pass");
            user = authenticationService.register(user.getEmail(), user.getPassword());
        User user2 = null;
        try {
            user2 = authenticationService.login("test@gmail.com", "pass");
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }


        System.out.println(user);
        System.out.println(user2);
        System.out.println(user.equals(user2));
        System.out.println("Movies: \n" + movieService.getAll());
        System.out.println("Halls: \n" + cinemaHallService.getAll());
        System.out.println("Sessions 1: \n" + movieSessionService.findAvailableSessions(movie.getMovieId(), time.toLocalDate()));
        System.out.println("Sessions 2: \n" + movieSessionService.findAvailableSessions(movie2.getMovieId(), time.toLocalDate()));
    }
}
