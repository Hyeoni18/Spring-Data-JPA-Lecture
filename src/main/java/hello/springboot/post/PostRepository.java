package hello.springboot.post;

import hello.springboot.MyRepository;

public interface PostRepository extends MyRepository<Post, Long> {
}
