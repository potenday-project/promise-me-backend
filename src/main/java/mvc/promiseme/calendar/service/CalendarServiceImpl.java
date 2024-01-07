package mvc.promiseme.calendar.service;

import lombok.RequiredArgsConstructor;
import mvc.promiseme.calendar.dto.CalendarAndTodoAllByRoleDto;
import mvc.promiseme.calendar.entity.Calendar;
import mvc.promiseme.calendar.repository.CalendarRepository;
import mvc.promiseme.common.utils.EntityLoaderById;
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
    private final EntityLoaderById entityLoaderById;

    @Override
    public List<CalendarAndTodoAllByRoleDto> calendarAndtodoAll(Long projectId, LocalDate date) {
        Project project = entityLoaderById.getProjectByIdOrThrow(projectId);
        List<Calendar> calendars = calendarRepository
                .findByProjectAndStartDateLessThanEqualAndFinishDateGreaterThanEqual(project, date, date);
        if(calendars == null) throw new NoSuchElementException("[ERROR] 선택한 날짜에 일정이 존재하지 않습니다.");

        List<CalendarAndTodoAllByRoleDto> result = makeTodoAllByRoleDtoList(calendars, date);
        return result;
    }

    private List<CalendarAndTodoAllByRoleDto> makeTodoAllByRoleDtoList(List<Calendar> calendars, LocalDate date){
        List<CalendarAndTodoAllByRoleDto> result = new ArrayList<>();
        for(Calendar c : calendars){
            CalendarAndTodoAllByRoleDto todoAllByRoleDto = makeTodoAllByRoleDto(c, date);
            result.add(todoAllByRoleDto);
        }
        return result;
    }

    private CalendarAndTodoAllByRoleDto makeTodoAllByRoleDto(Calendar c, LocalDate date){
        CalendarAndTodoAllByRoleDto todoAllByRoleDto = new CalendarAndTodoAllByRoleDto();
        todoAllByRoleDto.setRole(c.getRole().getName());
        todoAllByRoleDto.setRecommendation(c.getContent());

        List<Member> members = memberRepository.findByProjectAndAndRole(c.getProject(), c.getRole());
        List<CalendarAndTodoAllByRoleDto.MemberDto> memberDtoList = makeMemberDtoList(members, date);
        todoAllByRoleDto.setMembers(memberDtoList);

        return todoAllByRoleDto;
    }

    private List<CalendarAndTodoAllByRoleDto.MemberDto> makeMemberDtoList(List<Member> members, LocalDate date){
        List<CalendarAndTodoAllByRoleDto.MemberDto> memberDtoList = new ArrayList<>();

        for(Member m : members){
            CalendarAndTodoAllByRoleDto.MemberDto memberDto = makeMemberDto(m, date);
            memberDtoList.add(memberDto);
        }

        return memberDtoList;
    }

    private CalendarAndTodoAllByRoleDto.MemberDto makeMemberDto(Member m, LocalDate date){
        CalendarAndTodoAllByRoleDto.MemberDto memberDto = new CalendarAndTodoAllByRoleDto.MemberDto();

        List<Todo> todoList = todoRepository.findByMemberAndAndTodoDate(m, date);
        List<CalendarAndTodoAllByRoleDto.TodoDTO> todoDTOList = makeTodoDTOList(todoList);

        memberDto.setName(m.getUsers().getNickname());
        memberDto.setTodoList(todoDTOList);

        return memberDto;
    }

    private List<CalendarAndTodoAllByRoleDto.TodoDTO> makeTodoDTOList(List<Todo> todoList){
        List<CalendarAndTodoAllByRoleDto.TodoDTO> todoDTOList = new ArrayList<>();

        for(Todo t : todoList){
            CalendarAndTodoAllByRoleDto.TodoDTO todoDTO = makeTodoDto(t);
            todoDTOList.add(todoDTO);
        }
        return todoDTOList;
    }

    private CalendarAndTodoAllByRoleDto.TodoDTO makeTodoDto(Todo t){
        CalendarAndTodoAllByRoleDto.TodoDTO todoDTO = new CalendarAndTodoAllByRoleDto.TodoDTO();
        todoDTO.setContent(t.getContent());
        todoDTO.setStatus(t.isCompleted());
        return todoDTO;
    }

//    @Override
//    public List<CalendarResponseDTO> calendarAll(Long projectId) {
//        Project project = Project.builder().projectId(projectId).build();
//        return calendarRepository.findByProject(project);
//    }
}
