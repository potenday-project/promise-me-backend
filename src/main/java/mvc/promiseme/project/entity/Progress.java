package mvc.promiseme.project.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Progress {
    PROGRESSING("진행중"),
    COMPLETE("진행 완료");

    private String value;
}
