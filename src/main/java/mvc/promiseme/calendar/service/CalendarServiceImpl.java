package mvc.promiseme.calendar.service;

import lombok.RequiredArgsConstructor;
import mvc.promiseme.calendar.dto.CalendarResponseDTO;
import mvc.promiseme.calendar.repository.CalendarRepository;
import mvc.promiseme.project.entity.Project;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CalendarServiceImpl implements CalendarService{
    private final CalendarRepository calendarRepository;
//    @Override
//    public List<CalendarResponseDTO> calendarAll(Long projectId) {
//        Project project = Project.builder().projectId(projectId).build();
//        return calendarRepository.findByProject(project);
//    }
}
