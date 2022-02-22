package hello.springboot.post;

import com.querydsl.core.types.Predicate;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

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
        postRepository.save(post.publish());

        Predicate predicate = QPost.post.title.containsIgnoreCase("Hi");
        Optional<Post> one = postRepository.findOne(predicate);
        assertThat(one).isNotEmpty();

        assertEquals(1, postRepository.findAll().size());
        Optional<Post> nate = postRepository.findOne(QPost.post.title.contains("nate"));
        assertTrue(nate.isPresent());
        Optional<Post> jpa = postRepository.findOne(QPost.post.title.contains("jpa"));
        assertTrue(jpa.isEmpty());
    }

    @Test
    public void newCurd() {
        Post post = new Post();
        post.setTitle("Hibernate");
        Post newPost = postRepository.save(post);

        postRepository.contains(newPost);
    }
}