package mvc.promiseme.users.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class KakaoResponseDTO {
    private String kakaoApiKey;
    private String redirectUri;
//    private String token;
}
