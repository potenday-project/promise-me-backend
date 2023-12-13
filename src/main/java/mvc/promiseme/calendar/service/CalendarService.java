package mvc.promiseme.calendar.service;

import mvc.promiseme.calendar.dto.CalendarResponseDTO;

import java.util.List;

public interface CalendarService {

    public List<CalendarResponseDTO>calendarAll(Long projectId);
}
