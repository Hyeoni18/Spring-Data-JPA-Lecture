package hello.springboot.post;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;

    @Test
    public void getComment() {
        Post post = new Post();
        post.setTitle("JPA");
        Post savedPost = postRepository.save(post);

        Comment comment = new Comment();
        comment.setComment("Spring Data JPA");
        comment.setPost(savedPost);
        commentRepository.save(comment);

        Optional<Comment> byId = commentRepository.findById(1l);
        System.out.println(byId.get().getPost());
    }

    @Test
    public void getCommentTwo() {
        commentRepository.getById(1l); //EntityGraph사용
        System.out.println("================================");
        commentRepository.findById(1l); //기본제공, 설정한 거 없음
    }
}