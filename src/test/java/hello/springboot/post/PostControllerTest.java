package hello.springboot.post;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PostControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    PostRepository postRepository;

    @Test
    public void getPost() throws Exception {
        Post post = new Post();
        post.setTitle("JPA");
        postRepository.save(post);

        mockMvc.perform(get("/posts/"+post.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("JPA"));
    }

    @Test
    public void getPosts() throws Exception {
        createPosts();

        mockMvc.perform(get("/posts/")
                        .param("page","3") //4번째 페이지 쿼리
                        .param("size","10") //한 페이지 10개씩
                        .param("sort","created,desc") //정렬. 가장 최신부터.
                        .param("sort","title") //날짜가 같으면 다음 정렬 옵션은 타이틀.
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].title").value("JPA"));
    }

    private void createPosts() {
        int postsCount = 100;
        while(postsCount > 0) {
            Post post = new Post();
            post.setTitle("JPA");
            postRepository.save(post);
            postsCount--;
        }
    }
}