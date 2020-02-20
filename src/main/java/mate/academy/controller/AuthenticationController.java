package mate.academy.controller;

import mate.academy.exception.AuthenticationException;
import mate.academy.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;


    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password) {
        try {
            authenticationService.login(email, password);
        } catch (AuthenticationException e) {
            return "Something went wrong, check your email and password and try again";
        }
        return "Success";
    }

    @PostMapping
    public String register(@RequestParam String email, @RequestParam String password) {
        authenticationService.register(email, password);
        return "Success";
    }
}
