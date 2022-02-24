package hello.springboot.post;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static hello.springboot.post.CommentSpecs.isBest;
import static hello.springboot.post.CommentSpecs.isGood;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;

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

    @Test
    public void specs() {
        Page<Comment> page = commentRepository
                            .findAll(isBest().and(isGood()), PageRequest.of(0,10));

    }

    @Test
    public void qbe() {
        Comment prove = new Comment();
        prove.setBest(true);

        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny()
                .withIgnorePaths("up","down");

        Example<Comment> example = Example.of(prove, exampleMatcher);

        commentRepository.findAll(example);
    }
}