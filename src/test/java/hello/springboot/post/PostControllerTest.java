package hello.springboot.post;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
}