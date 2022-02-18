package hello.springboot;

import org.hibernate.Session;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Component
@Transactional
public class JpaRunner implements ApplicationRunner {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        Post post = new Post();
//        post.setTitle("Spring Data JPA");
//
//        Comment comment = new Comment();
//        comment.setComment("Ing....");
//        post.addComment(comment);
//
//        Comment comment1 = new Comment();
//        comment1.setComment("Too....");
//        post.addComment(comment1);

        Session session = entityManager.unwrap(Session.class);
        //session.save(post);
        Post post = session.get(Post.class, 1l);
        session.delete(post);
    }
}
