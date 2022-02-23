package hello.springboot.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class PostController {

    @Autowired
    private PostRepository postRepository;

//    private final PostRepository postRepository;
//
//    public PostController(PostRepository postRepository) {
//        this.postRepository = postRepository;
//    }

    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable("id") Post post) {
        return post.getTitle();
    }
}
