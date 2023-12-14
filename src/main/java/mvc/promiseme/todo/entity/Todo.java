package mvc.promiseme.todo.entity;

import jakarta.persistence.*;
import lombok.*;
import mvc.promiseme.calendar.entity.Calendar;
import mvc.promiseme.project.entity.Member;
import org.hibernate.annotations.CreationTimestamp;

import mvc.promiseme.project.entity.Project;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long toDoId;
    private String content;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ToDoStatus isCompleted;

    private LocalDate todoDate;
    private LocalDate createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDate.now();
    }

    //== 연관 관계 설정 ==//
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "calendar_id")
    private Calendar calendar;

}
