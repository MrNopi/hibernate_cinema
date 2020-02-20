package mate.academy.controller;

import mate.academy.exception.AuthenticationException;
import mate.academy.service.AuthenticationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;
    private static final Logger LOGGER = Logger.getLogger(AuthenticationController.class);

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password) {
        try {
            authenticationService.login(email, password);
        } catch (AuthenticationException e) {
            LOGGER.error("Unable to login ", e);
            return "Something went wrong, check your email and password and try again";
        }
        return "Success";
    }

    @PostMapping("/registration")
    public String register(@RequestParam String email, @RequestParam String password) {
        authenticationService.register(email, password);
        return "Success";
    }
}
