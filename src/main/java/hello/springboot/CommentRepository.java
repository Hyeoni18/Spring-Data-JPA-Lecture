package hello.springboot;

import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;

//@RepositoryDefinition(domainClass = Comment.class, idClass = Long.class) //Repository 직접 정의, 어떤 인터페이스도 상속 받지 않아도 됨.
public interface CommentRepository extends MyRepository<Comment, Long> { //내가 만든 인터페이스를 상속

    //메소드를 정의하면 해당하는 기능을 스프링 데이터 JPA가 구현 가능한 기능은 기본 제공.
    Comment save(Comment comment);

    List<Comment> findAll();
}
