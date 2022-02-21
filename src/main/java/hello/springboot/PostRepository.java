package hello.springboot;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findByTitleContains(String title, Pageable pageable); //타이틀에 특정한 키워드를 가진 포스트 목록을 찾겠다. 근데 페이징을 해서 찾겠다. 메소드 이름을 분석해서 쿼리를 작성해 줌.

    long countByTitleContains(String title);
}
