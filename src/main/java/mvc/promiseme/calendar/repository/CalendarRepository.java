package mvc.promiseme.calendar.repository;

import mvc.promiseme.calendar.entity.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {
//    @Query("SELECT NEW mvc.promiseme.calendar.dto.CalendarResponseDTO(c.content, c.startDate, c.finishDate, u.nickname) from Calendar c join c.member m join m.users u where c.project = :project")
//    List<CalendarResponseDTO> findByProject(Project project);
}
