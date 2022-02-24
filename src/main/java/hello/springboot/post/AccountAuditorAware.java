package hello.springboot.post;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountAuditorAware implements AuditorAware<Account> {

    @Override
    public Optional<Account> getCurrentAuditor() {
        System.out.println("looking for current user");
        //여기서 현재 유저를 리턴 해주는거야. security를 하지는 않을거임.
        return Optional.empty();
    }
}
