package mvc.promiseme.calendar.service;

import mvc.promiseme.calendar.dto.CalendarAndTodoAllByRoleDto;
import mvc.promiseme.calendar.dto.CalendarResponseDTO;

import java.time.LocalDate;
import java.util.List;

public interface CalendarService {

    //public List<CalendarResponseDTO>calendarAll(Long projectId);

    List<CalendarAndTodoAllByRoleDto> calendarAndtodoAll(Long projectId, LocalDate date);
}
