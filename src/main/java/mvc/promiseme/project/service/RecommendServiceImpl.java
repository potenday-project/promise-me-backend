package mvc.promiseme.project.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import mvc.promiseme.calendar.entity.Calendar;
import mvc.promiseme.calendar.repository.CalendarRepository;
import mvc.promiseme.project.dto.Message;
import mvc.promiseme.project.dto.RecommendMemberRequestDTO;
import mvc.promiseme.project.dto.RecommendScheduleRequestDTO;
import mvc.promiseme.project.entity.Project;
import mvc.promiseme.project.entity.Role;
import mvc.promiseme.project.repository.ProjectRepository;
import mvc.promiseme.project.repository.RoleRepository;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class RecommendServiceImpl implements RecommendService{

    private final ClovaStudioRecommend clovaStudioRecommend;
    private final ProjectRepository projectRepository;
    private final CalendarRepository calendarRepository;
    private final RoleRepository roleRepository;
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
    @Transactional
    public List<Map<String, String>> recommendSchedule(RecommendScheduleRequestDTO recommendScheduleRequestDTO) {
        String url = clovaStudioRecommend.getUrl();
        String apiKeyClovaStudio = clovaStudioRecommend.getApiKeyClovaStudio();


        List<Message> messages = new ArrayList<>();

        // 첫 번째 메시지
        Message systemMessage = new Message("system", "당신은 프로젝트 관리를 잘하는 사람입니다. 당신은 프로젝트 분야와 프로젝트 기간을 알려주면 필요한 구성원을 추천해줄 수 있습니다.");
        messages.add(systemMessage);

        // 두 번째 메시지
        Message userMessage = new Message("user","나는 웹개발프로젝트를 진행할예정입니다. 시작날짜는 2023-12-07, 마감날짜는 2023-12-31입니다. 우리팀은 기획자, 백엔드개발자, 프론트개발자,디자이너가 있습니다. 기간 내에 각 역할별로 해야하는 업무를 알려주세요.  각자 프로젝트에서 해야하는 업무도 알려주고 그 업무들의 순서를 정해줬으면 좋겠습니다. 각 업무의 시작날짜와 마감날짜도 함께 알려주세요. 시작날짜와 마감날짜 형식은 \"yyyy-MM-dd\" 형식으로 작성해주세요. 다른 말 필요없이\n" +
                "\n" +
                "\"역할\" \n" +
                "\"해야할 업무\" (시작날짜 ~ 마감날짜)로만 대답해주세요. 다른 말 없이 역할과 해야할 업무, 시작날짜, 마감날짜만 작성해주세요. 역할 앞에 숫자를 넣지 말고, 업무 앞에 -를 넣지 마세요. ");
        messages.add(userMessage);

        // 세 번째 메시지
        Message assistantMessage = new Message("assistant", "기획자\n" +
                "사용자 요구사항 정의서 작성 (2023-12-07 ~ 2023-12-10)\n" +
                "기능 명세서 작성 (2023-12-11 ~ 2023-12-15)\n" +
                "화면 설계서 작성 (2023-12-16 ~ 2023-12-20)\n" +
                "\n" +
                "백엔드 개발자\n" +
                "DB 스키마 작성 (2023-12-21 ~ 2023-12-25)\n" +
                "API 기능 구현 (2023-26 ~ 2023-12-30)\n" +
                "서버 구축 및 배포 (2023-12-31)\n" +
                "\n" +
                "프론트엔드 개발자\n" +
                "HTML/CSS 레이아웃 구성 (2023-12-07 ~ 2023-12-10)\n" +
                "JavaScript 기능 구현 (2023-12-11 ~ 2023-12-20)\n" +
                "반응형 웹 적용 (2023-12-21 ~ 2023-12-25)\n" +
                "\n" +
                "디자이너\n" +
                "로고 디자인 (2023-12-07 ~ 202310)\n" +
                "UI/UX 디자인 (2023-12-11 ~ 2023-12-20)\n" +
                "웹 페이지 디자인 (2023-12-21 ~ 2023-12-31)");
        messages.add(assistantMessage);

        Message requestMessage = new Message("user", "나는" + recommendScheduleRequestDTO.getCategory() + "프로젝트를 진행할예정입니다. 시작날짜는" + recommendScheduleRequestDTO.getStart() + ", 마감날짜는" + recommendScheduleRequestDTO.getDeadline() + "입니다. 우리팀은"+recommendScheduleRequestDTO.getMember()+"가 있습니다. 기간 내에 각 역할별로 해야하는 업무를 알려주세요.  각자 프로젝트에서 해야하는 업무도 알려주고 그 업무들의 순서를 정해줬으면 좋겠습니다. 각 업무의 시작날짜와 마감날짜도 함께 알려주세요. 시작날짜와 마감날짜 형식은 \"yyyy-MM-dd\" 형식으로 작성해주세요. 다른 말 필요없이\n" +
                "\n" +
                "\"역할\" \n" +
                "\"해야할 업무\" (시작날짜 ~ 마감날짜)로만 대답해주세요. 다른 말 없이 역할과 해야할 업무, 시작날짜, 마감날짜만 작성해주세요. 역할 앞에 숫자를 넣지 말고, 업무 앞에 -를 넣지 마세요. ");
        messages.add(requestMessage);

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-NCP-CLOVASTUDIO-API-KEY", apiKeyClovaStudio);
        headers.set("X-NCP-APIGW-API-KEY", clovaStudioRecommend.getApiGateWayKey());
        headers.set("X-NCP-CLOVASTUDIO-REQUEST-ID", clovaStudioRecommend.getRequestScheduleId());
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
        List<Map<String,String>> roleScheduleList = new ArrayList<>();

        String[] roleLines = content.split("\n\n");
        System.out.println("================roleLines==========");
        for(String s : roleLines)
            System.out.println(s);

        System.out.println("================roleLines==========");
        for (String line : roleLines) {
            String[] rolePair = line.split("\n");
            if (rolePair.length > 0) {
                String role = rolePair[0].trim();
                String rolePattern = "(\\d+\\.\\s*)?(.+)";
                Pattern roleRegex = Pattern.compile(rolePattern);
                Matcher roleMatcher = roleRegex.matcher(role);

                if (roleMatcher.matches()) {
                    String roleNumber = roleMatcher.group(1); // 1.
                    role = roleMatcher.group(2); // 기획자

                    // 숫자와 점을 제거한 role
                    role = role.trim();
                }

                if(Character.isDigit(role.charAt(0)))
                    role = role.substring(4);
                for (int i = 1; i < rolePair.length; i++) {
                    Map<String, String> roleSchedule = new HashMap<>();
                    String patternString = "(.*?) \\((.*?) ~ (.*?)\\)";

                    // 패턴 컴파일
                    Pattern pattern = Pattern.compile(patternString);

                    // 매처 생성
                    Matcher matcher = pattern.matcher(rolePair[i]);

                    // 매칭되는 그룹 찾기
                    if (matcher.find()) {
                        String task = matcher.group(1).trim();
                        String taskPattern = "-?\\s*(.+)";

                        Pattern taskRegex = Pattern.compile(taskPattern);
                        Matcher taskMatcher = taskRegex.matcher(task);

                        if (taskMatcher.matches())
                            task = taskMatcher.group(1).trim();

                        String startDate = matcher.group(2).trim();
                        String endDate = matcher.group(3).trim();

                        // 결과 출력
                        System.out.println("Role : " + role);
                        System.out.println("Task: " + task);
                        System.out.println("Start Date: " + startDate);
                        System.out.println("End Date: " + endDate);
                        System.out.println();

                        roleSchedule.put("role", role);
                        roleSchedule.put("task", task);
                        roleSchedule.put("start", startDate);
                        roleSchedule.put("finish", endDate);
                    } else {
                        String patternString1 = "(.*?) \\((.*?)\\)";

                        // 패턴 컴파일
                        Pattern pattern1 = Pattern.compile(patternString1);

                        // 매처 생성
                        Matcher matcher1 = pattern1.matcher(rolePair[i]);

                        // 매칭되는 그룹 찾기
                        if (matcher1.find()) {
                            String task = matcher1.group(1).trim();
                            String startDate = matcher1.group(2).trim();
                            String endDate = matcher1.group(2).trim();

                            roleSchedule.put("role", role);
                            roleSchedule.put("task", task);
                            roleSchedule.put("start", startDate);
                            roleSchedule.put("finish", endDate);
                        } else {
                            System.out.println("No match found for line: " + line);
                            // 이 부분에서 예외 처리 또는 로깅을 수행할 수 있습니다.
                        }
                    }
                    roleScheduleList.add(roleSchedule);
                }
            }
        }
        insertCalender(recommendScheduleRequestDTO.getProjectId(),roleScheduleList);
        return roleScheduleList;
    }

    @Transactional
    public void insertCalender(Long projectId, List<Map<String, String>> roleScheduleList) {
        Project project = projectRepository.findById(projectId).orElseThrow(()->new NoSuchElementException("[ERROR] 해당하는 프로젝트가 존재하지 않습니다."));
        for(Map<String,String> map : roleScheduleList){
            Role r = new Role();
            Role role = roleRepository.findByName(map.get("role"));
            if(role==null) {
                role = Role.builder().name(map.get("role")).build();
                roleRepository.save(role);
            }
            Calendar calendar = Calendar.builder().project(project).role(role).content(map.get("content")).finishDate(LocalDate.parse(map.get("finish"))).startDate(LocalDate.parse(map.get("start"))).build();
            calendarRepository.save(calendar);
        }
    }

}
