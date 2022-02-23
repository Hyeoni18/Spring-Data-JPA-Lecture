package hello.springboot.post;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByTitleStartsWith(String title);

    @Query("SELECT p, p.title AS title FROM Post AS p WHERE p.title=?1") //alias는 p.title AS title 이렇게 정의.
    List<Post> findByTitle(String title, Sort sort); //Sort를 하고 싶으면 추가만 하면 돼.
}
