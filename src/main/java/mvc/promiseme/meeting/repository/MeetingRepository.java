package mvc.promiseme.meeting.repository;

import mvc.promiseme.meeting.dto.MeetingResponseDTO;
import mvc.promiseme.meeting.entity.Meeting;
import mvc.promiseme.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {
    @Query("SELECT NEW mvc.promiseme.meeting.dto.MeetingResponseDTO(m.meetingId, m.summary, m.meetingContent, m.meetingDate) from Meeting m where m.project = :project")
    List<MeetingResponseDTO> findByProject(@Param("project") Project project);

}
