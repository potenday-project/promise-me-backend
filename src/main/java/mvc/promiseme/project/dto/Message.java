package mvc.promiseme.project.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Message {
    private String role;
    private String content;

}
