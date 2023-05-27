package com.example.askme;

import com.example.askme.domain.Memo;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class InitDB {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.init();
    }

    @Component
    @RequiredArgsConstructor
    @Transactional
    static class InitService {

        private final EntityManager em;

        public void init() {
            Memo memoOne = new Memo();
            memoOne.setQuestion("DTO를 왜 사용하는가?");
            memoOne.setAnswer("Entity를 보호하기 위해서 사용한다");
            memoOne.setCreatedDate(LocalDateTime.now());

            em.persist(memoOne);

            Memo memoTwo = new Memo();
            memoTwo.setQuestion("Spring의 5대 원칙");
            memoTwo.setAnswer("SOLID");
            memoTwo.setCreatedDate(LocalDateTime.now());

            em.persist(memoTwo);
        }
    }
}
