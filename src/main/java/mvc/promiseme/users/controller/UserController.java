package mvc.promiseme.users.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mvc.promiseme.common.exception.ErrorCode;
import mvc.promiseme.common.exception.ErrorResponse;
import mvc.promiseme.project.dto.ProjectResponseDTO;
import mvc.promiseme.users.dto.LoginRequestDTO;
import mvc.promiseme.users.dto.LoginResponseDTO;
import mvc.promiseme.users.dto.UserDTO;
import mvc.promiseme.users.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@Tag(name = "User", description = "회원관리 API")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(summary = "회원가입", description = "닉네임과 이메일, 패스워드를 입력받아 회원가입을 진행한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "401", description = "중복된 이메일입니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))

    })
    @PostMapping("/users/register")
    public ResponseEntity<String>register( @RequestBody  UserDTO userdto){
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

    @Operation(summary = "유저 조회", description = "닉네임과 이메일, 패스워드를 입력받아 회원가입을 진행한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "401", description = "중복된 이메일입니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))

    })
    @PostMapping("/users/check")
    public ResponseEntity<Long>searchAll(@RequestBody Map<String, String> requestBody){
        return ResponseEntity.ok(userService.check(requestBody.get("email")));
    }


}
