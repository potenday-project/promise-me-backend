package mvc.promiseme.meeting;

import mvc.promiseme.meeting.dto.MeetingResponseDTO;
import mvc.promiseme.meeting.service.MeetingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MeetingTest {
    @Autowired
    private MeetingService meetingService;

    @Test
    public void testMeetingAll() {
        List<MeetingResponseDTO> list = meetingService.meetingAll(1L);
        for(MeetingResponseDTO dto : list){
            System.out.println("meetingid : "+ dto.getMeetingId());

            System.out.println("summeary : "+ dto.getSummary());

            System.out.println("content : "+ dto.getMeetingContent());
        }
    }
}
