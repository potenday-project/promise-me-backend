package mvc.promiseme.users.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Schema(description = "회원가입DTO")
public class UserDTO {
    @Schema(description = "닉네임", required = true, type = "String", example = "사용자1")
    private String nickname;
    @Schema(description = "이메일", required = true, type = "String", example = "user@naver.com")
    private String email;
    @Schema(description = "비밀번호", required = true, type = "String", example = "password1")
    private String password;

}
