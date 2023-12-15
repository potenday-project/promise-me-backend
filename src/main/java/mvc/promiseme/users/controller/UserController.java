package mvc.promiseme.users.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mvc.promiseme.users.dto.LoginRequestDTO;
import mvc.promiseme.users.dto.LoginResponseDTO;
import mvc.promiseme.users.dto.UserDTO;
import mvc.promiseme.users.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor

public class UserController {
    private final UserService userService;

    @PostMapping("/users/register")
    public ResponseEntity<String>register(@RequestBody UserDTO userdto){
        if(userService.register(userdto).equals("success"))
            return ResponseEntity.ok("회원가입이 완료되었습니다.");
        else {
            return ResponseEntity.ok(userService.register(userdto));
        }
    }

    @PostMapping("/users/login")
    public ResponseEntity<LoginResponseDTO>login(@RequestBody LoginRequestDTO loginRequestDTO){
        return ResponseEntity.ok(userService.login(loginRequestDTO));
    }

    @PostMapping("/users/logout")
    public ResponseEntity<String>logout(@RequestBody String token){
        return ResponseEntity.ok(userService.logout(token));
    }

    @PostMapping("/users/check")
    public ResponseEntity<Long>check(@RequestBody String email){
        return ResponseEntity.ok(userService.check(email));
    }


}
