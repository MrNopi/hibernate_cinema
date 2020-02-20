package mate.academy.controller;

import java.time.LocalDateTime;
import java.util.stream.Collectors;
import mate.academy.model.MovieSession;
import mate.academy.model.ShoppingCart;
import mate.academy.model.Ticket;
import mate.academy.model.dto.MovieSessionDto;
import mate.academy.model.dto.ShoppingCartDto;
import mate.academy.model.dto.TicketDto;
import mate.academy.service.CinemaHallService;
import mate.academy.service.MovieService;
import mate.academy.service.MovieSessionService;
import mate.academy.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shopping-carts")
public class ShoppingCartController {
    @Autowired
    private MovieSessionService movieSessionService;
    @Autowired
    private CinemaHallService cinemaHallService;
    @Autowired
    private MovieService movieService;
    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping("/add-movie-session")
    public String addMovieSession(@RequestBody MovieSessionDto movieSessionDto,
                                  @RequestParam Long userId) {
        MovieSession movieSession = new MovieSession();
        movieSession.setCinemaHall(cinemaHallService.getById(movieSessionDto.getCinemaHallId()));
        movieSession.setMovie(movieService.getMovieById(movieSessionDto.getMovieId()));
        movieSession.setShowTime(LocalDateTime.parse(movieSessionDto.getShowTime()));
        movieSessionService.add(movieSession);
        return "Success";
    }

    @PostMapping("by-user/{userId}")
    public ShoppingCartDto getByUserId(@PathVariable Long userId) {
        ShoppingCart cart = shoppingCartService.getByUserId(userId);
        ShoppingCartDto shoppingCartDto = new ShoppingCartDto();
        shoppingCartDto.setTickets(cart.getTickets()
                .stream()
                .map(this::convertObjectToDto)
                .collect(Collectors.toList()));
        return shoppingCartDto;
    }

    private TicketDto convertObjectToDto(Ticket ticket) {
        TicketDto ticketDto = new TicketDto();
        ticketDto.setCinemaHallId(ticket.getCinemaHall().getId());
        ticketDto.setMovieId(ticket.getMovie().getMovieId());
        ticketDto.setShowTime(ticket.getShowTime().toString());
        return ticketDto;
    }
}
