package mate.academy.service;

import mate.academy.model.MovieSession;
import mate.academy.model.ShoppingCart;
import mate.academy.model.User;

public interface ShoppingCartService {

    void addSession(MovieSession movieSession, User user);

    ShoppingCart getByUser(User user);

    ShoppingCart getByUserId(Long userId);

    void update(ShoppingCart shoppingCart);

    void clear(ShoppingCart shoppingCart);

    ShoppingCart registerNewShoppingCart(User user);
}
