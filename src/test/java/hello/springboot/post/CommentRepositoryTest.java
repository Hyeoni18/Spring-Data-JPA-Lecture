package hello.springboot.post;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
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
        Post post = new Post();
        post.setTitle("JPA");
        Post savedPost = postRepository.save(post);

        Comment comment = new Comment();
        comment.setPost(savedPost);
        comment.setUp(10);
        comment.setDown(1);
        comment.setComment("JPA COMMENT");
        commentRepository.save(comment);

        commentRepository.findByPost_Id(savedPost.getId(), CommentSummary.class).forEach( e -> {
            System.out.println(e.getVotes());
        });

        commentRepository.findByPost_Id(savedPost.getId(), CommentOnly.class).forEach( e -> {
            System.out.println(e.getComment());
        });
    }
}