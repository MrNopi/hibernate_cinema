package mate.academy.service.implementation;

import mate.academy.dao.ShoppingCartDao;
import mate.academy.dao.TicketDao;
import mate.academy.lib.Inject;
import mate.academy.lib.Service;
import mate.academy.model.MovieSession;
import mate.academy.model.ShoppingCart;
import mate.academy.model.Ticket;
import mate.academy.model.User;
import mate.academy.service.ShoppingCartService;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Inject
    private ShoppingCartDao shoppingCartDao;
    @Inject
    private TicketDao ticketDao;

    @Override
    public void addSession(MovieSession movieSession, User user) {
        Ticket ticket = new Ticket();
        ticket.setMovie(movieSession.getMovie());
        ticket.setCinemaHall(movieSession.getCinemaHall());
        ticket.setShowTime(movieSession.getShowTime());
        ticket.setUser(user);
        ticketDao.add(ticket);
        ShoppingCart shoppingCart = getByUser(user);
        update(shoppingCart);
    }

    @Override
    public ShoppingCart getByUser(User user) {
        return shoppingCartDao.getByUser(user);
    }

    @Override
    public void update(ShoppingCart shoppingCart) {
        shoppingCartDao.update(shoppingCart);
    }

    @Override
    public void clear(ShoppingCart cart) {
        cart.getTickets().clear();
        update(cart);
    }

    @Override
    public ShoppingCart registerNewShoppingCart(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        return shoppingCartDao.add(shoppingCart);
    }
}