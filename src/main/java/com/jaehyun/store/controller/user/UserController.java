package com.jaehyun.store.controller.user;

import com.jaehyun.store.model.dto.UserDto;
import com.jaehyun.store.service.UserService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "회원 컨트롤러")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody UserDto userDto) {
        userService.join(userDto);
        return ResponseEntity.ok("join successfully");
    }

    @PostMapping("/login")
    public String login(@RequestBody UserDto userDto) {
        return "Bearer " + userService.login(userDto);
    }
}
