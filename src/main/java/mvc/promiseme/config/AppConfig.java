package mvc.promiseme.config;


import com.querydsl.jpa.impl.JPAQueryFactory;
import mvc.promiseme.kakao.KakaoApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
@Configuration
public class AppConfig {

    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public JPAQueryFactory queryFactory(){
        return new JPAQueryFactory(entityManager);


    }
    @Bean
    public KakaoApi kakaoApi() {
        return new KakaoApi();
    }
}