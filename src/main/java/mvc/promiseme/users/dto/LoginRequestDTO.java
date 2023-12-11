package mvc.promiseme.users.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class LoginRequestDTO {
    private String email;
    private String password;
}
