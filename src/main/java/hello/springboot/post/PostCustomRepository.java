package hello.springboot.post;

import java.util.List;

public interface PostCustomRepository<T> {
    List<Post> findMyPost(); //구현체 필요, 네이밍 컨벤션 필요.

    void delete(T entity); //이러면 중복이 됨. 그럴 경우 커스텀하게 구현된 메소드 우선.
}
