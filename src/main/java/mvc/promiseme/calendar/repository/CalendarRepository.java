package mvc.promiseme.calendar.repository;

import mvc.promiseme.calendar.dto.CalendarResponseDTO;
import mvc.promiseme.calendar.entity.Calendar;
import mvc.promiseme.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {
    List<CalendarResponseDTO> findByProject(Project project);
}
