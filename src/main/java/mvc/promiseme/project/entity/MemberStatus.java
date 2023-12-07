package mvc.promiseme.project.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MemberStatus {
    PARTICIPATION("참여"),
    WITHDRAW("탈퇴");

    private String value;
}
