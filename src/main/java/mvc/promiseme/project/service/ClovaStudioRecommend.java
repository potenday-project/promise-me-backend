package mvc.promiseme.project.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClovaStudioRecommend {

    @Value("${RECOMMEND_MEMBER_URL}")
    private String url;
    @Value("${RECOMMEND_API_KEY}")
    private String apiKeyClovaStudio;
    @Value("${RECOMMEND_API_GATE_WAY_KEY}")
    private String apiGateWayKey;
    @Value("${RECOMMEND_MEMBER_REQUEST_ID}")
    private String requestMemberId;
}