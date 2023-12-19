package mvc.promiseme.calendar.service;

import lombok.RequiredArgsConstructor;
import mvc.promiseme.calendar.repository.CalendarRepository;
import org.springframework.stereotype.Service;

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
