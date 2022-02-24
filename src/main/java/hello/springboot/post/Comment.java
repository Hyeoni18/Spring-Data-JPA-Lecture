package hello.springboot.post;

import javax.persistence.*;

@NamedEntityGraph(name = "Comment.post", attributeNodes = @NamedAttributeNode("post"))
@Entity
public class Comment {

    @Id @GeneratedValue
    private Long id;

    private String comment;

    @ManyToOne(fetch = FetchType.LAZY) //기본설정. Comment정보 가져올 때 Post정보도 같이 가져옴. LAZY로 바꾸면 Comment만 가져옴.
    private Post post;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
