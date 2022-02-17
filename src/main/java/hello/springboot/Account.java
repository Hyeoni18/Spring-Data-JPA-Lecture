package hello.springboot;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "myAccount") //테이블 이름.
public class Account {

    @Id
    @GeneratedValue //자동생성
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    private String password;

    @Temporal(TemporalType.DATE)
    private Date created = new Date();

    @Transient //칼럼으로 매핑하지 않을 때 사용
    private String yes;

    //getter, setter 없이도 칼럼매핑 가능
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
