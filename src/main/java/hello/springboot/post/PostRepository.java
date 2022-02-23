package hello.springboot.post;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByTitleStartsWith(String title);

    //@Query("SELECT p, p.title AS title FROM Post AS p WHERE p.title=?1")
    @Query("SELECT p FROM #{#entityName} AS p WHERE p.title= :title")
    List<Post> findByTitle(@Param("title") String keyword, Sort sort);
}
