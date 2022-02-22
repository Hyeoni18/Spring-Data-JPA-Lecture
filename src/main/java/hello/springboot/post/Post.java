package hello.springboot.post;

import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Post extends AbstractAggregateRoot<Post> {

    @Id @GeneratedValue
    private long id;

    private String title;

    @Lob
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Post publish() {
        //퍼블리시 할 때 여기서 만들면 되는거야
        this.registerEvent(new PostPublishedEvent(this)); //상속받은 클래스에서 제공되는 메소드. 안에 이벤트를 만들어서 넣어주면 됨. 끝임.
        return this;
    }
}
