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

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Progress isProgress;

    @Column(nullable = false, length = 50)
    private String category;

    @Column(nullable = false, length = 100)
    private String topic;

    private LocalDateTime start;
    private LocalDateTime deadline;

    @CreatedDate
    private LocalDateTime createAt;

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
