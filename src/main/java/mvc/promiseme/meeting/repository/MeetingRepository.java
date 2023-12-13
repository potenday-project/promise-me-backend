package mvc.promiseme.meeting.repository;

import mvc.promiseme.meeting.dto.MeetingResponseDTO;
import mvc.promiseme.meeting.entity.Meeting;
import mvc.promiseme.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {
    List<MeetingResponseDTO> findByProject(Project project);

}
