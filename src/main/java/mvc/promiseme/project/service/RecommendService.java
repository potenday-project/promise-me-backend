package mvc.promiseme.project.service;

import lombok.RequiredArgsConstructor;
import mvc.promiseme.project.dto.RecommendMemberRequestDTO;
import mvc.promiseme.project.dto.RecommendScheduleRequestDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



public interface RecommendService {
    public Map<String, String> recommendMember(RecommendMemberRequestDTO recommendMemberRequestDTO);
    public List<Map<String, String>> recommendSchedule(RecommendScheduleRequestDTO recommendScheduleRequestDTO);

    }
