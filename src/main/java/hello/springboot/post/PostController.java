package hello.springboot.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable("id") Post post) {
        return post.getTitle();
    }

    @GetMapping("/posts")
    public PagedResources<Resource<Post>> getPosts(Pageable pageable, PagedResourcesAssembler<Post> assembler) { //추가한 엔티티 타입의 리소스를 만들겠다는 의미
        return assembler.toResource(postRepository.findAll(pageable));
    }
}
