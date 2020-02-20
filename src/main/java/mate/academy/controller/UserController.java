package mate.academy.controller;

import mate.academy.model.User;
import mate.academy.model.dto.request.UserRequestDto;
import mate.academy.model.dto.response.UserResponseDto;
import mate.academy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{email}")
    public UserResponseDto getUserByEmail(@PathVariable String email) {
        User user = userService.findByEmail(email);
        return convertToUserResponseDto(user);
    }

    @PostMapping
    public String add(@RequestBody UserRequestDto userRequestDto) {
        User user = convertFromUserRequestDto(userRequestDto);
        userService.add(user);
        return "Success";
    }

    private UserResponseDto convertToUserResponseDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setEmail(user.getEmail());
        return userResponseDto;
    }

    private User convertFromUserRequestDto(UserRequestDto userRequestDto) {
        User user = new User();
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(userRequestDto.getPassword());
        return user;
    }
}
