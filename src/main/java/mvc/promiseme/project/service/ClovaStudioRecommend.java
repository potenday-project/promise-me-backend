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

    @Value("${RECOMMEND_URL}")
    private String url;
    @Value("${API_KEY_CLOVA_STUDIO}")
    private String apiKeyClovaStudio;
    @Value("${API_KEY}")
    private String apiKey;
    @Value("${REQUEST_ID}")
    private String requestId;
}