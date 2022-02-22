package hello.springboot.post;

import org.springframework.context.ApplicationListener;

public class PostListener implements ApplicationListener<PostPublishedEvent> { //리스너가 받을 이벤트 타입을 알려 줘야 해.
    @Override
    public void onApplicationEvent(PostPublishedEvent event) {
        //이벤트 발생 했을 때 해야할 일
        System.out.println("==============================");
        System.out.println(event.getPost().getTitle()+" is published");
        System.out.println("==============================");
    }
    //리스너가 빈으로 등록되어 있지 않음. 테스트용 컨피그를 만들거야. PostRepositoryTestConfig
}
