package mvc.promiseme.users.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class LoginResponseDTO {
    private String userId;
    private String nickname;
    private String token;
}
