package mvc.promiseme.project.repository;

import mvc.promiseme.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProjectRepository extends JpaRepository<Project,Long> {

    @Query("SELECT datediff(p.deadline, now()) From Project p where p.projectId = :projectId")
    int finddday(@Param("projectId") Long projecrtId);
}
