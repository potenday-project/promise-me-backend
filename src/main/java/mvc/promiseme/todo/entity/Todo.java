package mvc.promiseme.todo.entity;

import jakarta.persistence.*;
import lombok.*;
import mvc.promiseme.calendar.entity.Calendar;
import mvc.promiseme.project.entity.Member;
import mvc.promiseme.project.entity.Project;
import mvc.promiseme.todo.dto.TodoRequestDTO;

import java.time.LocalDate;

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
    @Builder.Default
    private ToDoStatus isCompleted = ToDoStatus.INCOMPLETE;

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


    public Todo mapToEntity(TodoRequestDTO todoRequestDTO, Project project, Member member) {
        return Todo.builder()
                .content(todoRequestDTO.getContent())
                .todoDate(todoRequestDTO.getTodoDate())
                .project(project)
                .member(member)
                .calendar(null)
                .build();
    }

}
