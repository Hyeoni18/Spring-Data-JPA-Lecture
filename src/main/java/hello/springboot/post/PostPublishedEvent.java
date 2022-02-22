package hello.springboot.post;

import org.springframework.context.ApplicationEvent;

public class PostPublishedEvent extends ApplicationEvent {

    //Post 정보 필요
    private final Post post;

    public PostPublishedEvent(Object source) {
        super(source);
        this.post = (Post) source; //이벤트 발생 시키는 곳을 지정
    }

    //이벤트 받는 리스너 쪽에서 어떤 포스터에 대한 이벤트 였는지 포스트를 참조할 수 있도록 getter 생성
    public Post getPost() {
        return post;
    }
}
