package hello.springboot.post;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByTitleStartsWith(String title);

    //@Query("SELECT p, p.title AS title FROM Post AS p WHERE p.title=?1")
    @Query("SELECT p FROM #{#entityName} AS p WHERE p.title= :title")
    List<Post> findByTitle(@Param("title") String keyword, Sort sort);

    @Modifying(clearAutomatically = true) //해당 쿼리는 셀렉트가 아닌 업데이트다.
    @Query("UPDATE Post p Set p.title = ?1 WHERE p.id = ?2")
    int updateTitle(String title, Long id);
}
