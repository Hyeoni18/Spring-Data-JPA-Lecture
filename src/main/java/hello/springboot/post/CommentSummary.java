package hello.springboot.post;

import org.springframework.beans.factory.annotation.Value;

public interface CommentSummary {

    String getComment();

    int getUp();

    int getDown();

    default String getVotes() { //interface에 default 메소드를 사용할 수 있음. 쿼리 최적화도 가능하고 커스텀하게 무언가도 계산할 수 있음.
        return getUp() + " " + getDown();
    }

    //이렇게 하면 성능 최적화는 불가능 함.
//    @Value("#{target.up + ' ' + target.down}")
//    String getVotes();
}
