package mvc.promiseme.meeting.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import mvc.promiseme.project.dto.Message;
import mvc.promiseme.project.service.ClovaStudioRecommend;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@RequiredArgsConstructor
public class SummaryTextServiceImpl implements SummaryTextService{

    private final ClovaStudioSummary clovaStudioSummary;
    private final ClovaStudioRecommend clovaStudioRecommend;
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

    @Override
    public String getTitle(String summary) {
        String url = clovaStudioRecommend.getUrl();
        String apiKeyClovaStudio = clovaStudioRecommend.getApiKeyClovaStudio();

        List<Message> messages = new ArrayList<>();

        // 첫 번째 메시지
        Message systemMessage = new Message("system", "당신은 주어진 텍스트를 한 줄로 요약하여 제목을 잘 지어주는 전문가입니다. ");
        messages.add(systemMessage);

        // 두 번째 메시지
        Message userMessage = new Message("user","나는 아래와 같은 회의록 요약 내용을 가지고 있어. 이 회의록 요약 내용을 보고 회의록 제목을 작성해줘. \n" +
                "\n" +
                "\n" +
                "\n" +
                "- 부서원도 한 명 줄었고 다들 열심히 해보자고 함\n" +
                " - 중대 발표를 해야 할 것 같다고 함\n" +
                " - 회사에서 부서에 떨어진 프로젝트가 치킨임\n" +
                " - 치킨이 굉장히 많고 고생이 많기 때문에 걱정이 됨\n" +
                " - 회의할 때 딴 짓 하지 말고 집중하라고 함\n" +
                "\n" +
                "\n" +
                "회의록 제목 : [제목]\n" +
                "\n" +
                "이 형식으로 작성해줘.\n" +
                "\n" +
                "다른 말 말고 \"제목\" 한개만 작성해줘.");
        messages.add(userMessage);

        // 세 번째 메시지
        Message assistantMessage = new Message("assistant", "부서 회의록 제목 : 새로운 프로젝트 '치킨' 할당 및 부서원 감소에 따른 대책 논의");
        messages.add(assistantMessage);

        Message requestMessage = new Message("user", "나는 아래와 같은 회의록 요약 내용을 가지고 있어. 이 회의록 요약 내용을 보고 회의록 제목을 작성해줘. \n" +
                "\n" +
                "\n" +
                "\n" +
                summary+
                "\n" +
                "\n" +
                "회의록 제목 : [제목]\n" +
                "\n" +
                "이 형식으로 작성해줘.\n" +
                "\n" +
                "다른 말 말고 \"제목\" 한개만 작성해줘.");
        messages.add(requestMessage);

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-NCP-CLOVASTUDIO-API-KEY", apiKeyClovaStudio);
        headers.set("X-NCP-APIGW-API-KEY", clovaStudioRecommend.getApiGateWayKey());
        headers.set("X-NCP-CLOVASTUDIO-REQUEST-ID", clovaStudioRecommend.getCreateTitleId());
        headers.set("Content-Type", "application/json");

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("messages", messages);
        requestBody.put("topP", 0.8);
        requestBody.put("topK", 0);
        requestBody.put("maxTokens", 256);
        requestBody.put("temperature", 0.5);
        requestBody.put("repeatPenalty", 5.0);
        requestBody.put("includeAiFilters", "True");

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        JsonObject jsonObject = JsonParser.parseString(responseEntity.getBody()).getAsJsonObject();
        JsonObject messageObject = jsonObject.getAsJsonObject("result").getAsJsonObject("message");
        String content = messageObject.get("content").getAsString();
        String[] parts = content.split(" : ", 2);

        if (parts.length == 2) {
            String meetingName = parts[1];
            return meetingName;

        } else {
            String meetingName = "제목 형식이 올바르지 않습니다.";
            return meetingName;
        }

    }
}
