package hello.springboot;

import org.hibernate.Session;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Component
@Transactional
public class JpaRunner implements ApplicationRunner {

    @PersistenceContext //JPA 어노테이션
    EntityManager entityManager; //JPA에서 핵심적인 클래스

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Account account = new Account();
        account.setUsername("spring");
        account.setPassword("hibernate");

        //hibernate도 사용가능. hibernate의 핵심적인 API는 Session임
        Session session = entityManager.unwrap(Session.class);
        session.persist(account); //영속화(DB저장), 트랜잭션 필요.
    }
}
