package mate.academy;

import java.time.LocalDateTime;
import java.util.List;
import mate.academy.dao.ShoppingCartDao;
import mate.academy.dao.TicketDao;
import mate.academy.exception.AuthenticationException;
import mate.academy.lib.Injector;
import mate.academy.model.CinemaHall;
import mate.academy.model.Movie;
import mate.academy.model.MovieSession;
import mate.academy.model.ShoppingCart;
import mate.academy.model.Ticket;
import mate.academy.model.User;
import mate.academy.service.AuthenticationService;
import mate.academy.service.CinemaHallService;
import mate.academy.service.MovieService;
import mate.academy.service.MovieSessionService;
import mate.academy.service.OrderService;
import mate.academy.service.ShoppingCartService;

public class Main {
    private static Injector injector = Injector.getInstance("mate.academy");

    public static void main(String[] args) {

        // Movie test
        MovieService movieService = (MovieService)
                injector.getInstance(MovieService.class);

        Movie movie = new Movie();
        movie.setTitle("Fast and furious");
        movieService.add(movie);

        Movie movie2 = new Movie();
        movie2.setTitle("Red dead redemption");
        movieService.add(movie2);

        //Cinema hall test
        CinemaHallService cinemaHallService = (CinemaHallService)
                injector.getInstance(CinemaHallService.class);
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(100);
        cinemaHall.setDescription("Red hall");
        cinemaHallService.add(cinemaHall);

        CinemaHall cinemaHall2 = new CinemaHall();
        cinemaHall2.setCapacity(200);
        cinemaHall2.setDescription("Green hall");
        cinemaHallService.add(cinemaHall2);

        // Movie session test
        MovieSession movieSession = new MovieSession();
        movieSession.setCinemaHall(cinemaHall);
        movieSession.setMovie(movie);
        movieSession.setShowTime(LocalDateTime.now());
        MovieSessionService movieSessionService = (MovieSessionService)
                injector.getInstance(MovieSessionService.class);
        movieSessionService.add(movieSession);

        MovieSession movieSession2 = new MovieSession();
        movieSession2.setCinemaHall(cinemaHall2);
        movieSession2.setMovie(movie2);
        movieSession2.setShowTime(LocalDateTime.now());
        movieSessionService.add(movieSession2);

        // User test

        User user = new User();

        user.setEmail("test@gmail.com");
        user.setPassword("pass");
        AuthenticationService authenticationService = (AuthenticationService)
                injector.getInstance(AuthenticationService.class);
        user = authenticationService.register(user.getEmail(), user.getPassword());
        User user2 = null;
        try {
            user2 = authenticationService.login("test@gmail.com", "pass");
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }

        // Shopping cart test
        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setShowTime(movieSession.getShowTime());
        ticket.setCinemaHall(cinemaHall);
        ticket.setMovie(movie);
        TicketDao ticketDao = (TicketDao)
                injector.getInstance(TicketDao.class);
        ticket = ticketDao.add(ticket);

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addTicket(ticket);
        shoppingCart.setUser(user);
        ShoppingCartDao shoppingCartDao = (ShoppingCartDao)
                injector.getInstance(ShoppingCartDao.class);
        shoppingCartDao.add(shoppingCart);
        OrderService orderService = (OrderService)
                injector.getInstance(OrderService.class);
        ShoppingCartService shoppingCartService = (ShoppingCartService)
                injector.getInstance(ShoppingCartService.class);
        List<Ticket> tickets = shoppingCartService.getByUser(user).getTickets();
        orderService.completeOrder(tickets, user);
    }
}
