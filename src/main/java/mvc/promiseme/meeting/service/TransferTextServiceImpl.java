package mvc.promiseme.meeting.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TransferTextServiceImpl implements TransferTextService{

    private final ClovaSpeech clovaSpeech;

    @Override
    public String transferText(String uploadURL) {
        String secretKey = clovaSpeech.getSecretKey();
        String invokeURL = clovaSpeech.getInvokeURL();
        String requestURI = invokeURL + "/recognizer/object-storage";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-CLOVASPEECH-API-KEY", secretKey);

        Map<String, Object> requestPayload = new HashMap<>();
        requestPayload.put("language", "ko-KR");
        requestPayload.put("completion", "sync");
        requestPayload.put("dataKey", uploadURL);

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestPayload, headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(requestURI, requestEntity, String.class);

        String text = null;
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            String responseBody = responseEntity.getBody();
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(responseBody, JsonObject.class);
            text = jsonObject.get("text").getAsString();
            System.out.println("text: " + text);

        } else {
            System.err.println("API 호출 실패: " + responseEntity.getStatusCode());
        }

        return text;
    }
}
