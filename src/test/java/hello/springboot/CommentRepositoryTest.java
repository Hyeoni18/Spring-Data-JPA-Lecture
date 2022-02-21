package hello.springboot;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.linesOf;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CommentRepositoryTest extends TestCase {

    @Autowired
    CommentRepository commentRepository;

    @Test
    public void crud() {
        this.createComment(100,"Spring Data JPA");
        this.createComment(10,"Hibernate Spring");

        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "LikeCount")); //page 0부터 시작, 가져올 개수 10
        //try catch로 받아서 사용해야 함. 닫아줘야 하기 때문
        try(Stream<Comment> comments = commentRepository.findByCommentContainsIgnoreCase("spring", pageRequest)) {
            Comment firstComment = comments.findFirst().get();
            assertThat(firstComment.getLikeCount()).isEqualTo(100);
        }
    }

    private void createComment(int likeCount, String comment) {
        Comment newComment = new Comment();
        newComment.setComment(comment);
        newComment.setLikeCount(likeCount);
        commentRepository.save(newComment);
    }
}