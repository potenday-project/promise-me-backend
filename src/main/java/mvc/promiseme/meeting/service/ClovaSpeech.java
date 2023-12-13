package mvc.promiseme.meeting.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClovaSpeech {

    @Value("${SECRET_KEY}")
    private String secretKey;
    @Value("${INVOKE_URL}")
    private String invokeURL;

}
