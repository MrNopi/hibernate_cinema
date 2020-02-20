package mate.academy.dao;

import mate.academy.model.ShoppingCart;
import mate.academy.model.User;

public interface ShoppingCartDao {
    ShoppingCart add(ShoppingCart shoppingCart);

    ShoppingCart getByUser(User user);

    ShoppingCart getByUserId(Long userId);

    void update(ShoppingCart shoppingCart);
}
