package mvc.promiseme.calendar.repository;

import mvc.promiseme.calendar.dto.CalendarResponseDTO;
import mvc.promiseme.calendar.entity.Calendar;
import mvc.promiseme.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {
//    @Query("SELECT NEW mvc.promiseme.calendar.dto.CalendarResponseDTO(c.content, c.startDate, c.finishDate, u.nickname) from Calendar c join c.member m join m.users u where c.project = :project")
//    List<CalendarResponseDTO> findByProject(Project project);
      List<Calendar> findByProjectAndStartDateLessThanEqualAndFinishDateGreaterThanEqual(Project project, LocalDate startDate, LocalDate finishDate);
}
