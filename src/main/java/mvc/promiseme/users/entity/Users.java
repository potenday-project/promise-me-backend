package mvc.promiseme.users.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import mvc.promiseme.project.entity.Member;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, length = 64)
    private String email;

    @Column(nullable = false, length = 31)
    private String password;

    @Column(nullable = false, length = 20)
    private String nickname;

    private LocalDate createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDate.now();
    }

    //== 연관관계 설정 ==//
    @OneToMany(mappedBy = "users")
    private List<Member> memberList = new ArrayList<>();
}
