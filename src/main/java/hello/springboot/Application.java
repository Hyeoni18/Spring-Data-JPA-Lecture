package hello.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomRepositoryImpl.class) //base class를 알려 줘야 해
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
