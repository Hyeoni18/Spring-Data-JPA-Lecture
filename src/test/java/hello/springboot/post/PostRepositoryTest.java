package hello.springboot.post;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import(PostRepositoryTestConfig.class) //테스트용 추가설정을 import
public class PostRepositoryTest extends TestCase {

    @Autowired
    PostRepository postRepository;

    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void event() {
        //퍼블리싱
        Post post = new Post();
        post.setTitle("EVENT");
        PostPublishedEvent event = new PostPublishedEvent(post);

        applicationContext.publishEvent(event);
    }

    @Test
    public void curd() {
        Post post = new Post();
        post.setTitle("Hibernate");

        assertThat(postRepository.contains(post)).isFalse();

        postRepository.save(post.publish());

        assertThat(postRepository.contains(post)).isTrue();

        postRepository.delete(post);
        postRepository.flush();
    }
}