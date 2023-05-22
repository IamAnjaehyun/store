package com.jaehyun.store.controller.user;

import com.jaehyun.store.model.dto.UserDto;
import com.jaehyun.store.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public Long join(@RequestBody UserDto userDto) {
        return userService.join(userDto);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserDto userDto) {
        return userService.login(userDto);
    }
}
