package mvc.promiseme.project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mvc.promiseme.calendar.entity.Calendar;
import mvc.promiseme.users.entity.Users;

import mvc.promiseme.notice.entity.Notice;
import mvc.promiseme.todo.entity.Todo;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(length = 50)
    private String role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemberStatus status;

    //== 연관 관계 설정 ==//
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @OneToMany(mappedBy = "member")
    private List<Notice> noticeList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Calendar> calendarList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Todo> todoList = new ArrayList<>();

}
