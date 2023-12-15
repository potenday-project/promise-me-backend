package mvc.promiseme.calendar.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CalendarResponseDTO {
    private String content;
    private LocalDate startDate;
    private LocalDate finishDate;
    private String nickname;
}
