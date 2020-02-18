package mate.academy.service.implementation;

import mate.academy.dao.UserDao;
import mate.academy.exception.AuthenticationException;
import mate.academy.model.User;
import mate.academy.service.AuthenticationService;
import mate.academy.util.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private UserDao userDao;

    @Override
    public User login(String email, String password) throws AuthenticationException {
        User user = userDao.findByEmail(email);
        if (user != null) {
            if (!user.getPassword().equals(HashUtil.hashPassword(password, user.getSalt()))) {
                throw new AuthenticationException("Incorrect login or password");
            }
        }
        return user;
    }

    @Override
    public User register(String email, String password) {
        User user = new User();
        if (userDao.findByEmail(email) == null) {
            user.setEmail(email);
            byte[] salt = HashUtil.generateSalt();
            user.setSalt(salt);
            user.setPassword(HashUtil.hashPassword(password, salt));
            user = userDao.add(user);
        }
        return user;
    }
}
