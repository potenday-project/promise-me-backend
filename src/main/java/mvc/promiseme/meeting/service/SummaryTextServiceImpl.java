package mvc.promiseme.meeting.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import mvc.promiseme.common.NaverKey;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SummaryTextServiceImpl implements SummaryTextService{

    private final ClovaStudioSummary clovaStudioSummary;

    @Override
    public String summary(String text) {

        String url = clovaStudioSummary.getUrl();
        String apiKeyClovaStudio = clovaStudioSummary.getApiKeyClovaStudio();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-NCP-CLOVASTUDIO-API-KEY", apiKeyClovaStudio);
        headers.set("X-NCP-APIGW-API-KEY", clovaStudioSummary.getApiKey());
        headers.set("X-NCP-CLOVASTUDIO-REQUEST-ID", clovaStudioSummary.getRequestId());
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("texts", Arrays.asList(text));
        requestBody.put("segMinSize", 300);
        requestBody.put("includeAiFilters", true);
        requestBody.put("maxTokens", 256);
        requestBody.put("autoSentenceSplitter", true);
        requestBody.put("segCount", -1);
        requestBody.put("segMaxSize", 1000);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        String resultText = null;
        JsonObject jsonObject = JsonParser.parseString(responseEntity.getBody()).getAsJsonObject();
        resultText = jsonObject.getAsJsonObject("result").get("text").getAsString();
        System.out.println(resultText);

        return resultText;
    }
}
