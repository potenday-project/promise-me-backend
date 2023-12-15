package mvc.promiseme.meeting.entity;

import jakarta.persistence.*;
import lombok.*;
import mvc.promiseme.project.entity.Project;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long meetingId;
    private LocalDate meetingDate;

    @PrePersist
    public void prePersist() {
        this.meetingDate = LocalDate.now();
    }

    @Column(columnDefinition = "LONGTEXT")
    private String meetingContent;

    @Column(length = 50000)
    private String summary;

    @Column(length = 50)
    private String meetingName;

    //== 연관 관계 설정 ==//
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;
}
