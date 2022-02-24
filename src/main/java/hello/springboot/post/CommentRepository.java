package hello.springboot.post;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long>, JpaSpecificationExecutor<Comment>, QueryByExampleExecutor<Comment> {

    @EntityGraph(attributePaths = "post")
    Optional<Comment> getById(Long id);

    @Transactional(readOnly = true) //그냥 이렇게 추가해서 사용할 수 있음. 근데 이때는 readonly=true가 좋음. 값을 변경하는게 아니니까.
    <T> List<T> findByPost_Id(Long id, Class<T> type);
}
