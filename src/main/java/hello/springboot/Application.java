package hello.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "accountAuditorAware") //사용하겠다 선언 (bean이름으로)
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
