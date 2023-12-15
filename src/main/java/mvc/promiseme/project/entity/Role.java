package mvc.promiseme.project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mvc.promiseme.calendar.entity.Calendar;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;
    @Column(length = 40)
    private String name;

    //== 연관관계 설정 ==//
    @OneToMany(mappedBy = "role")
    private List<Member> memberList = new ArrayList<>();

    @OneToMany(mappedBy = "role")
    private List<Calendar> calendarList = new ArrayList<>();
}
