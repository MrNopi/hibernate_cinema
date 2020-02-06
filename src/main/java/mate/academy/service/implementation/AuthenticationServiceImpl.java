package mate.academy.service.implementation;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import mate.academy.dao.UserDao;
import mate.academy.exception.AuthenticationException;
import mate.academy.lib.Inject;
import mate.academy.model.User;
import mate.academy.service.AuthenticationService;

public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    UserDao userDao;
    @Override
    public User login(String email, String password) throws AuthenticationException {
        User user = userDao.findByEmail(email);
        if(user != null) {
            if (!user.getPassword().equals(hashPassword(password, user.getSalt()))) {
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
            SecureRandom secureRandom = new SecureRandom();
            byte[] salt = new byte[16];
            secureRandom.nextBytes(salt);
            user.setSalt(salt);
            user.setPassword(hashPassword(password, salt));
            user = userDao.add(user);
        }
        return user;
    }

    private String hashPassword(String password, byte[] salt) {
        StringBuilder str = new StringBuilder();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
            messageDigest.update(salt);
            byte[] digest = messageDigest.digest(password.getBytes());

            for(byte b : digest) {
                str.append(String.format("%02x", b));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return str.toString();
    }
}
