package com.jaehyun.store.controller.user;

import com.jaehyun.store.model.dto.UserDto;
import com.jaehyun.store.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "회원 가입",notes = "아이디(휴대폰 번호), 비밀번호를 통해 회원가입 요청을 보냅니다.")
    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody UserDto userDto) {
        userService.join(userDto);
        return ResponseEntity.ok("join successfully");
    }

    @ApiOperation(value = "로그인",notes = "jwt 토큰을 사용하여 로그인시 토큰을 부여합니다.")
    @PostMapping("/login")
    public String login(@RequestBody UserDto userDto) {
        return "Bearer " + userService.login(userDto);
    }
}
