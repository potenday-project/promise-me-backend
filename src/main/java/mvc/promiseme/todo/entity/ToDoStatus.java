package mvc.promiseme.todo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ToDoStatus {
    COMPLETE("완료"),
    INCOMPLETE("미완료");

    private String value;
}
