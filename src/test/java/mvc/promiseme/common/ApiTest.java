package mvc.promiseme.common;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ApiTest {

    @Autowired
    private NaverKey naverKey;

    @Test
    public void testNaverKeyValues() {
        assertNotNull(naverKey, "NaverKey instance should not be null");

        System.out.println(naverKey.getAccessKey());
        System.out.println(naverKey.getKey());
    }
}
