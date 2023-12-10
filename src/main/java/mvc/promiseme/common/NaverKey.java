package mvc.promiseme.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NaverKey {

    @Value("${ACCESS_KEY_ID}")
    private String accessKey;
    @Value("${KEY}")
    private String key;
}
