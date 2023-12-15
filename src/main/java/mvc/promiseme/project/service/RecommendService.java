package mvc.promiseme.project.service;

import lombok.RequiredArgsConstructor;
import mvc.promiseme.project.dto.RecommendMemberRequestDTO;
import org.springframework.stereotype.Service;

import java.util.Map;



public interface RecommendService {
    public Map<String, String> recommendMember(RecommendMemberRequestDTO recommendMemberRequestDTO);
}
