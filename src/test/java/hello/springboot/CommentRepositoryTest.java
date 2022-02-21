package hello.springboot;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.linesOf;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CommentRepositoryTest extends TestCase {

    @Autowired
    CommentRepository commentRepository;

    @Test
    public void crud() {
        Optional<Comment> byId = commentRepository.findById(100l);
        assertThat(byId).isEmpty();

        List<Comment> comments = commentRepository.findAll();
        assertThat(comments).isEmpty(); //List는 Null이 나오지 않음.
        //콜렉션은 Null을 리턴하지 않고, 비어있는 콜렉션을 리턴.

        commentRepository.save(null);

    }
}