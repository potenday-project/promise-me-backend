package mvc.promiseme.calendar.service;

import lombok.RequiredArgsConstructor;
import mvc.promiseme.calendar.dto.CalendarAndTodoAllByRoleDto;
import mvc.promiseme.calendar.entity.Calendar;
import mvc.promiseme.calendar.repository.CalendarRepository;
import mvc.promiseme.project.entity.Member;
import mvc.promiseme.project.entity.Project;
import mvc.promiseme.project.repository.MemberRepository;
import mvc.promiseme.project.repository.ProjectRepository;
import mvc.promiseme.todo.entity.Todo;
import mvc.promiseme.todo.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CalendarServiceImpl implements CalendarService{

    private final CalendarRepository calendarRepository;
    private final MemberRepository memberRepository;
    private final TodoRepository todoRepository;
    private final ProjectRepository projectRepository;

    @Override
    public List<CalendarAndTodoAllByRoleDto> calendarAndtodoAll(Long projectId, LocalDate date) {
        //
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new NoSuchElementException("[ERROR] 해당 프로젝트는 존재하지 않습니다"));
        List<Calendar> calendars = calendarRepository
                .findByProjectAndStartDateLessThanEqualAndFinishDateGreaterThanEqual(project, date, date);
        if(calendars == null) throw new NoSuchElementException("[ERROR] Nothing in the calendar on selected date");

        //
        List<CalendarAndTodoAllByRoleDto> result = new ArrayList<>();
        for(Calendar c : calendars){
            CalendarAndTodoAllByRoleDto todoAllByRoleDto = new CalendarAndTodoAllByRoleDto();
            todoAllByRoleDto.setRole(c.getRole().getName());
            todoAllByRoleDto.setRecommendation(c.getContent());

            List<CalendarAndTodoAllByRoleDto.MemberDto> memberDtoList = new ArrayList<>();

            List<Member> members = memberRepository.findByProjectAndAndRole(c.getProject(), c.getRole());
            for(Member m : members){
                CalendarAndTodoAllByRoleDto.MemberDto memberDto = new CalendarAndTodoAllByRoleDto.MemberDto();
                memberDto.setName(m.getUsers().getNickname());

                List<CalendarAndTodoAllByRoleDto.TodoDTO> todoDTOList = new ArrayList<>();

                List<Todo> todoList = todoRepository.findByMemberAndAndTodoDate(m, date);
                for(Todo t : todoList){
                    CalendarAndTodoAllByRoleDto.TodoDTO todoDTO = new CalendarAndTodoAllByRoleDto.TodoDTO();
                    todoDTO.setContent(t.getContent());
                    todoDTO.setStatus(t.isCompleted());
                    todoDTOList.add(todoDTO);
                }
                memberDto.setTodoList(todoDTOList);
                memberDtoList.add(memberDto);
            }
            todoAllByRoleDto.setMembers(memberDtoList);
            result.add(todoAllByRoleDto);
        }

        return result;
    }
//    @Override
//    public List<CalendarResponseDTO> calendarAll(Long projectId) {
//        Project project = Project.builder().projectId(projectId).build();
//        return calendarRepository.findByProject(project);
//    }
}
