package mvc.promiseme.project.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import mvc.promiseme.project.dto.Message;
import mvc.promiseme.project.dto.RecommendMemberRequestDTO;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@RequiredArgsConstructor
public class RecommendServiceImpl implements RecommendService{

    private final ClovaStudioRecommend clovaStudioRecommend;
    @Override
    public Map<String, String> recommendMember(RecommendMemberRequestDTO recommendMemberRequestDTO) {
        String url = clovaStudioRecommend.getUrl();
        String apiKeyClovaStudio = clovaStudioRecommend.getApiKeyClovaStudio();


        List<Message> messages = new ArrayList<>();

        // 첫 번째 메시지
        Message systemMessage = new Message("system", "당신은 프로젝트 관리를 잘하는 사람입니다. 당신은 프로젝트 분야와 프로젝트 기간을 알려주면 필요한 구성원을 추천해줄 수 있습니다.");
        messages.add(systemMessage);

        // 두 번째 메시지
        Message userMessage = new Message("user", "나는 마케팅프로젝트를 진행할예정입니다. 시작날짜는 2023-12-07, 마감날짜는 2023-12-31입니다. 이때 필요한 팀의 구성원 역할을 알려주세요. 다른 말 필요없이\n\"역할\" : \"역할이 해야할일\"\n로만 대답해주세요. 다른 말 없이 역할과 역할이 해야할일만 작성해주세요.");
        messages.add(userMessage);

        // 세 번째 메시지
        Message assistantMessage = new Message("assistant", "PM : 프로젝트 전반에 걸쳐 일정, 예산, 인력 등을 관리하며 팀원들 간의 의사소통을 중재합니다.\n디자이너: 마케팅 전략에 맞는 시각적인 디자인을 담당합니다.\n개발자: 웹 또는 앱 개발을 담당하여 사용자 친화적인 인터페이스를 구축합니다.\n마케팅 전문가: 시장 조사 및 분석, 타겟 마케팅 전략 수립, 광고 캠페인 기획 등을 담당합니다.\n작가: 마케팅 컨텐츠 작성을 담당합니다.\n테크니컬 라이터: 기술적인 문서 작성을 담당합니다.\n프로젝트 매니저 보조: PM을 도와 일정 관리, 예산 관리, 인력 관리 등을 담당합니다.\n데이터 분석가: 마케팅 성과를 분석하고 보고서를 작성합니다.\n소셜 미디어 매니저: 소셜 미디어 채널을 관리하고, 광고 게시 및 고객과의 소통을 담당합니다.\n회계사: 예산 관리 및 회계 업무를 담당합니다.");
        messages.add(assistantMessage);

        Message requestMessage = new Message("user", "나는" + recommendMemberRequestDTO.getCategory() + "프로젝트를 진행할예정입니다. 시작날짜는" + recommendMemberRequestDTO.getStart() + ", 마감날짜는" + recommendMemberRequestDTO.getDeadline() + "입니다. 이때 필요한 팀의 구성원 역할을 알려주세요. 다른 말 필요없이\n\"역할\" : \"역할이 해야할일\"\n로만 대답해주세요. 다른 말 없이 역할과 역할이 해야할일만 작성해주세요.");
        messages.add(requestMessage);

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-NCP-CLOVASTUDIO-API-KEY", apiKeyClovaStudio);
        headers.set("X-NCP-APIGW-API-KEY", clovaStudioRecommend.getApiGateWayKey());
        headers.set("X-NCP-CLOVASTUDIO-REQUEST-ID", clovaStudioRecommend.getRequestMemberId());
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
        Map<String, String> roleDescriptions = new HashMap<>();

        String[] roleLines = content.split("\n");
        for (String line : roleLines) {
            String[] rolePair = line.split(":");
            if (rolePair.length == 2) {
                String role = rolePair[0].trim();
                String description = rolePair[1].trim();
                roleDescriptions.put(role, description);
            }


        }
        return roleDescriptions;
    }

}
