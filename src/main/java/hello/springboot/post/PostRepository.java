package hello.springboot.post;

import hello.springboot.CustomRepository;
import hello.springboot.MyRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface PostRepository extends CustomRepository<Post, Long>, QuerydslPredicateExecutor<Post> {
}
