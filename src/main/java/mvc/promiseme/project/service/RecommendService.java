package mvc.promiseme.project.service;

import mvc.promiseme.project.dto.RecommendMemberRequestDTO;
import mvc.promiseme.project.dto.RecommendScheduleRequestDTO;

import java.util.List;
import java.util.Map;



public interface RecommendService {
    public Map<String, String> recommendMember(RecommendMemberRequestDTO recommendMemberRequestDTO);
    public List<Map<String, String>> recommendSchedule(RecommendScheduleRequestDTO recommendScheduleRequestDTO);

    }
