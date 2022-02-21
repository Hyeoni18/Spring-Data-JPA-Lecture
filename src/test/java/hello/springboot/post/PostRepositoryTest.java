package hello.springboot.post;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PostRepositoryTest extends TestCase {

    @Autowired
    PostRepository postRepository;

    @Test
    public void curd() {
        postRepository.findMyPost();
        Post post = new Post();
        post.setTitle("Hibernate");
        postRepository.save(post);

        postRepository.findMyPost();

        postRepository.delete(post);
        postRepository.flush();
    }
}