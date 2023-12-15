package mvc.promiseme.project.repository;

import mvc.promiseme.project.dto.ProjectResponseDTO;
import mvc.promiseme.project.entity.Member;
import mvc.promiseme.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project,Long> {

    @Query("SELECT CAST((SELECT COUNT(t1) FROM Todo t1 WHERE t1.project.id = :projectId AND t1.isCompleted = true) / COUNT(t2) * 100 AS INTEGER) " +
            "FROM Todo t2 WHERE t2.project.id = :projectId")
    int getProgress(@Param("projectId") Long projecrtId);
    @Query("SELECT datediff(p.deadline, now()) From Project p where p.projectId = :projectId")
    int findDday(@Param("projectId") Long projecrtId);

    @Query("SELECT p.category From Project p group by p.category ORDER BY count (p.category) DESC LIMIT 6")
    List<String> getCategoryRanking();

}
