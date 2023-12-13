package mvc.promiseme.todo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ToDoStatus {
    INCOMPLETE("미완료"),
    COMPLETE("완료");

    private String value;
}
