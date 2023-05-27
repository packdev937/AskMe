package com.example.askme.repository;

import com.example.askme.domain.Memo;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemoRepository {

    private final EntityManager em;
    public void save(Memo memo) {
        em.persist(memo);
    }

    public Memo findOne(Long memoId) {
        return em.find(Memo.class, memoId);
    }

    public List<Memo> findAll() {
        return em.createQuery("select m from Memo m", Memo.class)
                .getResultList();
    }
}
