package mvc.promiseme.calendar.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CalendarAndTodoAllByRoleDto {
    private String role;
    private String recommendation;
    private List<MemberDto> members;


    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @Builder
    public class MemberDto{
        private String name;
        private List<TodoDTO> todoList;
    }


    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @Builder
    public class TodoDTO {
        private String content;
        private String status;
    }
}
