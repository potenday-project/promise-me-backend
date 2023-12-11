package mvc.promiseme.users.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserDTO {
    private String nickname;
    private String email;
    private String password;

}
