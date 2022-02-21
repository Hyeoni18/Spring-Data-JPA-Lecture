package hello.springboot.post;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PostRepositoryTest extends TestCase {

    @Autowired
    PostRepository postRepository;

    @Test
    public void curd() {
        Post post = new Post();
        post.setTitle("Hibernate");

        assertThat(postRepository.contains(post)).isFalse(); //트랜지스트 상태

        postRepository.save(post);

        assertThat(postRepository.contains(post)).isTrue(); //펄시스트 상태

        postRepository.delete(post);
        postRepository.flush();
    }
}