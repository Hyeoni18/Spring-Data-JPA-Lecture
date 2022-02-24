package hello.springboot.post;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    //@EntityGraph(value = "Comment.post", type = EntityGraph.EntityGraphType.LOAD) //연관관계 그래프의 이름(Comment.post)
    @EntityGraph(attributePaths = "post") //엔티티 클래스에 NamedEntityGraph를 정의하지 않고 이렇게 정의해도 됨.
    Optional<Comment> getById(Long id);
}
