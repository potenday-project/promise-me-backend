package mvc.promiseme.project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mvc.promiseme.calendar.entity.Calendar;
import mvc.promiseme.meeting.entity.Meeting;
import mvc.promiseme.todo.entity.Todo;

import mvc.promiseme.notice.entity.Notice;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;

    @Column(length = 50)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Progress isProgress;

    @Column(length = 50)
    private String category;

    @Column(length = 100)
    private String topic;

    private LocalDate start;
    private LocalDate deadline;
    private LocalDate createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDate.now();
    }

    //== 연관관계 설정 ==//
    @OneToMany(mappedBy = "project")
    private List<Member> memberList = new ArrayList<>();

    @OneToMany(mappedBy = "project")
    private List<Calendar> calendarList = new ArrayList<>();

    @OneToMany(mappedBy = "project")
    private List<Meeting> meetingList = new ArrayList<>();

    @OneToMany(mappedBy = "project")
    private List<Notice> noticeList = new ArrayList<>();

    @OneToMany(mappedBy = "project")
    private List<Todo> todoList = new ArrayList<>();
}
