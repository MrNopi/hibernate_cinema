package mate.academy.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import mate.academy.model.Order;
import mate.academy.model.Ticket;
import mate.academy.model.User;
import mate.academy.model.dto.TicketDto;
import mate.academy.model.dto.request.OrderRequestDto;
import mate.academy.model.dto.response.OrderResponseDto;
import mate.academy.service.CinemaHallService;
import mate.academy.service.MovieService;
import mate.academy.service.OrderService;
import mate.academy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private MovieService movieService;
    @Autowired
    private CinemaHallService cinemaHallService;
    @Autowired
    private UserService userService;

    @PostMapping("/complete")
    public String completeOrder(@RequestBody OrderRequestDto orderRequestDto) {
        Order order = convertFromOrderDto(orderRequestDto);
        orderService.completeOrder(order.getTickets(), order.getUser());
        return "Success";
    }

    @GetMapping
    public List<OrderResponseDto> getOrderHistory(Long userId) {
        User user = userService.findById(userId);
        return orderService.getOrderHistory(user)
                .stream()
                .map(this::convertToOrderDto)
                .collect(Collectors.toList());
    }

    private Order convertFromOrderDto(OrderRequestDto orderRequestDto) {
        Order order = new Order();
        order.setTickets(orderRequestDto.getTickets()
                .stream()
                .map(this::convertFromTicketDto)
                .collect(Collectors.toList()));
        order.setOrderDate(LocalDateTime.now());
        order.setUser(userService.findById(orderRequestDto.getUserId()));
        return order;
    }

    private OrderResponseDto convertToOrderDto(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setOrderTime(order.getOrderDate());
        orderResponseDto.setTickets(order.getTickets()
        .stream()
        .map(this::convertToTicketDto)
        .collect(Collectors.toList()));
        orderResponseDto.setUserId(order.getUser().getId());
        return orderResponseDto;
    }

    private Ticket convertFromTicketDto(TicketDto ticketDto) {
        Ticket ticket = new Ticket();
        ticket.setMovie(movieService.getMovieById(ticketDto.getMovieId()));
        ticket.setCinemaHall(cinemaHallService.getById(ticketDto.getCinemaHallId()));
        ticket.setShowTime(LocalDateTime.parse(ticketDto.getShowTime()));
        return ticket;
    }

    private TicketDto convertToTicketDto(Ticket ticket) {
        TicketDto ticketDto = new TicketDto();
        ticketDto.setMovieId(ticket.getMovie().getMovieId());
        ticketDto.setCinemaHallId(ticket.getCinemaHall().getId());
        ticketDto.setShowTime(ticket.getShowTime().toString());
        return ticketDto;
    }
}
