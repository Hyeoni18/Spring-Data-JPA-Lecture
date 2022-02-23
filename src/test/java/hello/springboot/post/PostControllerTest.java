package hello.springboot.post;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PostControllerTest {

    @Autowired
    PostRepository postRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void save() {
        Post post = new Post();
        post.setTitle("JPA");
        Post savedPost = postRepository.save(post); //insert query 발생, persist에 넘겨준 post 인스턴스 자체가 영속화됨.

        assertThat(entityManager.contains(post)).isTrue();
        assertThat(entityManager.contains(savedPost)).isTrue();
        assertThat(savedPost == post);

        Post postUpdate = new Post();
        postUpdate.setTitle("JPA222");
        postUpdate.setId(post.getId());
        Post updatedPost = postRepository.save(postUpdate); //update query 발생, merge, 복사본을 만들어서 영속화를 하고 복사본은 persist하지 않고 그 자체를 리턴해줌.

        assertThat(entityManager.contains(updatedPost)).isTrue(); //updatedPost 영속화
        assertThat(entityManager.contains(postUpdate)).isFalse(); //postUpdate 영속화 되지 않음
        assertThat(updatedPost == postUpdate);

        //그러니까 항상 반환을 받아서 사용하는게 좋아. postUpdate를 다시 사용하게 되면 persist가 감지를 못해.
        updatedPost.setTitle("do it"); //1.update를 하라고 알려주지 않아도 객체 상태 변화를 감지해서 알아서 update를 해줌

        List<Post> posts = postRepository.findAll(); //2.여기서. 데이터를 가져오려고 하네? 빨리 싱크해야겠다. 이게 persist 상태의 객체임.
        assertThat(posts.size()).isEqualTo(1);
    }

    @Test
    public void findByTitleStartWith() {
        savePost();

        List<Post> posts = postRepository.findByTitleStartsWith("Spring");
        assertThat(posts.size()).isEqualTo(1);
    }

    @Test
    public void findByTitle() {
        savePost();

  //      List<Post> posts = postRepository.findByTitle("Spring Data JPA", Sort.by("title")); //title 가능함. 포스트 엔티티의 프로퍼티니까.
  //      List<Post> posts = postRepository.findByTitle("Spring Data JPA", Sort.by("LENGTH(title)")); //title 길이로 정렬을 해줘. error 발생. 이를 우회하려면 JpaSort 사용
        List<Post> posts = postRepository.findByTitle("Spring Data JPA", JpaSort.unsafe("LENGTH(title)"));
        assertThat(posts.size()).isEqualTo(1);
    }

    private void savePost() {
        Post post = new Post();
        post.setTitle("Spring Data JPA");
        postRepository.save(post);
    }
}