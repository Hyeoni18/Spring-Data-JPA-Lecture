package hello.springboot;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

public class SimpleMyRepository<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements MyRepository<T, ID> {

    private EntityManager entityManager; //상위클래스에서 전달받은 em을 사용

    public SimpleMyRepository(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager=entityManager;
    }

    @Override
    public boolean contains(T entity) {
        return entityManager.contains(entity);
    }

    //오버라이딩 해서 만들어도 됨.
    @Override
    public List<T> findAll() {
        return super.findAll();
    }
}
